package com.develop.service.impl;

import com.develop.entity.Wallet;
import com.develop.repository.IWalletRepository;
import com.develop.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {

    private final IWalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public void updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
