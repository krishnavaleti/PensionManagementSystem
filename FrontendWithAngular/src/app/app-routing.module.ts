import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { AuthGuardService } from './auth-guard.service';
import { HomepageComponent } from './Components/homepage/homepage.component';
import { PensiondetailsComponent } from './Components/pensiondetails/pensiondetails.component';
import { ProcesspensionComponent } from './Components/processpension/processpension.component';
import { PensiondisburseComponent } from './Components/pensiondisburse/pensiondisburse.component';

const routes: Routes = [
  {path:'',pathMatch: 'full', redirectTo: "/login"},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomepageComponent,canActivate: [AuthGuardService]},
  {path:'pensiondetails',component:PensiondetailsComponent, canActivate:[AuthGuardService]},
  {path:'processpension',component:ProcesspensionComponent, canActivate:[AuthGuardService]},
  {path:'pensiondisburse',component:PensiondisburseComponent, canActivate:[AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
