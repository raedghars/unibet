package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;

    public List<Market> findAllByEventId(Long eventId){
       return marketRepository.findAllByEvent_Id(eventId);


    }
}
