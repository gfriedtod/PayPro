import {Component, OnInit, signal, WritableSignal} from '@angular/core';

import {lucideArrowRight, lucideUser, lucideWorkflow} from '@ng-icons/lucide';
import {provideIcons} from '@ng-icons/core';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {of} from 'rxjs';
import {NgClass} from '@angular/common';
import {HlmIconComponent} from '../lib/ui-icon-helm/src';
import {HlmButtonDirective} from '../lib/ui-button-helm/src';
import {OrganisationDto} from '../../model/OrganisationDto';

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
  organisation : WritableSignal<OrganisationDto> = signal({} as OrganisationDto);
  id: string ='';

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
    let urls = this.router.url.split('/');
    urls.pop()
    return urls.join('/');
  }

  ngOnInit(): void {
    this.id = this.router.url.split('/').pop() ?? '';
  }

  justTest() {
    console.log(this.getCurrentRoute())
  }
}
