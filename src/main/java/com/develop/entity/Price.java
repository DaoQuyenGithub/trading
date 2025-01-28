package com.develop.entity;

import com.develop.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "crypto_price")
public class Price extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String symbol;

    @Column(nullable = false, precision = 18, scale = 8)
    private BigDecimal bidPrice;

    @Column(nullable = false, precision = 18, scale = 8)
    private BigDecimal askPrice;
}