package com.annemariel.backend.expense.dto;

public record ExpenseResponseDto(String id, double amount, String category, String date) {
}
