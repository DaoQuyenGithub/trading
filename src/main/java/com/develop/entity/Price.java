package com.develop.entity;

import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "crypto_price")
public class Price extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trading_pair", nullable = false, length = 10)
    private String tradingPair;

    @Column(name = "bid_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal bidPrice;

    @Column(name = "ask_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal askPrice;
}