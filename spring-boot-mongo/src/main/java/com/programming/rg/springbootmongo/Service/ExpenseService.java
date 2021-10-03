package com.programming.rg.springbootmongo.Service;

import com.programming.rg.springbootmongo.model.Expense;
import com.programming.rg.springbootmongo.repository.ExpenseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public Expense getExpenseByName(String ExpenseName) {
        return expenseRepository.findByName(ExpenseName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Expense by Name %s", ExpenseName)));
    }

    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(()
                -> new RuntimeException(
                String.format("Cannot Find Expense By Id %s", expense.getId())));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());

        expenseRepository.save(expense);
    }
}
