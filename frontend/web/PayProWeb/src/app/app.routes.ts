import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {OraganisationPageComponent} from './pages/organanisaation/oraganisation-page/oraganisation-page.component';
import {UserDetailComponent} from './pages/user-detail/user-detail.component';
import {MainComponent} from './pages/main/main.component';
import {SingupComponent} from './pages/singup/singup.component';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'signup',
    pathMatch: "full"
  },
  {
    path: 'signup',
    component:SingupComponent
  },
  {
    path: 'main',
    component:MainComponent,
    children:[

      {
        path: '',
        redirectTo: 'home',
        pathMatch: "full"
      },
      {
        path: 'home',
        component: HomePageComponent,
      },
      {
        path: 'organisation',
        component:OraganisationPageComponent
      },
      {
        path: 'users',
        component:UserDetailComponent
      }
    ]

  }


];
