package com.pdevCode.Expense_Income_tracker.services.stats;

import com.pdevCode.Expense_Income_tracker.dto.GraphDto;
import com.pdevCode.Expense_Income_tracker.dto.StatsDto;

public interface StatsService {

    GraphDto getChartData();

    StatsDto getStats();


    }
