package com.annemariel.backend.expense;

import com.annemariel.backend.expense.dto.ExpenseRequestDto;
import com.annemariel.backend.expense.dto.ExpenseRequestUpdateDto;
import com.annemariel.backend.expense.dto.ExpenseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.findAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(expenseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody ExpenseRequestDto dto) {
        ExpenseResponseDto createdExpense = expenseService.createExpense(dto);
        return ResponseEntity.created(URI.create("/api/expenses/" + createdExpense.id())).body(createdExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@PathVariable String id, @RequestBody ExpenseRequestUpdateDto dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }
}
