import { Injectable } from '@angular/core';
import { User } from './Model/user';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Text } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
    private router: Router) {}

  baseUrl: string = 'http://localhost:8081';

  
  login(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/authenticate`, user,{responseType: 'text'});
  }
  validateToken(token: string): Observable<any>{
    const headers = { 'Authorization': 'Bearer ' + token}
     return this.http.get(`${this.baseUrl}/authorize`,{ headers });

  }
  getToken() {
    console.log(localStorage.getItem("token"));
    return localStorage.getItem("token");
  }
  setSession(token: string) {
    localStorage.setItem('token', token);
  }
  removeToken(){
    localStorage.removeItem('token');
    return true;
  }
  isLoggedIn() {
    return this.getToken() != null;
  }



 
}
