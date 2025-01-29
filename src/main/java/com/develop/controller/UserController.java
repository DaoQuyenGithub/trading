package com.develop.controller;

import com.develop.dto.response.UserWalletResp;
import com.develop.service.IUserService;
import com.develop.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    @PostMapping
    public UserWalletResp initUser() {
        log.info("[GET] start init user");
        var startTime = System.currentTimeMillis();
        UserWalletResp res = userService.initUser();
        log.info("[GET] init user response Data={} ExecuteTime={}ms", CommonUtils.toJsonString(res), System.currentTimeMillis() - startTime);
        return res;
    }

}
