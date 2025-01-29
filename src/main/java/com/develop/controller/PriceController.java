package com.develop.controller;

import com.develop.dto.response.PriceResp;
import com.develop.service.IPriceService;
import com.develop.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/pricing")
public class PriceController {
    private final IPriceService pricingService;

    @GetMapping("/latest")
    public List<PriceResp> getLatestBestPrice() {
        log.info("[GET] start load pricing latest");
        var startTime = System.currentTimeMillis();
        List<PriceResp> priceResps = pricingService.getLatestPriceForPair();
        log.info("[GET] pricing latest response Data={} ExecuteTime={}ms", CommonUtils.toJsonString(priceResps), System.currentTimeMillis() - startTime);
        return priceResps;
    }
}
