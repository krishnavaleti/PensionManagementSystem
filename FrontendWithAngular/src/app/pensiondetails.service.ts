import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PensiondetailsService {
  constructor(private http: HttpClient,
    private router: Router,private _loginservice:LoginService) { }

  baseUrl: string = 'http://localhost:8082';
  processUrl:string='http://localhost:8083';
  value:any;
  processpension= {
     aadhaarNumber:'',
     accountNumber:'',
     pensionAmount:'',
     bankServiceCharge:'',

}


  getPensionerDetails(aadharNo:string): Observable<any>{
     this.value= this._loginservice.getToken();
     const headers = { 'Authorization': 'Bearer ' + this.value}
    return this.http.get<any>(`${this.baseUrl}/pensionerDetail/${aadharNo}`,{ headers });
  }
  getProcessPension(processPensionInput:any):Observable<any>{
    return this.http.post(`${this.processUrl}/processPension`,processPensionInput,{headers : new HttpHeaders({ 'Content-Type': 'application/json'})});
  }
  
  getPensionDisbureDetails():Observable<any>{
    
    this.processpension.aadhaarNumber=localStorage.getItem('AadhaarNumber')!;
    this.processpension.accountNumber=localStorage.getItem('AccountNumber')!;
    this.processpension.bankServiceCharge=localStorage.getItem('BankServiceCharge')!;
    this.processpension.pensionAmount=localStorage.getItem('PensionAmount')!;
   return this.http.post(`${this.processUrl}/ProcessPensionTransaction`,this.processpension);
    
  }
}
