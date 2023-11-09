package com.annemariel.backend.expense;

import com.annemariel.backend.expense.dto.ExpenseResponseDto;
import com.annemariel.backend.user.UserRepository;
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
}