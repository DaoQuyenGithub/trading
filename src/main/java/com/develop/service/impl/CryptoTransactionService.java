package com.develop.service.impl;

import com.develop.constant.OrderType;
import com.develop.entity.CryptoTransaction;
import com.develop.entity.Price;
import com.develop.repository.ICryptoTransactionRepository;
import com.develop.service.ICryptoTransactionService;
import com.develop.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CryptoTransactionService implements ICryptoTransactionService {
    private final ICryptoTransactionRepository transactionRepository;
    private final IPriceService priceService;

    @Override
    @Transactional
    public CryptoTransaction executeTrade(Long userId, String symbol, OrderType tradeType, BigDecimal quantity) {
        Price latestPrice = priceService.findLatestBySymbol(symbol);

        BigDecimal tradePrice = (tradeType == OrderType.BUY)
                ? latestPrice.getAskPrice()
                : latestPrice.getBidPrice();

        BigDecimal total = tradePrice.multiply(quantity);

        CryptoTransaction trade = new CryptoTransaction();
        trade.setUserId(userId);
        trade.setSymbol(symbol);
        trade.setOrderType(tradeType);
        trade.setPrice(tradePrice);
        trade.setQuantity(quantity);
        trade.setTotalValue(total);

        return transactionRepository.save(trade);
    }
}
