import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from '../../services/expense/expense.service';
import { IncomeService } from '../../services/income/income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrl: './income.component.scss'
})
export class IncomeComponent {


  incomeForm!: FormGroup;

  listOfCategory: any[] =[
   "Salary",
  "Freelance",
  "Investment",
  "Rental Income",
  "Gifts",
  "Dividends",
  "Side Business",
  "Other"];
  
    incomes:any;

    constructor(private fb: FormBuilder,
      private incomeService: IncomeService,
      private message: NzMessageService,
      private router: Router
    ){}
  
    ngOnInit(){
      //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
      //Add 'implements OnInit' to the class.
      this.getAllIncomes();
      this.incomeForm = this.fb.group({
        title: [null, Validators.required],
        amount: [null, Validators.required],
        date: [null, Validators.required],
        category: [null, Validators.required],
        description: [null, Validators.required],
      })
    }


    submitForm() {
      this.incomeService.postIncome(this.incomeForm.value).subscribe(res=>{
        this.message.success("Income successfully posted", {nzDuration:5000});
        this.getAllIncomes();
      },error => {
        this.message.error("Error while posting income", {nzDuration:5000});
      })
      }

      getAllIncomes(){
        this.incomeService.getAllIncomes().subscribe(res=>{
          this.incomes = res;
          // console.log(this.incomes);
        })
      }

      updateIncome(id:number){
        this.router.navigateByUrl(`income/${id}/edit`);
      }

      deleteIncome(id:number){
        this.incomeService.deleteIncome(id).subscribe(res=>{
          this.message.success("income deleted successfully..");   
          // upadate the getAllIncomes
          this.getAllIncomes();
        }, error=>{
          this.message.error("Error while deleting income", { nzDuration:5000});
        })
  
      }
}
