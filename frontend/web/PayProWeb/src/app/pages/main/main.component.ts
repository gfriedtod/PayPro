import { Component } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {RouterOutlet} from "@angular/router";
import {provideIcons} from '@ng-icons/core';
import {lucideArrowRight, lucideLogOut, lucideUser, lucideWorkflow} from '@ng-icons/lucide';

@Component({
  selector: 'app-main',
  standalone: true,
    imports: [
        NavBarComponent,
        RouterOutlet
    ],
  providers: [
    provideIcons(
      {
        lucideArrowRight,
        lucideLogOut,
        lucideUser,
        lucideWorkflow
      },
    )
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
