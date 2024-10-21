import { Component } from '@angular/core';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {HlmIconComponent} from '@spartan-ng/ui-icon-helm';
import {lucideArrowRight, lucideUser, lucideWorkflow} from '@ng-icons/lucide';
import {provideIcons} from '@ng-icons/core';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    HlmButtonDirective,
    HlmIconComponent,
    HlmButtonDirective,
    HlmIconComponent
  ],
  providers: [
    provideIcons({
      lucideArrowRight,
      lucideUser,
      lucideWorkflow
    })
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

}
