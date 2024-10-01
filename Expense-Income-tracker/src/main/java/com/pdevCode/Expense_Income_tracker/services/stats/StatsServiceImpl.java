package com.pdevCode.Expense_Income_tracker.services.stats;

import com.pdevCode.Expense_Income_tracker.dto.GraphDto;
import com.pdevCode.Expense_Income_tracker.dto.StatsDto;
import com.pdevCode.Expense_Income_tracker.entities.Expense;
import com.pdevCode.Expense_Income_tracker.entities.Income;
import com.pdevCode.Expense_Income_tracker.repository.ExpenseRepository;
import com.pdevCode.Expense_Income_tracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StatsServiceImpl implements StatsService{


    @Autowired
   private final IncomeRepository incomeRepository;

    @Autowired
    private final ExpenseRepository expenseRepository;

    public StatsServiceImpl(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }


    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);
        GraphDto graphDto = new GraphDto();

        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);
        List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);

        System.out.println("Expenses: " + expenses);
        System.out.println("Income: " + incomes);

        graphDto.setExpenseList(expenses);
        graphDto.setIncomeList(incomes);

        return graphDto;
    }

    public StatsDto getStats(){
        Double totalIncome = incomeRepository.sumAllAmounts();
        Double totalExpense = expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDto statsDto = new StatsDto();
        statsDto.setIncome(totalIncome);
        statsDto.setExpense(totalExpense);

        optionalIncome.ifPresent(statsDto::setLatestIncome);
        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();


        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ?minIncome.getAsDouble() : null);

        return statsDto;
    }

}
