package com.develop.entity;

import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "crypto_wallet")
public class Wallet extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal balanceUsdt = new BigDecimal(50000 );

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal balanceEth = BigDecimal.ZERO;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal balanceBtc = BigDecimal.ZERO;
}
