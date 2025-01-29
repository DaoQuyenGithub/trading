package com.develop.service.client;

import com.develop.dto.response.BinanceResp;
import feign.RequestLine;

import java.util.List;

public interface IBinanceServiceClient {
    @RequestLine("GET /ticker/bookTicker")
    List<BinanceResp> getPriceAggregation();
}
