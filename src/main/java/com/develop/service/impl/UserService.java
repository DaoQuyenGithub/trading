package com.develop.service.impl;

import com.develop.dto.response.UserWalletResp;
import com.develop.entity.User;
import com.develop.entity.Wallet;
import com.develop.mapper.IUserMapper;
import com.develop.repository.IUserRepository;
import com.develop.service.IUserService;
import com.develop.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IWalletService walletService;
    private final IUserMapper mapper = IUserMapper.INSTANCE;

    @Override
    public UserWalletResp createUser() {
        User user = userRepository.save(new User());
        return mapper.toRes(user);
    }

    @Override
    @Transactional
    public UserWalletResp initUser() {
        User user = userRepository.save(new User());
        Wallet wallet = walletService.init(user.getId());
        UserWalletResp userWalletResp = mapper.toUserWalletResp(wallet);
        return userWalletResp;
    }
}
