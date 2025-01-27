package com.develop.service.impl;

import com.develop.entity.Price;
import com.develop.repository.IPriceRepository;
import com.develop.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
    private final IPriceRepository priceRepository;

    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Price getLatestPriceForPair(String tradingPair) {
        return priceRepository.findFirstByTradingPairOrderByCreatedAtDesc(tradingPair);
    }
}
