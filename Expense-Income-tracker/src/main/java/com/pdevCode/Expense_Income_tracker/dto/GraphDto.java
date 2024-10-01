package com.pdevCode.Expense_Income_tracker.dto;

import com.pdevCode.Expense_Income_tracker.entities.Expense;
import com.pdevCode.Expense_Income_tracker.entities.Income;

import java.util.List;

public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;

    public GraphDto(){

    }

//    public GraphDto(List<Expense> expenseList, List<Income> incomeList) {
//        this.expenseList = expenseList;
//        this.incomeList = incomeList;
//    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }
}
