import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './login.service';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PensiondetailsComponent } from './Components/pensiondetails/pensiondetails.component';
import { HomepageComponent } from './Components/homepage/homepage.component';
import { AuthInterceptorService } from './auth-interceptor.service';
import { ProcesspensionComponent } from './Components/processpension/processpension.component';
import { PensiondetailsService } from './pensiondetails.service';
import { PensiondisburseComponent } from './Components/pensiondisburse/pensiondisburse.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PensiondetailsComponent,
    HomepageComponent,
    ProcesspensionComponent,
    PensiondisburseComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}],
  bootstrap: [AppComponent],
  exports: [AppRoutingModule]
})
export class AppModule { }
