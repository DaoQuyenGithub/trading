package com.develop.repository;

import com.develop.entity.CryptoTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICryptoTransactionRepository extends JpaRepository<CryptoTransaction, Long> {
    List<CryptoTransaction> findByUserId(Long userId);
    List<CryptoTransaction> findByUserIdAndSymbol(Long userId, String tradingPair);
}
