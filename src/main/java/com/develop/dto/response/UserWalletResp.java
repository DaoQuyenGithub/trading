package com.develop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWalletResp {
    private Long userId;
    private Long walletId;
    private BigDecimal balanceUsdt;
    private BigDecimal balanceEth;
    private BigDecimal balanceBtc;
}
