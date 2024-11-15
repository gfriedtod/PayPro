import {Component, computed, effect, signal, TrackByFunction} from '@angular/core';
import {
  BrnTableImports, PaginatorState, useBrnColumnManager
} from "@spartan-ng/ui-table-brain";
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from "@spartan-ng/ui-select-brain";
import {DecimalPipe, TitleCasePipe} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {provideIcons} from '@ng-icons/core';
import {lucidePlus, lucideUser, lucideUserMinus, lucideUserPlus,lucideSearch} from '@ng-icons/lucide';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';
import {HlmCardContentDirective, HlmCardDirective, HlmCardImports} from '@spartan-ng/ui-card-helm';
import {HlmIconComponent} from '@spartan-ng/ui-icon-helm';
import {HlmInputDirective} from '@spartan-ng/ui-input-helm';
import {
  HlmSelectContentDirective,
  HlmSelectImports,
  HlmSelectOptionComponent,
  HlmSelectValueDirective
} from '@spartan-ng/ui-select-helm';
import {HlmScrollAreaModule} from '@spartan-ng/ui-scrollarea-helm';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {HlmTableDirective, HlmTableImports, HlmTdComponent, HlmThComponent} from '@spartan-ng/ui-table-helm';
import {HlmCheckboxComponent, HlmCheckboxImports} from '@spartan-ng/ui-checkbox-helm';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent, HlmMenuItemDirective,
  HlmMenuItemImports, HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuStructureImports
} from '@spartan-ng/ui-menu-helm';
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';
import {
  HlmSheetCloseDirective,
  HlmSheetComponent,
  HlmSheetContentComponent, HlmSheetDescriptionDirective, HlmSheetFooterComponent,
  HlmSheetHeaderComponent,
  HlmSheetTitleDirective
} from '@spartan-ng/ui-sheet-helm';
import {DepartementDto} from '../../../model/DepartementDto';
import {DepartementService} from '../../../services/departement/departement.service';
import {HlmSpinnerComponent} from '@spartan-ng/ui-spinner-helm';

@Component({
  selector: 'app-oraganisation-page',
  standalone: true,
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
    HlmSpinnerComponent
  ],
  providers: [
    provideIcons({
      lucideUser,
      lucideUserMinus,
      lucideUserPlus,
      lucideSearch,
      lucidePlus
    })
  ],  templateUrl: './oraganisation-page.component.html',
  styleUrl: './oraganisation-page.component.css'
})
export class OraganisationPageComponent {

  departmentDtos = signal<DepartementDto[]>([]);
  async ngOnInit()  {
    this.loading.set(true);
    console.log("hello home");
    (await this.departementService.fetchDepatments()).subscribe(
      (users) => {this.departmentDtos.set(users); this.loading.set(false)},
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
}
