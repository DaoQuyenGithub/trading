package com.develop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResp {
    private Long id;
    private Long userId;
    private BigDecimal balanceUsdt;
    private BigDecimal balanceEth;
    private BigDecimal balanceBtc;
}
