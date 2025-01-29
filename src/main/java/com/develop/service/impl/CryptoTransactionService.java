package com.develop.service.impl;

import com.develop.constant.OrderType;
import com.develop.dto.response.CryptoTransactionResp;
import com.develop.entity.CryptoTransaction;
import com.develop.entity.Price;
import com.develop.entity.Wallet;
import com.develop.mapper.ICryptorTransactionMapper;
import com.develop.repository.ICryptoTransactionRepository;
import com.develop.service.ICryptoTransactionService;
import com.develop.service.IPriceService;
import com.develop.service.IWalletService;
import com.develop.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoTransactionService implements ICryptoTransactionService {
    private final ICryptoTransactionRepository transactionRepository;
    private final IPriceService priceService;
    private final IWalletService walletService;
    private final ICryptorTransactionMapper mapper = ICryptorTransactionMapper.INSTANCE;

    @Override
    @Transactional
    public CryptoTransactionResp executeTrade(Long userId, String symbol, OrderType tradeType, BigDecimal quantity) {
        Price latestPrice = priceService.findLatestBySymbol(symbol);
        BigDecimal tradePrice = (tradeType == OrderType.BUY)
                ? latestPrice.getAskPrice()
                : latestPrice.getBidPrice();
        BigDecimal total = tradePrice.multiply(quantity);

        Wallet wallet = walletService.updateAfterTrade(userId, total,tradeType, symbol, quantity);
        log.info("update wallet after trade successfully data={}", CommonUtils.toJsonString(wallet));

        CryptoTransaction trade = new CryptoTransaction();
        trade.setUserId(userId);
        trade.setSymbol(symbol);
        trade.setOrderType(tradeType);
        trade.setPrice(tradePrice);
        trade.setQuantity(quantity);
        trade.setTotal(total);
        trade.setCurrentBalanceUsdt(wallet.getBalanceUsdt());
        trade.setCurrentBalanceEth(wallet.getBalanceEth());
        trade.setCurrentBalanceBtc(wallet.getBalanceBtc());

        log.info("save transaction data={}", CommonUtils.toJsonString(trade));
        CryptoTransaction res = transactionRepository.save(trade);
        return mapper.toResp(res);
    }

    @Override
    public List<CryptoTransactionResp> getUserTradeHistory(Long userId) {
        List<CryptoTransaction> entity = transactionRepository.findByUserIdOrderByUpdatedAtDesc(userId);
        return mapper.toResp(entity);
    }
}
