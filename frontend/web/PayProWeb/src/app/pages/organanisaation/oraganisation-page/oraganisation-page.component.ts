import {Component, computed, effect, inject, signal, TrackByFunction, WritableSignal} from '@angular/core';
import {
  BrnTableImports, PaginatorState, useBrnColumnManager
} from "@spartan-ng/ui-table-brain";
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from "@spartan-ng/ui-select-brain";
import {DecimalPipe, TitleCasePipe} from "@angular/common";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {provideIcons} from '@ng-icons/core';
import {lucidePlus, lucideUser, lucideUserMinus, lucideUserPlus,lucideSearch} from '@ng-icons/lucide';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map, timer} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';

import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';

import {DepartementDto} from '../../../model/DepartementDto';
import {DepartementService} from '../../../services/departement/departement.service';
import {HlmCardContentDirective, HlmCardDirective, HlmCardImports} from '../../../components/lib/ui-card-helm/src';
import {HlmIconComponent} from '../../../components/lib/ui-icon-helm/src';
import {HlmInputDirective} from '../../../components/lib/ui-input-helm/src';
import {
  HlmSelectContentDirective, HlmSelectImports,
  HlmSelectOptionComponent,
  HlmSelectValueDirective
} from '../../../components/lib/ui-select-helm/src';
import {HlmScrollAreaModule} from '../../../components/lib/ui-scrollarea-helm/src';
import {HlmButtonDirective} from '../../../components/lib/ui-button-helm/src';
import {
  HlmTableDirective,
  HlmTableImports,
  HlmTdComponent,
  HlmThComponent
} from '../../../components/lib/ui-table-helm/src';
import {HlmCheckboxComponent, HlmCheckboxImports} from '../../../components/lib/ui-checkbox-helm/src';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemImports, HlmMenuLabelComponent,
  HlmMenuSeparatorComponent, HlmMenuStructureImports
} from '../../../components/lib/ui-menu-helm/src';
import {HlmSpinnerComponent} from '../../../components/lib/ui-spinner-helm/src';
import {
  HlmSheetCloseDirective, HlmSheetComponent, HlmSheetContentComponent,
  HlmSheetDescriptionDirective,
  HlmSheetFooterComponent, HlmSheetHeaderComponent, HlmSheetTitleDirective
} from '../../../components/lib/ui-sheet-helm/src';
import {Router} from '@angular/router';
import {v4} from 'uuid';
import {OrganisationDto} from '../../../model/OrganisationDto';
import {ToastComponent} from '../../../components/toast/toast.component';

@Component({
  imports: [
    HlmCardDirective,
    HlmCardContentDirective,
    HlmCardContentDirective,
    HlmIconComponent,
    HlmInputDirective,
    BrnSelectComponent,
    HlmSelectValueDirective,
    HlmSelectContentDirective,
    HlmSelectOptionComponent,
    HlmSelectValueDirective,
    HlmSelectValueDirective,
    HlmSelectContentDirective,
    HlmSelectOptionComponent,
    HlmScrollAreaModule,
    HlmSelectImports,
    HlmSelectValueDirective,
    BrnSelectValueComponent,
    HlmButtonDirective,
    HlmIconComponent,
    HlmIconComponent,
    HlmButtonDirective,
    HlmTableDirective,
    HlmTableImports,
    BrnTableImports,
    HlmTableDirective,
    HlmTableImports,
    HlmThComponent,
    HlmTableImports,
    HlmCheckboxComponent,
    HlmCheckboxImports,
    HlmTdComponent,
    HlmTableImports,
    HlmMenuSeparatorComponent,
    HlmMenuStructureImports,
    HlmMenuGroupComponent,
    HlmMenuItemImports,
    HlmMenuSeparatorComponent,
    HlmMenuStructureImports,
    HlmMenuLabelComponent,
    HlmMenuStructureImports,
    HlmSelectContentDirective,
    HlmSelectImports,
    BrnSelectContentComponent,
    HlmSelectOptionComponent,
    HlmSelectImports,
    FormsModule,
    HlmMenuComponent,
    HlmMenuComponent,
    HlmMenuComponent,
    HlmMenuComponent,
    BrnMenuTriggerDirective,
    DecimalPipe,
    TitleCasePipe,
    HlmCheckboxImports,
    HlmInputDirective,
    HlmCardImports,
    HlmCardDirective,
    HlmTdComponent,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    HlmSheetContentComponent,
    HlmSheetComponent,
    HlmSheetComponent,
    HlmButtonDirective,
    HlmIconComponent,
    HlmSheetContentComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetCloseDirective,
    HlmMenuSeparatorComponent,
    HlmMenuItemDirective,
    HlmMenuItemImports,
    HlmSpinnerComponent,
    ReactiveFormsModule,
    ToastComponent
  ],
  providers: [
    provideIcons({
      lucideUser,
      lucideUserMinus,
      lucideUserPlus,
      lucideSearch,
      lucidePlus
    })
  ],
  selector: 'app-oraganisation-page',
  standalone: true, styleUrl: './oraganisation-page.component.css',
  templateUrl: './oraganisation-page.component.html'
})
export class OraganisationPageComponent {

  departmentDtos = signal<DepartementDto[]>([]);
  router: Router = inject(Router);


  departmentForm = new FormGroup({
    name: new FormControl('',[Validators.required]),
  });
  private id: string = '';

  async ngOnInit()  {
     this.id   = this.router.url.split('/').pop() ?? '';
    this.loading.set(true);
    console.log("hello home");


    (await this.departementService.fetchDepatments(this.id)).subscribe(
      (departements) => {this.departmentDtos.set(departements); this.loading.set(false)},
      (error) => console.error(error)
    )
  }
  protected readonly _rawFilterInput = signal('');
  protected readonly _emailFilter = signal('');
  private readonly _debouncedFilter = toSignal(toObservable(this._rawFilterInput).pipe(debounceTime(300)));

  private readonly _displayedIndices = signal({ start: 0, end: 0 });
  protected readonly _availablePageSizes = [5, 10, 20, 10000];
  protected readonly _pageSize = signal(this._availablePageSizes[0]);

  private readonly _selectionModel = new SelectionModel<DepartementDto>(true);
  protected readonly _isDepartementDtoSelected = (DepartementDto: DepartementDto) => this._selectionModel.isSelected(DepartementDto);
  protected readonly _selected = toSignal(this._selectionModel.changed.pipe(map((change) => change.source.selected)), {
    initialValue: [],
  });

  protected readonly _brnColumnManager = useBrnColumnManager({
    ids: { visible: true, label: 'ids' },
    name: { visible: true, label: 'Name' },

  });
  protected readonly _allDisplayedColumns = computed(() => [
    'select',
    ...this._brnColumnManager.displayedColumns(),
    'actions',
  ]);

  private readonly _DepartementDtos = this.departmentDtos;
  private readonly _filteredDepartementDtos = computed(() => {
    const emailFilter = this._emailFilter()?.trim()?.toLowerCase();
    if (emailFilter && emailFilter.length > 0) {
      return this._DepartementDtos().filter((u) => u.name.toLowerCase().includes(emailFilter));
    }
    return this._DepartementDtos();
  });
  private readonly _emailSort = signal<'ASC' | 'DESC' | null>(null);
  protected readonly _filteredSortedPaginatedDepartementDtos = computed(() => {
    const sort = this._emailSort();
    const start = this._displayedIndices().start;
    const end = this._displayedIndices().end + 1;
    const DepartementDtos = this._filteredDepartementDtos();
    if (!sort) {
      return DepartementDtos.slice(start, end);
    }
    return [...DepartementDtos]
      .sort((p1, p2) => (sort === 'ASC' ? 1 : -1) * p1.name.localeCompare(p2.name))
      .slice(start, end);
  });
  protected readonly _allFilteredPaginatedDepartementDtosSelected = computed(() =>
    this._filteredSortedPaginatedDepartementDtos().every((DepartementDto) => this._selected().includes(DepartementDto)),
  );
  protected readonly _checkboxState = computed(() => {
    const noneSelected = this._selected().length === 0;
    const allSelectedOrIndeterminate = this._allFilteredPaginatedDepartementDtosSelected() ? true : 'indeterminate';
    return noneSelected ? false : allSelectedOrIndeterminate;
  });

  protected readonly _trackBy: TrackByFunction<DepartementDto> = (_: number, p: DepartementDto) => p.id;
  protected readonly _totalElements = computed(() => this._filteredDepartementDtos().length);
  protected readonly _onStateChange = ({ startIndex, endIndex }: PaginatorState) =>
    this._displayedIndices.set({ start: startIndex, end: endIndex });
  loading = signal<boolean>(false);
  visibleToast = signal<boolean>(false);
  toastState = signal<boolean>(true);

  constructor(private departementService: DepartementService) {
    // needed to sync the debounced filter to the name filter, but being able to override the
    // filter when loading new users without debounce
    effect(() => this._emailFilter.set(this._debouncedFilter() ?? ''), { allowSignalWrites: true });
  }

  protected toggleDepartementDto(DepartementDto: DepartementDto) {
    this._selectionModel.toggle(DepartementDto);
  }

  protected handleHeaderCheckboxChange() {
    const previousCbState = this._checkboxState();
    if (previousCbState === 'indeterminate' || !previousCbState) {
      this._selectionModel.select(...this._filteredSortedPaginatedDepartementDtos());
    } else {
      this._selectionModel.deselect(...this._filteredSortedPaginatedDepartementDtos());
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

  async submit() {

    if (this.departmentForm.valid) {
      this.loading.set(true);
      let departement: WritableSignal<DepartementDto>
      departement = signal<DepartementDto>({
        name: this.departmentForm.value.name as string,
        id: v4(),
        createdAt: new Date().toISOString(),
        organisation: {id: this.id} as OrganisationDto,
      });

      (await this.departementService.saveDepartement(departement())).subscribe(
        {
          next: (data) => {
            this.loading.set(false);
            this.departmentForm.reset();
            this.ngOnInit();
          },
          error: () => {
            this.loading.set(false);
            this.visibleToast.set(true);
            this.toastState.set(false);
            timer(1000).subscribe(() => this.visibleToast.set(false));
          },
          complete: () => {
            this.loading.set(false);
            this.visibleToast.set(true);
            this.toastState.set(true);
            timer(1000).subscribe(() => this.visibleToast.set(false));

          }

        }
      )

    }
  }
}
