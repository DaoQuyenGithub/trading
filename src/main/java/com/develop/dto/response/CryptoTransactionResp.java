package com.develop.dto.response;

import com.develop.constant.OrderType;
import com.develop.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoTransactionResp extends Auditable {
    private Long id;
    private Long userId;
    private String symbol;
    private OrderType orderType;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal total;
    private BigDecimal currentBalanceUsdt;
    private BigDecimal currentBalanceEth;
    private BigDecimal currentBalanceBtc;
}
