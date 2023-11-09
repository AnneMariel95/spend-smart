package com.annemariel.backend.expense;

import com.annemariel.backend.expense.dto.ExpenseRequestDto;
import com.annemariel.backend.expense.dto.ExpenseRequestUpdateDto;
import com.annemariel.backend.expense.dto.ExpenseResponseDto;
import com.annemariel.backend.user.User;
import com.annemariel.backend.user.UserRepository;
import com.annemariel.backend.wallet.Wallet;
import com.annemariel.backend.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, WalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<ExpenseResponseDto> findAllExpenses() {
        List<ExpenseResponseDto> allExpenses = new ArrayList<>();
        expenseRepository.findAll().forEach(e -> allExpenses.add(new ExpenseResponseDto(e.getId(), e.getAmount(), e.getCategory(), e.getDate())));
        return allExpenses.stream().sorted(Comparator.comparing(obj -> LocalDate.parse(obj.date(), dateFormatter))).toList();
    }

    public ExpenseResponseDto findById(String id) {
        return expenseRepository.findById(id).map(e -> new ExpenseResponseDto(e.getId(), e.getAmount(), e.getCategory(), e.getDate())).orElseThrow();
    }

    public ExpenseResponseDto createExpense(ExpenseRequestDto dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Wallet acc = walletRepository.findById(dto.walletId()).orElseThrow();
        Expense exp = new Expense(dto.category(), dto.amount(), dto.date(), acc, user);
        expenseRepository.save(exp);
        acc.setBalance(acc.getBalance() - exp.getAmount());
        walletRepository.save(acc);
        return new ExpenseResponseDto(exp.getId(), exp.getAmount(), exp.getCategory(), exp.getDate());
    }

    public ExpenseResponseDto updateExpense(String id, ExpenseRequestUpdateDto dto) {
        Expense expense = expenseRepository.findById((id)).orElseThrow();
        expense.setCategory(dto.category());
        expense.setAmount(dto.amount());
        expense.setDate(dto.date());
        expenseRepository.save(expense);
        return new ExpenseResponseDto(expense.getId(), expense.getAmount(), expense.getCategory(), expense.getDate());
    }
}