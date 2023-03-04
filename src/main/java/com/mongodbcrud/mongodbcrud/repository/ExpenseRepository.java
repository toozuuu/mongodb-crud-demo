package com.mongodbcrud.mongodbcrud.repository;


import com.mongodbcrud.mongodbcrud.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    Expense findByExpenseName(String name);
}
