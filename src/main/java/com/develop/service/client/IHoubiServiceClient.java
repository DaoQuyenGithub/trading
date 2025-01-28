package com.develop.service.client;

import com.develop.dto.HoubiResp;
import feign.RequestLine;

public interface IHoubiServiceClient {
    @RequestLine("GET /market/tickers")
    HoubiResp getPriceAggregation();
}
