import { Component } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-main',
  standalone: true,
    imports: [
        NavBarComponent,
        RouterOutlet
    ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
