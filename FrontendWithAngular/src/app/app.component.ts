import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PensionManagementAngular';
  constructor(private router: Router,
    private _loginservice:LoginService) { }


  isLoggedIn() {
    return this._loginservice.isLoggedIn();
  }
}
