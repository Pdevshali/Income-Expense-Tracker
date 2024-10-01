package com.pdevCode.Expense_Income_tracker.controller;

import com.pdevCode.Expense_Income_tracker.dto.ExpenseDto;
import com.pdevCode.Expense_Income_tracker.entities.Expense;
import com.pdevCode.Expense_Income_tracker.services.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping()
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto expenseDto){
        Expense createdExpense = expenseService.postExpense(expenseDto);
        if(createdExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // GET: Retrieve all expenses
    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> allExpenses = expenseService.getAllExpense();
        if (allExpenses != null && !allExpenses.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allExpenses);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Expense expense = expenseService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(expense);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto){
        try {
            return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        try{
            expenseService.deleteExpenses(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
