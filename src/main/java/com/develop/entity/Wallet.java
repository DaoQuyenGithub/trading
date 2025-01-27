package com.develop.entity;

import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "crypto_wallet")
public class Wallet extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "balance_usdt", nullable = false, precision = 20, scale = 2)
    private BigDecimal balanceUsdt = new BigDecimal(50000 );

    @Column(name = "balance_eth", nullable = false, precision = 20, scale = 8)
    private BigDecimal balanceEth = BigDecimal.ZERO;

    @Column(name = "balance_btc", nullable = false, precision = 20, scale = 8)
    private BigDecimal balanceBtc = BigDecimal.ZERO;
}
