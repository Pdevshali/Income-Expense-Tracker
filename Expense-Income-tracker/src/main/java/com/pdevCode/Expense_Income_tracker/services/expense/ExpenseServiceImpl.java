package com.pdevCode.Expense_Income_tracker.services.expense;


import com.pdevCode.Expense_Income_tracker.dto.ExpenseDto;
import com.pdevCode.Expense_Income_tracker.entities.Expense;
import com.pdevCode.Expense_Income_tracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    ExpenseRepository repository;

    public Expense postExpense(ExpenseDto expenseDto){
        return saveOrUpdate(new Expense(), expenseDto);
    }

    public Expense saveOrUpdate(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());

        return repository.save(expense);
    }

    public List<Expense> getAllExpense(){
//        return repository.findAll();

        // another way in this we sort them by date newest to appear first
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getById(Long id){
        Optional<Expense> optionalExpense = repository.findById(id);
        if(optionalExpense.isPresent()){
            return optionalExpense.get();
        }else {
            throw new EntityNotFoundException("The Expense not found with id: " +id);
        }
    }

    public Expense updateExpense(Long id, ExpenseDto expenseDto){
        Optional<Expense> optionalExpense = repository.findById(id);
        if (optionalExpense.isPresent()){
            return saveOrUpdate(optionalExpense.get(), expenseDto);
        }else {
            throw new EntityNotFoundException("Expense not found with id: "+id);
        }
    }

    public void deleteExpenses(Long id){
        Optional<Expense> optionalExpense = repository.findById(id);
        if (optionalExpense.isPresent()){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException("The expense not found with id: "+id);
        }
    }
}
