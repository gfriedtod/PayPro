import {Component, OnInit, signal} from '@angular/core';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {HlmIconComponent} from '@spartan-ng/ui-icon-helm';
import {lucideArrowRight, lucideUser, lucideWorkflow} from '@ng-icons/lucide';
import {provideIcons} from '@ng-icons/core';
import {Route, Router, RouterLink, RouterLinkActive} from '@angular/router';
import {of} from 'rxjs';
import {NgClass} from '@angular/common';

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
      link: '/home'
    },
    {
      title: 'Organisation',
      icon: 'lucideWorkflow',
      link: '/organisation'
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
