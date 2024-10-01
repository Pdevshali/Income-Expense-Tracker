import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from '../../services/expense/expense.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.scss'] // Correct this to styleUrls (plural)
})
export class ExpenseComponent {




  expenseForm!: FormGroup;

  listOfCategory: any[] =[
    "Education",
    "Groceries",
    "Health",
    "Subcriptions",
    "Takeways",
    "Clothing",
    "Travelling",
    "Other"];
  
    expenses:any; 

  
  constructor(private fb: FormBuilder,
    private expenseService: ExpenseService,
    private message: NzMessageService,
    private router: Router
  ){}

  ngOnInit(){
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getAllExpenses();
    this.expenseForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    })
  }

  submitForm() {
    this.expenseService.postExpense(this.expenseForm.value).subscribe(res=>{
      this.message.success("Successfully posted expense", { nzDuration: 5000});
          this.getAllExpenses();
    }, errror=>{
      this.message.error("Error while posting expense", {nzDuration:5000});
    })
    }

    getAllExpenses(){
      this.expenseService.getAllExpenses().subscribe(res=>{
        this.expenses = res;
        // console.log(this.expenses);
      } )
    }

   

    updateExpense(id:number){
      this.router.navigateByUrl(`/expense/${id}/edit`);
    }

    deleteExpense(id:number){
      this.expenseService.deleteExpense(id).subscribe(res=>{
        this.message.success("Expense deleted successfully..");   
        // upadate the getAllExpenses
        this.getAllExpenses();
      }, error=>{
        this.message.error("Error while deleting expense", { nzDuration:5000});
      })

    }

}
