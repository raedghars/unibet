package com.kindredgroup.unibetlivetest.service;


import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.exception.CustomHttpStatusException;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.Enums.*;
import com.kindredgroup.unibetlivetest.types.Request.BetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Log4j2
@Service
@RequiredArgsConstructor
public class BetService {
    private final BetRepository betRepository;
    private final SelectionRepository selectionRepository;
    private final CustomerService customerService;
    private final Object verrou = new Object();

    public  Bet saveBet(BetRequest betRequest) {

        synchronized (verrou) {
            log.info("Verifier conditions pour enregistrer un pari");
            /*if (Thread.holdsLock(verrou)) {
                throw new CustomException(" Conflit, pari déjà en cours*", ExceptionType.BAD_REQUEST);
            }*/

            if (betRequest.getSelectionId() == null || betRequest.getCote().compareTo(BigDecimal.ZERO) == 0 || betRequest.getMise() <= 0) {
                throw new CustomException(" Requête mal formée", ExceptionType.BAD_REQUEST);
            }
            Selection s = selectionRepository.findById(betRequest.getSelectionId()).orElseThrow(
                    () -> new CustomException(" Requête mal formée", ExceptionType.BAD_REQUEST)
            );
            if (s.getState() == SelectionState.CLOSED) {
                throw new CustomHttpStatusException("Selection fermée",CustomHttpStatus.SELECTION_CLOSED);
            }
            if (betRequest.getMise() > 50.0) {
                throw new CustomHttpStatusException(" Balance insuffissante", CustomHttpStatus.INSUFFISANT_BALANCE);
            }
            if ( betRequest.getCote().compareTo(s.getCurrentOdd()) != 0) {
                throw new CustomHttpStatusException("Changement de cote", CustomHttpStatus.ODD_CHANGED);
            }
            log.info("verification termine");
            log.info("Ajouter pari");
            Bet newBet = new Bet();
            newBet.setDate(new Date());
            newBet.setSelection(s);
            newBet.setCustomer(customerService.findCustomerByPseudo("unibest"));
            return betRepository.save(newBet);
        }

    }

    public List<Bet> findBetsWithClosedSelection(){
        log.info("Recuperation des ids selections ferme");
        List<Long> selectionsClosed=selectionRepository.getSelectionByStateEquals(SelectionState.CLOSED)
                .stream().map(e->e.getId()).toList();
        return betRepository.findAllBySelectionIdIn(selectionsClosed);

    }
    public List<Bet> payBets(){
        log.info("get bets results");
        return findBetsWithClosedSelection().stream().map(
                e->{if(e.getSelection().getResult()== SelectionResult.WON){
                    e.setBetState(BetState.WON);
                }
                else e.setBetState(BetState.LOST);
                return e;}
        ).toList();
    }
}
