package com.annemariel.backend.expense.dto;

public record ExpenseRequestDto(double amount, String category, String date, String walletId, String userId) {
}
