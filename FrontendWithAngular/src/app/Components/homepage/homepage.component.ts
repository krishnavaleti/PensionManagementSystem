import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private router: Router,private _loginservice:LoginService) { }

  ngOnInit(): void {
  }

  isLoggedIn() {
    return this._loginservice.isLoggedIn();
  }

  logoutUser()
  {
    this._loginservice.removeToken();
    localStorage.clear();
    location.reload();
  }



}
