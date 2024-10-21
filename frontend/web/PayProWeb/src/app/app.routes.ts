import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {OraganisationPageComponent} from './pages/organanisaation/oraganisation-page/oraganisation-page.component';

export const routes: Routes = [

  {
    path: 'home',
    component: HomePageComponent,
  },
  {
    path: '**',
    redirectTo: 'home',
  },
  {
    path: 'organisation',
    component:OraganisationPageComponent
  }
];
