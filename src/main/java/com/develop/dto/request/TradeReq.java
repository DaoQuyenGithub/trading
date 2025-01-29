package com.develop.dto.request;

import com.develop.constant.OrderType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeReq {

    @NotNull
    private Long userId;

    @NotNull
    private String symbol;

    @NotNull
    private OrderType tradeType;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false, message = "Quantity must be greater than 0")
    private BigDecimal quantity;
}
