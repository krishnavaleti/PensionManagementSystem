import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate  {

  constructor(private _loginservice:LoginService,private router:Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log("Runninng AUTHENTICAION GUARD..");
    if (!this._loginservice.isLoggedIn()) {
      this.router.navigateByUrl("login");
    }
    return this._loginservice.isLoggedIn();
  }


}
