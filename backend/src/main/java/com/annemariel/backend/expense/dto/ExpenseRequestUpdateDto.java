package com.annemariel.backend.expense.dto;

public record ExpenseRequestUpdateDto(double amount, String category, String date) {
}
