package com.pdevCode.Expense_Income_tracker.services.income;

import com.pdevCode.Expense_Income_tracker.dto.IncomeDto;
import com.pdevCode.Expense_Income_tracker.entities.Income;

import java.util.List;

public interface IncomeService {

    public Income postIncome(IncomeDto incomeDto);

    public List<IncomeDto> getAllIncomes();

    public Income updateIncome(Long id, IncomeDto incomeDto);

    public IncomeDto getById(Long id);

    public void deleteIncome(Long id);




    }
