import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login.service';
import { User } from 'src/app/Model/user';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userModel: User = new User("", "");
  errorMessage = '';
  tokenvalue:any;
  error = false;

  constructor(private _loginService:LoginService,private router: Router) { }
  
  ngOnInit() : void{
   
  }
 
  clickLoginBtn(){
    this._loginService.login(this.userModel).subscribe(
      (data:any)=>{
      this.error = false;
      this._loginService.setSession(data);
      this.tokenvalue=this._loginService.getToken();
    this._loginService.validateToken(this.tokenvalue).subscribe(
       data=>{
        console.log(data);
        if(data==true){
         this.router.navigateByUrl("/home");
        }else{
          this.errorMessage='Token Authentication Failed';
        }
      } 
    ) 
   },
   (error) => {
      this.error = true;
      this.errorMessage = 'Internal Server';  
  }
  );
}

}
  
  

