package com.develop.controller;

import com.develop.dto.response.CryptoTransactionResp;
import com.develop.dto.request.TradeReq;
import com.develop.service.ICryptoTransactionService;
import com.develop.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final ICryptoTransactionService transactionService;

    @PostMapping
    public CryptoTransactionResp executeTrade(@RequestBody @Valid TradeReq req) {
        log.info("[POST] start trade crypto request={}", CommonUtils.toJsonString(req));
        var startTime = System.currentTimeMillis();
        CryptoTransactionResp res = transactionService.executeTrade(req.getUserId(), req.getSymbol(), req.getTradeType(), req.getQuantity());
        log.info("[POST] trade crypto response Data={} ExecuteTime={}ms", CommonUtils.toJsonString(res), System.currentTimeMillis() - startTime);
        return res;
    }

    @GetMapping("/{userId}")
    public List<CryptoTransactionResp> tradingHistory(@PathVariable Long userId) {
        log.info("[GET] start load trading history userId={}", userId);
        var startTime = System.currentTimeMillis();
        List<CryptoTransactionResp> res = transactionService.getUserTradeHistory(userId);
        log.info("[GET] load trading history response Data={} ExecuteTime={}ms", CommonUtils.toJsonString(res), System.currentTimeMillis() - startTime);
        return res;
    }
}
