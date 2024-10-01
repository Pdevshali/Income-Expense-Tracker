package com.pdevCode.Expense_Income_tracker.services.expense;


import com.pdevCode.Expense_Income_tracker.dto.ExpenseDto;
import com.pdevCode.Expense_Income_tracker.entities.Expense;

import java.util.List;

public interface ExpenseService {

    public Expense postExpense(ExpenseDto expenseDto);

    public List<Expense> getAllExpense();

    public Expense getById(Long id);

    public Expense updateExpense(Long id, ExpenseDto expenseDto);

    public void deleteExpenses(Long id);






    }
