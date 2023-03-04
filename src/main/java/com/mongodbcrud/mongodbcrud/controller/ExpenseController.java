package com.mongodbcrud.mongodbcrud.controller;

import com.mongodbcrud.mongodbcrud.model.Expense;
import com.mongodbcrud.mongodbcrud.dto.ExpenseRequest;
import com.mongodbcrud.mongodbcrud.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/api/save")
    public ResponseEntity<ExpenseRequest> create(@RequestBody ExpenseRequest expenseRequest) {
        expenseService.addExpense(expenseRequest);
        return new ResponseEntity<>(expenseRequest, HttpStatus.CREATED);
    }

    @PostMapping("/api/update")
    public ResponseEntity<Boolean> updateExpense(@RequestBody ExpenseRequest expenseRequest) {
        boolean isUpdated = expenseService.updateExpense(expenseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(isUpdated);
    }

    @GetMapping("/api/all")
    public ResponseEntity<List<Expense>> get() {
        return ResponseEntity.ok(expenseService.getAll());
    }

    @GetMapping("/api/{expenseName}")
    public ResponseEntity<ExpenseRequest> getExpenseByName(@PathVariable String expenseName) {
        ExpenseRequest expense = expenseService.findByExpenseName(expenseName);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable String id) {
        boolean isDeleted = expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }
}
