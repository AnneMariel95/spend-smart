package com.annemariel.backend.wallet.dto;

public record WalletRequestDto(String name, double balance, String userId) {
}