package com.pdevCode.Expense_Income_tracker.services.income;

import com.pdevCode.Expense_Income_tracker.dto.IncomeDto;
import com.pdevCode.Expense_Income_tracker.entities.Income;
import com.pdevCode.Expense_Income_tracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    IncomeRepository incomeRepository;



    public Income postIncome(IncomeDto incomeDto){
        return saveOrUpdateIncome(new Income(), incomeDto);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setAmount(incomeDto.getAmount());
        income.setCategory(incomeDto.getCategory());
        income.setDescription(incomeDto.getDescription());

        return incomeRepository.save(income);

    }

    public List<IncomeDto> getAllIncomes(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    // Whenever we update or save we use Income means we use database entity to update or saving
     public Income updateIncome(Long id, IncomeDto incomeDto){
         Optional<Income> optionalIncome = incomeRepository.findById(id);
         if(optionalIncome.isPresent()){
             return saveOrUpdateIncome(optionalIncome.get(), incomeDto);
         }
         else {
             throw new EntityNotFoundException("Income is not found with id: "+id);
         }
     }

     // Whenever we get something we use IncomeDto not Income
     public IncomeDto getById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return optionalIncome.get().getIncomeDto();
        }else {
            throw new EntityNotFoundException("Income not found with id: "+id);
        }
    }

   public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("income is not found with id: "+id);
        }
   }
}
