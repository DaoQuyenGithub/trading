package com.develop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BinanceResp {
    private String symbol;
    private BigDecimal bidPrice;
    private Double bidQty;
    private BigDecimal askPrice;
    private Double askQty;
}
