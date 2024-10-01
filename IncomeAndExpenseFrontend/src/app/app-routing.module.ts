import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpenseComponent } from './components/expense/expense.component';
import { IncomeComponent } from './components/income/income.component';
import { UpdateExpenseComponent } from './components/update-expense/update-expense.component';
import { UpdateIncomeComponent } from './components/update-income/update-income.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {path:"expense", component:ExpenseComponent},
  {path:"income", component:IncomeComponent},
  {path:"expense/:id/edit", component:UpdateExpenseComponent},
  {path:"income/:id/edit", component:UpdateIncomeComponent},
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },  // Redirect root path to /dashboard
  { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
