package com.mongodbcrud.mongodbcrud.dto;

import com.mongodbcrud.mongodbcrud.model.ExpenseCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseResponse {
    private String id;
    private String expenseName;
    private ExpenseCategory expenseCategory;
    private BigDecimal expenseAmount;
}
