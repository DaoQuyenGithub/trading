package com.develop.entity;

import com.develop.constant.OrderType;
import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "crypto_trading_transaction")
public class CryptoTransaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "trading_pair", nullable = false, length = 20)
    private String tradingPair;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 4)
    private OrderType orderType;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal price;

    @Column(name = "total_value", nullable = false, precision = 20, scale = 2)
    private BigDecimal totalValue;

}
