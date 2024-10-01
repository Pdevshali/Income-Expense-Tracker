import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { StatsService } from '../../services/stats/stats.service';
import { Chart } from 'chart.js/auto';
import { CategoryScale } from 'chart.js/auto';

Chart.register(CategoryScale);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']  // Fixed 'styleUrl' to 'styleUrls'
})
export class DashboardComponent implements OnInit {

  stats: any;

  expenses: any[]; // Changed to any[] for better type definition
  incomes: any[];  // Changed to any[] for better type definition

  gridStyle = {
    width: '25%',
    textAlign: 'center'
  };

  @ViewChild('incomeLineChartRef') private incomeLineChartRef!: ElementRef;  // Added non-null assertion
  @ViewChild('expenseLineChartRef') private expenseLineChartRef!: ElementRef;  // Added non-null assertion

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    this.getStats();
    this.getChartData();
  }

  createLineChart() { // Fixed method name from 'createLieChart' to 'createLineChart'
    const incomeCtx = this.incomeLineChartRef.nativeElement.getContext('2d');

    new Chart(incomeCtx, {
      type: 'line',
      data: {
        labels: this.incomes.map(income => income.date),
        datasets: [{
          label: 'Income',
          data: this.incomes.map(income => income.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80, 200, 120)',
          borderColor: 'rgb(0, 100, 0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    const expenseCtx = this.expenseLineChartRef.nativeElement.getContext('2d');

    new Chart(expenseCtx, {
      type: 'line',
      data: {
        labels: this.expenses.map(expense => expense.date),  // Fixed variable name from 'income' to 'expense'
        datasets: [{
          label: 'Expense',
          data: this.expenses.map(expense => expense.amount),  // Fixed variable name from 'income' to 'expense'
          borderWidth: 1,
          backgroundColor: 'rgb(255, 0, 0)',
          borderColor: 'rgb(255, 0, 0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  getStats() {
    this.statsService.getStats().subscribe(res => {
      this.stats = res;
    }, error => {
      console.error("Error fetching stats: ", error);
    });
  }

  getChartData() {
    this.statsService.getChart().subscribe(res => {
      if (res.expenseList != null && res.incomeList != null) {
        this.incomes = res.incomeList;  // Changed to use 'incomeList' instead of 'expenseList'
        this.expenses = res.expenseList; // Ensure expenses are assigned correctly
        console.log(res);
        this.createLineChart(); // Fixed method name to 'createLineChart'
      }
    });
  }
}
