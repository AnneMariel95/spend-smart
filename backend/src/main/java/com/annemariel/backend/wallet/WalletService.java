package com.annemariel.backend.wallet;

import com.annemariel.backend.user.User;
import com.annemariel.backend.user.UserRepository;
import com.annemariel.backend.wallet.dto.WalletRequestDto;
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

    public WalletResponseDto createWallet(WalletRequestDto dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Wallet acc = new Wallet(dto.name(), dto.balance(), user);
        walletRepository.save(acc);
        return new WalletResponseDto(acc.getId(), acc.getName(), acc.getBalance());
    }

    public WalletResponseDto updateWallet(String id, WalletRequestDto dto) {
        Wallet wallet = walletRepository.findById((id)).orElseThrow();
        wallet.setName(dto.name());
        wallet.setBalance(dto.balance());
        walletRepository.save(wallet);
        return new WalletResponseDto(wallet.getId(), wallet.getName(), wallet.getBalance());
    }
}
