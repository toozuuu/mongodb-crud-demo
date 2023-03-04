package com.mongodbcrud.mongodbcrud.service;

import com.mongodbcrud.mongodbcrud.dto.ExpenseRequest;
import com.mongodbcrud.mongodbcrud.model.Expense;
import com.mongodbcrud.mongodbcrud.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public void addExpense(ExpenseRequest expenseRequest) {

        Expense expense = Expense.builder()
                .expenseAmount(expenseRequest.getExpenseAmount())
                .expenseName(expenseRequest.getExpenseName())
                .expenseCategory(expenseRequest.getExpenseCategory())
                .build();
        expenseRepository.save(expense);

        log.info("Expense {} is saved!", expenseRequest.getId());

    }

    public List<Expense> getAll() {
        List<Expense> expenseList = expenseRepository.findAll();
        if (!expenseList.isEmpty()) {
            return expenseList.stream().map(this::expenseMapToList).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private Expense expenseMapToList(Expense expense) {
        return Expense.builder().id(expense.getId())
                .expenseCategory(expense.getExpenseCategory())
                .expenseName(expense.getExpenseName())
                .expenseAmount(expense.getExpenseAmount())
                .build();
    }

    public Boolean updateExpense(ExpenseRequest expenseRequest) {
        Optional<Expense> expense = expenseRepository.findById(expenseRequest.getId());
        if (expense.isPresent()) {
            Expense exp = expense.get();
            exp.setExpenseName(expenseRequest.getExpenseName());
            exp.setExpenseAmount(expenseRequest.getExpenseAmount());
            exp.setExpenseCategory(expenseRequest.getExpenseCategory());
            expenseRepository.save(exp);
            return true;
        }
        return false;
    }

    public ExpenseRequest findByExpenseName(String name) {
        Expense expense = expenseRepository.findByExpenseName(name);
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setId(expense.getId());
        expenseRequest.setExpenseName(expense.getExpenseName());
        expenseRequest.setExpenseAmount(expense.getExpenseAmount());
        expenseRequest.setExpenseCategory(expense.getExpenseCategory());
        return expenseRequest;
    }

    public Boolean deleteExpense(String id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            expenseRepository.deleteById(id);

            log.info("Expense {} is deleted!", id);

            return true;
        }

        log.info("Expense {} not found!", id);

        return false;
    }
}
