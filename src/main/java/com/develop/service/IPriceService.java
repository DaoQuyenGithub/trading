package com.develop.service;

import com.develop.dto.response.BinanceResp;
import com.develop.dto.response.HoubiDataResp;
import com.develop.dto.response.PriceResp;
import com.develop.entity.Price;

import java.util.List;

public interface IPriceService {
    List<Price> saveAll(List<Price> prices);
    List<PriceResp> getLatestPriceForPair();
    List<Price> getBestPriceAggregation(List<HoubiDataResp> houbis, List<BinanceResp> binances);

    Price findLatestBySymbol(String symbol);
}
