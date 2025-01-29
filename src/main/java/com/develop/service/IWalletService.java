package com.develop.service;

import com.develop.constant.OrderType;
import com.develop.dto.response.WalletResp;
import com.develop.entity.Wallet;

import java.math.BigDecimal;

public interface IWalletService {
    Wallet init(Long userId);

    WalletResp getCurrentBalanceByUserId(Long userId);

    Wallet findByUserId(Long userId);

    Wallet updateAfterTrade(Long userId, BigDecimal totalTrade, OrderType orderType, String symbol, BigDecimal quantity);
}
