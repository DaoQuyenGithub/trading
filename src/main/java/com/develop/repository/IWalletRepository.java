package com.develop.repository;

import com.develop.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
}