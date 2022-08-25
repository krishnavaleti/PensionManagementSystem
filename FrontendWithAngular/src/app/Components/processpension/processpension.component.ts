import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProcessPensionInput } from 'src/app/Model/process-pension-input';
import { PensiondetailsService } from 'src/app/pensiondetails.service';

@Component({
  selector: 'app-processpension',
  templateUrl: './processpension.component.html',
  styleUrls: ['./processpension.component.css']
})
export class ProcesspensionComponent implements OnInit {
  processpensionInput={
    aadhaarNumber:""
   }
  
   bankservicecharge=0;
   pensionamount=0.0;
   isShown: boolean = false;
   count:number=0;
   error = false;
   errorMessage = '';

  constructor(private route:Router,private _processpension:PensiondetailsService) { }

  ngOnInit(): void {
  }
  getProcessPensionDetails(){
    const processpensiondata=new ProcessPensionInput();
    processpensiondata.aadhaarNumber=this.processpensionInput.aadhaarNumber;
    this.getProcessPensionData(processpensiondata);

  }
  getProcessPensionData(processpensiondata: any){
    if(processpensiondata.aadhaarNumber===localStorage.getItem('PensionDetailsAadhar')){
    this._processpension.getProcessPension(processpensiondata).subscribe(
      (data:any)=>{
        if(data.status="200"){
        this.error = false;
        this.bankservicecharge=data.bankServiceCharge;
        this.pensionamount=data.pensionAmount;
        localStorage.setItem('AadhaarNumber',data.aadhaarNumber);
        localStorage.setItem('AccountNumber',data.accountNumber);
        localStorage.setItem('BankServiceCharge',data.bankServiceCharge);
        localStorage.setItem('PensionAmount',data.pensionAmount);
        if(this.count==0){
          this.isShown = ! this.isShown;
          this.count++;
        }
      }
      }
      ,
    error=>
    { 
      this.error = true;
      this.errorMessage='Unable to Fetch Process Pension Details';
      console.log(error);
      
    }
  );
  }else{
    this.error = true;
      this.errorMessage='Try Again with valid Aadhaar Number !!! Entered Aadhaar Number does not match with details';
  }
}
  

  navigateBack(){
    this.route.navigateByUrl("home");
}
navigatetoPensionDisburse(){
  this.route.navigateByUrl("pensiondisburse");
}

}


