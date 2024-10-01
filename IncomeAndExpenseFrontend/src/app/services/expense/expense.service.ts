import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

const BASIC_URL = environment.url;

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  [x: string]: any;

  constructor(private http: HttpClient) { }

  postExpense(expenseDto: any):Observable<any>{
   return this.http.post(BASIC_URL+ "api/expense", expenseDto);
  } 
  
  getAllExpenses():Observable<any>{
    return this.http.get(BASIC_URL+ "api/expense/all");
   } 

    // Get expense by ID


    
  // getExpenseById(id: number): Observable<any> {
  //   return this.http.get(BASIC_URL + "api/expense/${id}");
  // }

  deleteExpense(id:number): Observable<any>{
    return this.http.delete(BASIC_URL+ `api/expense/${id}` );
  }

  getAllExpenseById(id:number):Observable<any>{
    return this.http.get(BASIC_URL+ `api/expense/${id}`);
   }

   updateExpense(id:number, expenseDto: any):Observable<any>{
    return this.http.put(BASIC_URL+ `api/expense/${id}`, expenseDto);
   }
}
