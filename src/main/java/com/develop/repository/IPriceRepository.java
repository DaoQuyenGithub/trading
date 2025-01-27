package com.develop.repository;

import com.develop.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPriceRepository extends JpaRepository<Price, Long> {
    Price findFirstByTradingPairOrderByCreatedAtDesc(String tradingPair);
}