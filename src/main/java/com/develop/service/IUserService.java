package com.develop.service;

import com.develop.dto.response.UserWalletResp;

public interface IUserService {
    UserWalletResp createUser();
    UserWalletResp initUser();
}
