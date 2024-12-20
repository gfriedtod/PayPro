import {Component, inject, signal, WritableSignal} from '@angular/core';
import {HlmH1Directive, HlmH3Directive} from '../../components/lib/ui-typography-helm/src';
import {
  HlmCardContentDirective, HlmCardDescriptionDirective,
  HlmCardDirective,
  HlmCardFooterDirective,
  HlmCardHeaderDirective
} from '../../components/lib/ui-card-helm/src';
import {HlmIconComponent} from '../../components/lib/ui-icon-helm/src';
import {HlmButtonDirective} from '../../components/lib/ui-button-helm/src';
import {provideIcons} from '@ng-icons/core';
import {lucideChevronsRight, lucideChevronsRightLeft, lucideClock, lucidePlus} from '@ng-icons/lucide';
import {HlmInputDirective} from '../../components/lib/ui-input-helm/src';
import {SiteCardComponent} from './site-card/site-card.component';
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {HlmLabelDirective} from '../../components/lib/ui-label-helm/src';
import {
  HlmSheetComponent,
  HlmSheetContentComponent,
  HlmSheetDescriptionDirective, HlmSheetFooterComponent, HlmSheetHeaderComponent, HlmSheetTitleDirective
} from '../../components/lib/ui-sheet-helm/src';
import {HlmSpinnerComponent} from '../../components/lib/ui-spinner-helm/src';
import {OrganisationService} from '../../services/organisation/organisation.service';
import {UserDto} from '../../model/UserDto';
import {AdminDto} from '../../model/AdminDto';
import {OrganisationDto, SpaceDto} from '../../model/OrganisationDto';
import {v4} from 'uuid';
import {ToastComponent} from '../../components/toast/toast.component';
import {timer} from 'rxjs';

@Component({
  selector: 'app-general',
  standalone: true,
  imports: [
    HlmH1Directive,
    HlmH3Directive,
    HlmCardDirective,
    HlmCardHeaderDirective,
    HlmIconComponent,
    HlmCardFooterDirective,
    HlmCardContentDirective,
    HlmButtonDirective,
    HlmCardDescriptionDirective,
    HlmInputDirective,
    SiteCardComponent,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    FormsModule,
    HlmLabelDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    HlmSpinnerComponent,
    ReactiveFormsModule,
    ToastComponent
  ],
  templateUrl: './general.component.html',
  styleUrl: './general.component.css',
  providers: [
    provideIcons({
      lucidePlus,
      lucideClock,
      lucideChevronsRightLeft,
      lucideChevronsRight
    })
  ]
})
export class GeneralComponent {
  loading = signal(false);
  site: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    date: new FormControl(new Date().toISOString(), [Validators.required]),
  });

  organisation : WritableSignal<OrganisationDto[]> =signal([])
  loadingOrganisation = signal(false)
  user: WritableSignal<AdminDto> = signal({} as AdminDto);

  organisationService = inject(OrganisationService);
  visibleToast = signal<boolean>(false);
  toastState = signal<boolean>(true);

  async fetchOrganisationBySpaceId(user:AdminDto) {

    this.loadingOrganisation.set(true);



    (await this.organisationService.fetchOrganisationBySpaceId(user.space.id))
      .subscribe(
        {
          next: (data) => {
            this.organisation.set(data);
          },
          error: (error) => {
            console.error(error)
            this.toastState.set(false);
            this.visibleToast.set(true);
            timer(1000).subscribe(() => this.visibleToast.set(false));
            this.fetchOrganisationBySpaceId(user)

          },
          complete: () => {
            this.loadingOrganisation.set(false);
            this.loading.set(false);
            this.toastState.set(true);
            this.visibleToast.set(true);
            timer(1000).subscribe(() => this.visibleToast.set(false));
          }

        }
      );[]

  }

  ngOnInit() {
     this.user.set(JSON.parse(localStorage.getItem('user')!) as AdminDto);
     if(this.user().role.name == 'ADMIN'){
       this.organisation.set(this.user().adminRows!.map(row => row.organisation));
     }else{
       this.fetchOrganisationBySpaceId(this.user());

     }
  }

  async submitSite() {
    const orgId = v4();
    if (this.site.valid) {
      this.loading.set(true);
      let site  = signal<OrganisationDto>({} as OrganisationDto)
      site().id = orgId;
      site().name = this.site.value.name;
      site().createdAt = Date.parse(this.site.value.date).toString();
      site().departments = [];
      site().space = {id: this.user().space.id} as SpaceDto;
      site().adminRows = [
        {
          organisation: {id: orgId} as OrganisationDto,
          admin: JSON.parse(localStorage.getItem('user')!)
        }
      ];


      (await this.organisationService.saveOrganisation(site())).subscribe({
        next: (data) => {
          this.organisation().push(data);
        },
        error: (error) => {
          console.error(error);
          this.loading.set(false);
          this.visibleToast.set(true);
          this.toastState.set(false);
          timer(1000).subscribe(() => this.visibleToast.set(false));
        },
        complete: () => {
          this.loading.set(false)
          this.visibleToast.set(true);
          this.toastState.set(true);
          timer(1000).subscribe(() => this.visibleToast.set(false));
        }
      });
    }


  }
}
