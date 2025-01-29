package com.develop.service;

import com.develop.constant.OrderType;
import com.develop.dto.response.CryptoTransactionResp;

import java.math.BigDecimal;
import java.util.List;

public interface ICryptoTransactionService {
    CryptoTransactionResp executeTrade(Long userId, String symbol, OrderType tradeType, BigDecimal quantity);

    List<CryptoTransactionResp> getUserTradeHistory(Long userId);
}
