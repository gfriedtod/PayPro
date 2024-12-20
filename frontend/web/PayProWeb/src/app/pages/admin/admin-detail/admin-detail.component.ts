import {Component, computed, OnInit, signal, TrackByFunction, WritableSignal} from '@angular/core';
import {HlmCardDirective, HlmCardImports} from '../../../components/lib/ui-card-helm/src';
import {HlmButtonDirective} from '../../../components/lib/ui-button-helm/src';
import {
  BrnCellDefDirective,
  BrnColumnDefComponent,
  BrnPaginatorDirective,
  BrnTableComponent,
  BrnTableImports,
  PaginatorState,
  useBrnColumnManager
} from '@spartan-ng/ui-table-brain';
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from '@spartan-ng/ui-select-brain';
import {HlmCheckboxComponent} from '../../../components/lib/ui-checkbox-helm/src';
import {HlmIconComponent} from '../../../components/lib/ui-icon-helm/src';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemCheckboxDirective,
  HlmMenuItemCheckComponent,
  HlmMenuItemDirective,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent
} from '../../../components/lib/ui-menu-helm/src';
import {
  HlmSelectContentDirective,
  HlmSelectOptionComponent,
  HlmSelectTriggerComponent,
  HlmSelectValueDirective
} from '../../../components/lib/ui-select-helm/src';
import {HlmSpinnerComponent} from '../../../components/lib/ui-spinner-helm/src';
import {
  HlmTableDirective,
  HlmTableImports,
  HlmTdComponent,
  HlmThComponent
} from '../../../components/lib/ui-table-helm/src';
import {RouterLink} from '@angular/router';
import {TitleCasePipe} from '@angular/common';
import {copy} from '../../../environement/env';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';
import {DepartementDto} from '../../../model/DepartementDto';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {OrganisationService} from '../../../services/organisation/organisation.service';
import {AdminDto, OrganisationDto} from '../../../model/AdminDto';
import {provideIcons} from '@ng-icons/core';
import {lucideMoreHorizontal, lucidePlus} from '@ng-icons/lucide';
import {HlmInputDirective} from '../../../components/lib/ui-input-helm/src';
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';
import {HlmLabelDirective} from '../../../components/lib/ui-label-helm/src';
import {
  HlmSheetComponent,
  HlmSheetContentComponent,
  HlmSheetDescriptionDirective,
  HlmSheetFooterComponent,
  HlmSheetHeaderComponent,
  HlmSheetTitleDirective
} from '../../../components/lib/ui-sheet-helm/src';
import {AdminRowService} from '../../../services/admin-row/admin-row.service';

@Component({
  selector: 'app-Organisation-detail',
  standalone: true,
  imports: [
    HlmCardDirective,
    HlmCardImports,
    HlmButtonDirective,
    BrnCellDefDirective,
    BrnColumnDefComponent,
    BrnPaginatorDirective,
    BrnSelectComponent,
    BrnSelectContentComponent,
    BrnSelectValueComponent,
    BrnTableComponent,
    HlmCheckboxComponent,
    HlmIconComponent,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmSelectContentDirective,
    HlmSelectOptionComponent,
    HlmSelectTriggerComponent,
    HlmSelectValueDirective,
    HlmSpinnerComponent,
    HlmTableDirective,
    HlmTdComponent,
    HlmThComponent,
    RouterLink,
    TitleCasePipe,
    BrnTableImports,
    HlmTableImports,
    FormsModule,
    BrnMenuTriggerDirective,
    HlmInputDirective,
    HlmMenuItemCheckComponent,
    HlmMenuItemCheckboxDirective,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    HlmLabelDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    ReactiveFormsModule
  ],
  providers: [
    provideIcons({
      lucideMoreHorizontal,
      lucidePlus
    })
  ],
  templateUrl: './Admin-detail.component.html',
  styleUrl: './Admin-detail.component.css'
})
export class AdminDetailComponent implements OnInit {

  organisation: WritableSignal<OrganisationDto[]> = signal([])
  allOrganisation: WritableSignal<OrganisationDto[]> = signal([])
  adminShared: WritableSignal<AdminDto> = signal({} as AdminDto)
  user: WritableSignal<AdminDto> = signal({} as AdminDto)
  orgFrom: FormGroup = new FormGroup({
    organisation: new FormControl('ALL', [Validators.required]),

  });
  visibleToast = signal(false);
  toastState = signal(true);
  departmentDtos = signal<DepartementDto[]>([]);
  loading = signal(false)
  OrganisationFrom: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    department: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required]),
    status: new FormControl('Organisation', [Validators.required]),
    gender: new FormControl('', [Validators.required]),
    displayName: new FormControl('', [Validators.required]),
    cni: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    dateOfBirth: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
  });
  selectedOrganisation!: OrganisationDto;
  protected readonly _rawFilterInput = signal('');
  protected readonly _emailFilter = signal('');
  protected readonly _availablePageSizes = [5, 10, 20, 10000];
  protected readonly _pageSize = signal(this._availablePageSizes[0]);
  protected readonly _brnColumnManager = useBrnColumnManager({
    status: {visible: true, label: 'Name'},

    // departement: { visible: true, label: 'Departement' },
  });
  protected readonly _allDisplayedColumns = computed(() => [
    'select',
    ...this._brnColumnManager.displayedColumns(),
    'actions',
  ]);
  protected readonly _allFilteredPaginatedOrganisationDtosSelected = computed(() =>
    this._filteredSortedPaginatedOrganisationDtos().every((OrganisationDto) => this._selected().includes(OrganisationDto)),
  );
  protected readonly _checkboxState = computed(() => {
    const noneSelected = this._selected().length === 0;
    const allSelectedOrIndeterminate = this._allFilteredPaginatedOrganisationDtosSelected() ? true : 'indeterminate';
    return noneSelected ? false : allSelectedOrIndeterminate;
  });
  protected readonly copy = copy;
  private readonly _debouncedFilter = toSignal(toObservable(this._rawFilterInput).pipe(debounceTime(300)));
  private readonly _displayedIndices = signal({start: 0, end: 0});
  private readonly _selectionModel = new SelectionModel<OrganisationDto>(true);
  protected readonly _selected = toSignal(this._selectionModel.changed.pipe(map((change) => change.source.selected)), {
    initialValue: [],
  });
  private readonly _OrganisationDtos = this.organisation;
  private readonly _filteredOrganisationDtos = computed(() => {
    const emailFilter = this._emailFilter()?.trim()?.toLowerCase();
    if (emailFilter && emailFilter.length > 0) {
      return this._OrganisationDtos().filter((u) => u.name.toLowerCase().includes(emailFilter));
    }
    return this._OrganisationDtos();
  });
  protected readonly _totalElements = computed(() => this._filteredOrganisationDtos().length);
  private readonly _emailSort = signal<'ASC' | 'DESC' | null>(null);
  protected readonly _filteredSortedPaginatedOrganisationDtos = computed(() => {
    const sort = this._emailSort();
    const start = this._displayedIndices().start;
    const end = this._displayedIndices().end + 1;
    const OrganisationDtos = this._filteredOrganisationDtos();
    if (!sort) {
      return OrganisationDtos.slice(start, end);
    }
    return [...OrganisationDtos]
      .sort((p1, p2) => (sort === 'ASC' ? 1 : -1) * p1.name.localeCompare(p2.name))
      .slice(start, end);
  });

  constructor(private organisationService: OrganisationService, private adminService: AdminRowService) {
  }

  async ngOnInit() {

    this.loading.set(true);
    this.adminShared.set(JSON.parse(localStorage.getItem('adminShared')!) as AdminDto);
    this.user.set(JSON.parse(localStorage.getItem('user')!) as AdminDto);

    (await this.organisationService.fetchOrganisationBySpaceId(this.user().space.id)).subscribe({
      next: (data) => {
        this.allOrganisation.set(data);
        this.loading.set(false)
      }
    })


    this.organisation.set(this.adminShared().adminRows!.map(row => row.organisation))
    // (await this.OrganisationService.fetchOrganisationByUserId(this.user().space.id)).subscribe(
    //   {
    //     next: (data) => {
    //       this.organisation.set(data);
    //     },
    //     complete: () => {
    //       this.loading.set(false);
    //     },
    //   }
    // );

  }

  sortOrganisation(gender: string) {
    return this.organisation()?.filter((u) => u.name == gender).length
  }

  saveOrganisation() {
    console.log(this.selectedOrganisation)

    if (this.orgFrom.valid) {
      this.loading.set(true)
      this.adminService.save({
        admin: this.adminShared(),
        organisation: {id: this.orgFrom.value.organisation} as OrganisationDto
      }).subscribe({
        next: (data) => {
          this.loading.set(false)
          this.visibleToast.set(true)
          this.toastState.set(true)
          this.organisation.update(val => [...val, this.organisation().filter(org => org.id == data.organisation.id)![0]]);
          console.log(this.organisation())
          this.user().adminRows?.push({
            id: 0,
            organisation: this.organisation().find(
              org => org.id == data.organisation.id)!,
            admin: {} as AdminDto
          });
          localStorage.setItem('user', JSON.stringify(this.user()))
          this.orgFrom.reset()

        }

      });
    }

  }

  save(element: any) {

  }

  saveAdmin() {

  }

  protected readonly _isOrganisationDtoSelected = (OrganisationDto: OrganisationDto) => this._selectionModel.isSelected(OrganisationDto);

  protected readonly _trackBy: TrackByFunction<OrganisationDto> = (_: number, p: OrganisationDto) => p.id;

  protected readonly _onStateChange = ({startIndex, endIndex}: PaginatorState) => {
    this._displayedIndices.set({start: startIndex, end: endIndex});
  };

  protected toggleOrganisationDto(OrganisationDto: OrganisationDto) {
    this._selectionModel.toggle(OrganisationDto);
  }

  protected handleHeaderCheckboxChange() {
    const previousCbState = this._checkboxState();
    if (previousCbState === 'indeterminate' || !previousCbState) {
      this._selectionModel.select(...this._filteredSortedPaginatedOrganisationDtos());
    } else {
      this._selectionModel.deselect(...this._filteredSortedPaginatedOrganisationDtos());
    }
  }

  protected handleEmailSortChange() {
    const sort = this._emailSort();
    if (sort === 'ASC') {
      this._emailSort.set('DESC');
    } else if (sort === 'DESC') {
      this._emailSort.set(null);
    } else {
      this._emailSort.set('ASC');
    }
  }
}
