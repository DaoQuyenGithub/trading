package com.develop.service.impl;

import com.develop.entity.CryptoTransaction;
import com.develop.repository.ICryptoTransactionRepository;
import com.develop.service.ICryptoTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoTransactionService implements ICryptoTransactionService {
    private final ICryptoTransactionRepository transactionRepository;

    public CryptoTransaction saveTransaction(CryptoTransaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<CryptoTransaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<CryptoTransaction> getTransactionsByUserIdAndPair(Long userId, String tradingPair) {
        return transactionRepository.findByUserIdAndTradingPair(userId, tradingPair);
    }
}
