import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from '../../services/income/income.service';

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrl: './update-income.component.scss'
})
export class UpdateIncomeComponent {

  incomeForm!: FormGroup;
  listOfCategory: any[] = [
   "Salary",
  "Freelance",
  "Investment",
  "Rental Income",
  "Gifts",
  "Dividends",
  "Side Business",
  "Other"
  ];

  incomes: any;
  id!: number; // Initialized later after fetching from route params

  constructor(
    private fb: FormBuilder,
    private incomeService: IncomeService,
    private message: NzMessageService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.incomeForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    });
    
    // Fetch the 'id' from route params
    this.activatedRoute.params.subscribe(params => {
      this.id = +params['id']; // '+' converts the string to a number
      this.getExpenseById();
    });

  }

  getExpenseById() {
    this.incomeService.getAllIncomeById(this.id).subscribe(res => {
      this.incomeForm.patchValue(res);
    }, error => {
      this.message.error("Something went wrong..", { nzDuration: 5000 });
    });
  }


  submitForm() {
    this.incomeService.updateIncome(this.id, this.incomeForm.value).subscribe(res => {
      this.message.success("Expense updated successfully", { nzDuration: 5000 });
      // send user to expense page
      this.router.navigateByUrl("/income");
    },error =>{
      this.message.error("Error while updating expense..", { nzDuration: 5000 });
    });
  }

}
