package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import com.kindredgroup.unibetlivetest.types.Enums.ExceptionType;
import com.kindredgroup.unibetlivetest.types.Enums.SelectionState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final MarketService marketService;

    public Event findEventById(Long eventId){
        log.info("Recuperation événement par eventId ");
        return eventRepository.findById(eventId)
                        .orElseThrow(() -> new CustomException("Evénement introuvable", ExceptionType.EVENT_NOT_FOUND));
    }
    public List<Event> findAllEventsLive(){
        log.info("Recuperation des événements live");
        return eventRepository.findAll();
    }
    public List<Selection> findAllSelectionsByEventId(Long eventId, Optional<SelectionState> selectionState){
        this.findEventById(eventId);
        log.info("Recuperation des markets avec eventId");
        List<Market> listMarkets=marketService.findAllByEventId(eventId);
        List<Selection> listSelections=new ArrayList<>();
        log.info("Recuperation des selections");
        listMarkets.forEach(e->listSelections.addAll(e.getSelections()));
        if(selectionState.isPresent()){
            log.info("Recuperation des selections selon state");
            return listSelections.stream().filter(
                    e->(e.getState().equals(selectionState.get()))
            ).toList();
        }
        return listSelections ;

    }


}
