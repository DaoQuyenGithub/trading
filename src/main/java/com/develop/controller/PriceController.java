package com.develop.controller;

import com.develop.dto.PriceResp;
import com.develop.entity.Price;
import com.develop.dto.PriceReq;
import com.develop.service.IPriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PriceController {
    private final IPriceService pricingService;

    @GetMapping("/api/v1/pricing/latest")
    public List<PriceResp> getLatestBestPrice() {
        List<PriceResp> priceResps = pricingService.getLatestPriceForPair();
        return priceResps;
    }

    @PostMapping("/api/v1/pricing")
    public Price save(@RequestBody PriceReq price) {
        return pricingService.save(price);
    }
}
