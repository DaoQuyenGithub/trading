package com.develop.entity;

import com.develop.constant.OrderType;
import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "crypto_transaction")
public class CryptoTransaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderType orderType;

    @Column
    private BigDecimal quantity;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal total;

    @Column
    private BigDecimal currentBalanceUsdt;

    @Column
    private BigDecimal currentBalanceEth;

    @Column
    private BigDecimal currentBalanceBtc;

}
