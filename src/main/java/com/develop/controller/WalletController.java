package com.develop.controller;

import com.develop.dto.response.WalletResp;
import com.develop.service.IWalletService;
import com.develop.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
    private final IWalletService walletService;

    @PostMapping("/{userId}")
    public WalletResp currentBalance(@PathVariable Long userId) {
        log.info("[GET] current balance userID={}", userId);
        var startTime = System.currentTimeMillis();
        WalletResp res = walletService.getCurrentBalanceByUserId(userId);
        log.info("[GET] current balance response Data={} ExecuteTime={}ms", CommonUtils.toJsonString(res), System.currentTimeMillis() - startTime);
        return res;
    }

}
