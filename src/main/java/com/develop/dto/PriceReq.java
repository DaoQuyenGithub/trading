package com.develop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceReq {
    private Long id;
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
}