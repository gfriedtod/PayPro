import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {OraganisationPageComponent} from './pages/organanisaation/oraganisation-page/oraganisation-page.component';
import {UserDetailComponent} from './pages/user-detail/user-detail.component';

export const routes: Routes = [

  {
    path: 'home',
    component: HomePageComponent,
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: "full"
  },
  {
    path: 'organisation',
    component:OraganisationPageComponent
  },
  {
    path: 'users',
    component:UserDetailComponent
  }
];
