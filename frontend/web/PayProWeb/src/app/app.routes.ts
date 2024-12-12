import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {OraganisationPageComponent} from './pages/organanisaation/oraganisation-page/oraganisation-page.component';
import {UserDetailComponent} from './pages/user-detail/user-detail.component';
import {MainComponent} from './pages/main/main.component';
import {SingupComponent} from './pages/singup/singup.component';
import {authGuard} from './guard/auth.guard';
import {LoginComponent} from './pages/login/login.component';
import {GeneralComponent} from './pages/general/general.component';
import {AdminKernelComponent} from './pages/admin-kernel/admin-kernel.component';
import {AdminComponent} from './pages/admin/admin.component';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'general',
    pathMatch: "full"
  },
  {
    path: 'signup',
    component:SingupComponent
  },
  {
    path: 'login',
    component:LoginComponent
  },
  {
    path:'general',
    component:AdminKernelComponent,
    children:[
      {
        path: 'site',
        component:GeneralComponent
      },
      {
        path: 'administration',
        component:AdminComponent
      }

    ]
  },
  {
    path: 'main',
    component:MainComponent,
    canActivate: [authGuard],
    children:[

      {
        path: '',
        redirectTo: 'organisation',
        pathMatch: "full"
      },
      {
        path: 'home/:id',
        component: HomePageComponent,
      },
      {
        path: 'organisation/:id',
        component:OraganisationPageComponent
      },
      {
        path: 'user/:id',
        component:UserDetailComponent
      }
    ]

  }


];
