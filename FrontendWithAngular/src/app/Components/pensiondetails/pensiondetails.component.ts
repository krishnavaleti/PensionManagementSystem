import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PensiondetailsService } from 'src/app/pensiondetails.service';
import { Pensiondetails } from 'src/app/Model/pensiondetails';
import { Bankdetails } from 'src/app/Model/bankdetails';

@Component({
  selector: 'app-pensiondetails',
  templateUrl: './pensiondetails.component.html',
  styleUrls: ['./pensiondetails.component.css']
})
export class PensiondetailsComponent implements OnInit {


  credentials = {
    aadhaarNumber: ""
  }
  isShown: boolean = false;
  count: number = 0;
  pension: Pensiondetails[] = []
  errorMessage = '';
  error = false;


  constructor(private _pensiondetails: PensiondetailsService, private route: Router) { }

  ngOnInit(): void {
  }
  onSubmit() {

    this._pensiondetails.getPensionerDetails(this.credentials.aadhaarNumber).subscribe(

      (pensiondetail: any) => {
        this.error = false;
        this.pension = [];
        this.pension.push(pensiondetail);
        if (pensiondetail != null) {
          localStorage.setItem('PensionDetailsAadhar', pensiondetail.aadhaarNumber);
          if (this.count == 0) {
            this.isShown = !this.isShown;
            this.count++;
          }
        } else {
          this.error = true;
          this.errorMessage = 'Unable to Fetch Pension Details';
        }
      },
      error => {
        this.error = true;
        this.errorMessage = 'Unable to Fetch Pension Details';
        console.log(error);
      }
    );

  }
  navigateBack() {
    this.route.navigateByUrl("home");

  }

}
