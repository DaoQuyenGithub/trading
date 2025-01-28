package com.develop.service;

import com.develop.dto.BinanceResp;
import com.develop.dto.HoubiDataResp;
import com.develop.dto.PriceResp;
import com.develop.entity.Price;
import com.develop.dto.PriceReq;

import java.util.List;

public interface IPriceService {
    Price save(PriceReq req);
    List<Price> saveAll(List<Price> prices);
    List<PriceResp> getLatestPriceForPair();
    List<Price> getBestPriceAggregation(List<HoubiDataResp> houbis, List<BinanceResp> binances);

    Price findLatestBySymbol(String symbol);
}
