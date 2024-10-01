import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

const BASIC_URL = environment.url;

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  constructor(private http: HttpClient) { }

  postIncome(incomeDto: any): Observable<any>{
    return this.http.post(BASIC_URL + "api/income",  incomeDto);
  }

  getAllIncomes(): Observable<any>{
    return this.http.get(BASIC_URL  +"api/income/all");
  }

  deleteIncome(id:number): Observable<any>{
    return this.http.delete(BASIC_URL+ `api/income/${id}` );
  }


  getAllIncomeById(id:number):Observable<any>{
    return this.http.get(BASIC_URL+ `api/income/${id}`);
   }

   updateIncome(id:number, incomeDto: any):Observable<any>{
    return this.http.put(BASIC_URL+ `api/income/${id}`, incomeDto);
   }
}
