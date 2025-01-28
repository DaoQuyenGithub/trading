package com.develop.service;

import com.develop.constant.OrderType;
import com.develop.entity.CryptoTransaction;

import java.math.BigDecimal;

public interface ICryptoTransactionService {
    CryptoTransaction executeTrade(Long userId, String symbol, OrderType tradeType, BigDecimal quantity);
}
