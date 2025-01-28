package com.develop.config.job;


import com.develop.dto.BinanceResp;
import com.develop.dto.HoubiDataResp;
import com.develop.dto.HoubiResp;
import com.develop.entity.Price;
import com.develop.service.IPriceService;
import com.develop.service.client.IBinanceServiceClient;
import com.develop.service.client.IHoubiServiceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CollectPriceAggregation {
    private final IBinanceServiceClient binanceServiceClient;
    private final IHoubiServiceClient houbiServiceClient;
    private final IPriceService priceService;


    @Transactional(rollbackFor = Exception.class)
    @Scheduled(fixedRate = 10000)
    public void runCollectBestPriceEveryTenSecond() {
        List<BinanceResp> binances = new ArrayList<>();
        List<HoubiDataResp> houbiData = new ArrayList<>();
        try {
            binances = binanceServiceClient.getPriceAggregation();
            log.info("Fetch data BIANANCE successfully");
            HoubiResp houbiResp = houbiServiceClient.getPriceAggregation();
            if(houbiResp.getStatus().equals("ok")) {
                houbiData = houbiResp.getData();
            }
            log.info("Fetch data HOUBI successfully");
        } catch (FeignException ex) {
            log.error("Fetch data failed error={}", ex.getMessage());
        }

//        Long latestUpdate = priceService.getNextSequenceValue();
        List<Price> prices = priceService.getBestPriceAggregation(houbiData, binances);
        priceService.saveAll(prices);
        log.info("ok");
    }
}
