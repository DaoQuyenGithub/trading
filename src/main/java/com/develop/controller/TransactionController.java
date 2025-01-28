package com.develop.controller;

import com.develop.constant.OrderType;
import com.develop.entity.CryptoTransaction;
import com.develop.service.ICryptoTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class TransactionController {
    private final ICryptoTransactionService transactionService;

    @PostMapping
    public CryptoTransaction executeTrade(
            @RequestParam Long userId,
            @RequestParam String symbol,
            @RequestParam OrderType tradeType,
            @RequestParam BigDecimal quantity) {
        return transactionService.executeTrade(userId, symbol, tradeType, quantity);
    }
}
