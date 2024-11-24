import {Component, Input} from '@angular/core';
import {HlmIconComponent} from '../../../components/lib/ui-icon-helm/src';
import {
  HlmCardContentDirective, HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective
} from '../../../components/lib/ui-card-helm/src';
import {OrganisationDto} from '../../../model/OrganisationDto';
import {DatePipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-site-card',
  standalone: true,
  imports: [
    HlmIconComponent,
    HlmCardContentDirective,
    HlmCardFooterDirective,
    HlmCardHeaderDirective,
    HlmCardDirective,
    DatePipe,
    RouterLink
  ],
  templateUrl: './site-card.component.html',
  styleUrl: './site-card.component.css'
})
export class SiteCardComponent {
  @Input() item!: OrganisationDto;

}
