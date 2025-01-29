package com.develop.service.impl;

import com.develop.constant.OrderType;
import com.develop.constant.RespCode;
import com.develop.dto.response.WalletResp;
import com.develop.entity.Wallet;
import com.develop.exception.BusinessException;
import com.develop.mapper.IWalletMapper;
import com.develop.repository.IWalletRepository;
import com.develop.service.IWalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.develop.constant.AppConstants.BTCUSDT;
import static com.develop.constant.AppConstants.ETHUSDT;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {

    private final IWalletRepository walletRepository;
    private final IWalletMapper mapper = IWalletMapper.INSTANCE;

    public Wallet init(Long userId) {
        Wallet entity = new Wallet();
        entity.setUserId(userId);
        entity.setBalanceBtc(BigDecimal.ZERO);
        entity.setBalanceEth(BigDecimal.ZERO);
        entity.setBalanceUsdt(new BigDecimal(50000));
        return walletRepository.save(entity);
    }

    @Override
    public WalletResp getCurrentBalanceByUserId(Long userId) {
        Wallet walletEnt = this.findByUserId(userId);
        WalletResp resp = mapper.toResp(walletEnt);
        return resp;
    }

    @Override
    public Wallet findByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
               .orElseThrow(() -> {
                   log.error("Not found for userId: {}", userId);
                   throw new BusinessException(RespCode.NOT_FOUND, "Not found for userId: " + userId);
               });
    }

    @Override
    public Wallet updateAfterTrade(Long userId, BigDecimal totalTrade, OrderType orderType,
                                   String symbol, BigDecimal quantity) {
        Wallet walletReq = this.findByUserId(userId);
        if(orderType == OrderType.BUY ) {
            if(walletReq.getBalanceUsdt().compareTo(totalTrade) == -1) {
                log.error("Invalid USDT balance. Available: {} USDT, Requested: {} USDT", walletReq.getBalanceUsdt(), totalTrade);
                throw new BusinessException(RespCode.ERR_DATA_INVALID,
                        String.format("Invalid USDT balance. Available: %s USDT, Requested: %s USDT", walletReq.getBalanceUsdt(), totalTrade));
            }
            BigDecimal currentWallet = walletReq.getBalanceUsdt().subtract(totalTrade);
            walletReq.setBalanceUsdt(currentWallet);
        }else {
            walletReq.setBalanceUsdt(walletReq.getBalanceUsdt().add(totalTrade));
        }

        switch (symbol) {
            case ETHUSDT -> {
                if(orderType == OrderType.SELL) {
                    if(walletReq.getBalanceEth().compareTo(quantity) < 0) {
                        log.error("Invalid ETH balance. Available: {} ETH, Requested: {} ETH", walletReq.getBalanceEth(), quantity);
                        throw new BusinessException(RespCode.ERR_DATA_INVALID,
                                String.format("Invalid ETH balance. Available: %s ETH, Requested: %s ETH", walletReq.getBalanceEth(), quantity));
                    }
                    BigDecimal balanceEth = walletReq.getBalanceEth().subtract(quantity);
                    walletReq.setBalanceEth(balanceEth);
                } else {
                    walletReq.setBalanceEth(walletReq.getBalanceEth().add(quantity));
                }
            }
            case BTCUSDT -> {
                if(orderType == OrderType.SELL) {
                    if(walletReq.getBalanceBtc().compareTo(quantity) < 0) {
                        log.error("Invalid BTC balance. Available: %s BTC, Requested: %s BTC", walletReq.getBalanceBtc(), quantity);
                        throw new BusinessException(RespCode.ERR_DATA_INVALID,
                                String.format("Invalid BTC balance. Available: %s BTC, Requested: %s BTC", walletReq.getBalanceBtc(), quantity));
                    }
                    BigDecimal balanceBtc = walletReq.getBalanceBtc().subtract(quantity);
                    walletReq.setBalanceBtc(balanceBtc);
                } else {
                    walletReq.setBalanceBtc(walletReq.getBalanceBtc().add(quantity));
                }
            }
            default -> {
                log.error("Not support crypto: {}", symbol);
                throw new BusinessException(RespCode.NOT_SUPPORT, "Not support crypto: " + symbol );
            }
        }

        Wallet walletResp = walletRepository.save(walletReq);
        return walletResp;
    }
}
