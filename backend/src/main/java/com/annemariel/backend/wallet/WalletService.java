package com.annemariel.backend.wallet;

import com.annemariel.backend.user.UserRepository;
import com.annemariel.backend.wallet.dto.WalletResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public List<WalletResponseDto> findAllWallets() {
        List<WalletResponseDto> wallets = new ArrayList<>();
        walletRepository.findAll().forEach(a -> wallets.add(new WalletResponseDto(a.getId(), a.getName(), a.getBalance())));
        return wallets;
    }

    public WalletResponseDto findById(String id) {
        return walletRepository.findById(id).map(a -> new WalletResponseDto(a.getId(), a.getName(), a.getBalance())).orElseThrow();
    }
}
