import {Component, signal, WritableSignal} from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {HlmButtonDirective} from '../../components/lib/ui-button-helm/src';
import {HlmIconComponent} from '../../components/lib/ui-icon-helm/src';
import {OrganisationDto} from '../../model/OrganisationDto';
import {provideIcons} from '@ng-icons/core';
import {lucideArrowRight, lucideLogOut, lucideUser, lucideWorkflow} from '@ng-icons/lucide';

@Component({
  selector: 'app-admin-kernel',
  standalone: true,
  imports: [
    NavBarComponent,
    RouterOutlet,
    HlmButtonDirective,
    HlmIconComponent,
    RouterLink
  ],
  providers: [
    provideIcons(
      {
        lucideArrowRight,
        lucideLogOut,
        lucideUser,
        lucideWorkflow
      },
    ),
  ],
  templateUrl: './admin-kernel.component.html',
  styleUrl: './admin-kernel.component.css'
})
export class AdminKernelComponent {
  currentRoute =signal('');
  organisation : WritableSignal<OrganisationDto> = signal({} as OrganisationDto);
  id: string ='';

  items = [
    {
      title: 'Site',
      icon: 'lucideWorkflow',
      link: '/general/site'
    },
    {
      title: 'Administration',
      icon: 'lucideWorkflow',
      link: '/general/administration'
    }
  ];


  constructor(private  router:Router) {
  }

  getCurrentRoute() {
    let urls = this.router.url.split('/');
    return urls.join('/');
  }

  ngOnInit(): void {
    this.id = this.router.url.split('/').pop() ?? '';
  }

  justTest(item:any) {
    console.log(this.getCurrentRoute())
    console.log(item.link)
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
