package com.kindredgroup.unibetlivetest.api;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.service.BetService;
import com.kindredgroup.unibetlivetest.types.Request.BetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(Urls.BASE_PATH)
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BetApi {
    private final BetService betService;

    /** TODO
     *  @PostMapping(Urls.ADD_BET)
     */
    @PostMapping(Urls.ADD_BET)
     public ResponseEntity<Bet> addBet(@RequestBody BetRequest betRequest){
        return ResponseEntity.ok( betService.saveBet(betRequest));
    }



}
