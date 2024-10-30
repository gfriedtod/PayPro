import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {provideHttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HlmButtonDirective, HlmButtonDirective, NavBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers:[

  ]
})
export class AppComponent {
  title = 'PayProWeb';
}
