package com.kindredgroup.unibetlivetest.batchs;


import com.kindredgroup.unibetlivetest.service.BetService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class MarketBatch {
    private final BetService betService;

    private static MarketBatch instance;

    public static synchronized MarketBatch getInstance(BetService betService) {
        if (instance == null) {
            instance = new MarketBatch(betService);
        }
        return instance;
    }
    /**
     * TODO
     *  void payerLesParisBatch()
     */
    @Scheduled(fixedRate = 5000)
    void payerLesParisBatch(){
        long startTime = System.nanoTime();
        log.info("{} bet(s) results.", betService.payBets().size());
        long endTime = System.nanoTime();
        long durationInNano = endTime - startTime;
        double durationInMilli = durationInNano / 1_000_000.0;
        log.info("Execution time {}.",durationInMilli);
    }

}
