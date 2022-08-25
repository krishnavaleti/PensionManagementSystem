import { Bankdetails } from "./bankdetails";
export class Pensiondetails {
    constructor(public aadhaarNumber: string, public name: string,public dateOfBirth:string,
        public pan:string,public salaryEarned:Number,public allowances:Number,public bankDetails:Bankdetails) { }
}
