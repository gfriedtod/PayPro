import {Component, OnInit, signal} from '@angular/core';

import {lucideArrowRight, lucideUser, lucideWorkflow} from '@ng-icons/lucide';
import {provideIcons} from '@ng-icons/core';
import {Route, Router, RouterLink, RouterLinkActive} from '@angular/router';
import {of} from 'rxjs';
import {NgClass} from '@angular/common';
import {HlmIconComponent} from '../lib/ui-icon-helm/src';
import {HlmButtonDirective} from '../lib/ui-button-helm/src';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    HlmButtonDirective,
    HlmIconComponent,
    HlmButtonDirective,
    HlmIconComponent,
    RouterLink,
    NgClass
  ],
  providers: [
    RouterLinkActive,
    RouterLink,
    Router,
    provideIcons({
      lucideArrowRight,
      lucideUser,
      lucideWorkflow
    })
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  currentRoute =signal('');

  items = [
    {
      title: 'Overview',
      icon: 'lucideUser',
      link: '/main/home'
    },
    {
      title: 'Organisation',
      icon: 'lucideWorkflow',
      link: '/main/organisation'
    }
  ];

  protected readonly of = of;

  constructor(private  router:Router) {
  }
  getCurrentRoute() {
    return this.router.url;
  }

  ngOnInit(): void {
  }

  justTest() {
    console.log(this.getCurrentRoute())
  }
}
