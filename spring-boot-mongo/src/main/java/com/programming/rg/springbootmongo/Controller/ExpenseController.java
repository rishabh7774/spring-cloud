package com.programming.rg.springbootmongo.Controller;

import com.programming.rg.springbootmongo.Service.ExpenseService;
import com.programming.rg.springbootmongo.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("addExpense")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("getExpense/{ExpenseName}")
    public ResponseEntity<Expense> getExpenseByName(@PathVariable String ExpenseName) {
        return ResponseEntity.ok(expenseService.getExpenseByName(ExpenseName));
    }

    @GetMapping("getExpenses")
    public ResponseEntity<List<Expense>> getAllExpense() {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @DeleteMapping("deleteExpense/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("updateExpense")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) {
        expenseService.updateExpense(expense);
        return ResponseEntity.ok().build();
    }

}
