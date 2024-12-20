import {Component, computed, OnInit, signal, TrackByFunction, WritableSignal} from '@angular/core';
import {
  BrnCellDefDirective,
  BrnColumnDefComponent, BrnHeaderDefDirective,
  BrnPaginatorDirective,
  BrnTableComponent, BrnTableImports, PaginatorState, useBrnColumnManager
} from "@spartan-ng/ui-table-brain";
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from "@spartan-ng/ui-select-brain";
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from "@spartan-ng/ui-sheet-brain";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {HlmButtonDirective} from "../../components/lib/ui-button-helm/src";
import {HlmCardDirective} from "../../components/lib/ui-card-helm/src";
import {HlmCheckboxComponent} from "../../components/lib/ui-checkbox-helm/src";
import {HlmIconComponent} from "../../components/lib/ui-icon-helm/src";
import {HlmInputDirective} from "../../components/lib/ui-input-helm/src";
import {HlmLabelDirective} from "../../components/lib/ui-label-helm/src";
import {
    HlmMenuComponent,
    HlmMenuGroupComponent, HlmMenuItemCheckboxDirective,
    HlmMenuItemCheckComponent, HlmMenuItemDirective, HlmMenuLabelComponent, HlmMenuSeparatorComponent
} from "../../components/lib/ui-menu-helm/src";
import {
    HlmSelectContentDirective,
    HlmSelectOptionComponent,
    HlmSelectTriggerComponent, HlmSelectValueDirective
} from "../../components/lib/ui-select-helm/src";
import {
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective, HlmSheetFooterComponent, HlmSheetHeaderComponent, HlmSheetTitleDirective
} from "../../components/lib/ui-sheet-helm/src";
import {HlmSpinnerComponent} from "../../components/lib/ui-spinner-helm/src";
import {
  HlmTableDirective,
  HlmTableImports,
  HlmTdComponent,
  HlmThComponent
} from "../../components/lib/ui-table-helm/src";
import {RouterLink} from "@angular/router";
import {TitleCasePipe} from "@angular/common";
import {ToastComponent} from "../../components/toast/toast.component";
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map, timer} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';
import {AdminDto, RoleDto} from '../../model/AdminDto';
import {DepartementDto} from '../../model/DepartementDto';
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {copy} from '../../environement/env';
import {provideIcons} from '@ng-icons/core';
import {
  lucideMoreHorizontal,
  lucidePlus,
  lucideSearch,
  lucideUser,
  lucideUserMinus,
  lucideUserPlus
} from '@ng-icons/lucide';
import {AdminService} from '../../services/admin/admin.service';
import {RoleService} from '../../services/role/role.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    BrnCellDefDirective,
    BrnColumnDefComponent,
    BrnPaginatorDirective,
    BrnSelectComponent,
    BrnSelectContentComponent,
    BrnSelectValueComponent,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    BrnTableComponent,
    FormsModule,
    HlmButtonDirective,
    HlmCardDirective,
    HlmCheckboxComponent,
    HlmIconComponent,
    HlmInputDirective,
    HlmLabelDirective,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemCheckComponent,
    HlmMenuItemCheckboxDirective,
    HlmMenuItemDirective,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmSelectContentDirective,
    HlmSelectOptionComponent,
    HlmSelectTriggerComponent,
    HlmSelectValueDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    HlmSpinnerComponent,
    HlmTableDirective,
    HlmTdComponent,
    HlmThComponent,
    ReactiveFormsModule,
    RouterLink,
    TitleCasePipe,
    ToastComponent,
    BrnMenuTriggerDirective,
    BrnTableComponent,
    BrnTableComponent,
    BrnColumnDefComponent,
    HlmThComponent,
    BrnHeaderDefDirective,
    HlmThComponent,
    HlmThComponent,
    BrnColumnDefComponent,
    BrnColumnDefComponent,
    HlmThComponent,
    BrnColumnDefComponent,
    BrnTableImports,
    HlmTableImports
  ],
  providers:[
    provideIcons({
      lucideUser,
      lucideUserMinus,
      lucideUserPlus,
      lucideSearch,
      lucidePlus,
      lucideMoreHorizontal
    })
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{


  admin: WritableSignal<AdminDto[]> = signal([])
  roles: WritableSignal<RoleDto[]> = signal([]);
  user :WritableSignal<AdminDto> = signal({} as AdminDto);


  constructor(private adminService: AdminService, private roleService: RoleService) {
  }

  async ngOnInit() {

    this.user.set(JSON.parse(localStorage.getItem('user')!) as AdminDto);

    this.loading.set(true);
    await this.fecthAllAdminBySpaceId();
    await this.fetchAllRoles();
    this.loading.set(false);

  }

  private async fecthAllAdminBySpaceId() {
    (await this.adminService.fetchAdminBySpaceId(this.user().space.id)).subscribe(
      {
        next: (data) => {
          this.admin.set(data);
        },
        error: (error) => {
          console.error(error);
          this.fecthAllAdminBySpaceId();
        },
        complete: () => {
          // this.loading.set(false);
        },
      }
    );
  }

  private async fetchAllRoles() {
    (await this.roleService.fetchAll()).subscribe({
      next: (data) => {
        this.roles.set(data);
      },
      error: (error) => {
        console.error(error);
        this.fetchAllRoles()
      }
    })
  }

  protected readonly _rawFilterInput = signal('');
  protected readonly _emailFilter = signal('');
  private readonly _debouncedFilter = toSignal(toObservable(this._rawFilterInput).pipe(debounceTime(300)));

  private readonly _displayedIndices = signal({ start: 0, end: 0 });
  protected readonly _availablePageSizes = [5, 10, 20, 10000];
  protected readonly _pageSize = signal(this._availablePageSizes[0]);

  private readonly _selectionModel = new SelectionModel<AdminDto>(true);
  protected readonly _isAdminDtoSelected = (AdminDto: AdminDto) => this._selectionModel.isSelected(AdminDto);
  protected readonly _selected = toSignal(this._selectionModel.changed.pipe(map((change) => change.source.selected)), {
    initialValue: [],
  });

  protected readonly _brnColumnManager = useBrnColumnManager({
    status: { visible: true, label: 'Status' },
    email: { visible: true, label: 'Email' },
    amount: { visible: true, label: 'Amount ($)' },
    // departement: { visible: true, label: 'Departement' },
  });
  protected readonly _allDisplayedColumns = computed(() => [
    'select',
    ...this._brnColumnManager.displayedColumns(),
    'actions',
  ]);

  private readonly _AdminDtos =this.admin;
  private readonly _filteredAdminDtos = computed(() => {
    const emailFilter = this._emailFilter()?.trim()?.toLowerCase();
    if (emailFilter && emailFilter.length > 0) {
      return this._AdminDtos().filter((u) => u.email.toLowerCase().includes(emailFilter));
    }
    return this._AdminDtos();
  });
  private readonly _emailSort = signal<'ASC' | 'DESC' | null>(null);
  protected readonly _filteredSortedPaginatedAdminDtos = computed(() => {
    const sort = this._emailSort();
    const start = this._displayedIndices().start;
    const end = this._displayedIndices().end + 1;
    const AdminDtos = this._filteredAdminDtos();
    if (!sort) {
      return AdminDtos.slice(start, end);
    }
    return [...AdminDtos]
      .sort((p1, p2) => (sort === 'ASC' ? 1 : -1) * p1.email.localeCompare(p2.email))
      .slice(start, end);
  });
  protected readonly _allFilteredPaginatedAdminDtosSelected = computed(() =>
    this._filteredSortedPaginatedAdminDtos().every((AdminDto) => this._selected().includes(AdminDto)),
  );
  protected readonly _checkboxState = computed(() => {
    const noneSelected = this._selected().length === 0;
    const allSelectedOrIndeterminate = this._allFilteredPaginatedAdminDtosSelected() ? true : 'indeterminate';
    return noneSelected ? false : allSelectedOrIndeterminate;
  });

  protected readonly _trackBy: TrackByFunction<AdminDto> = (_: number, p: AdminDto) => p.id;
  protected readonly _totalElements = computed(() => this._filteredAdminDtos().length);
  protected readonly _onStateChange = ({ startIndex, endIndex }: PaginatorState) => {
    this._displayedIndices.set({start: startIndex, end: endIndex});
  };
  protected toggleAdminDto(AdminDto: AdminDto) {
    this._selectionModel.toggle(AdminDto);
  }

  protected handleHeaderCheckboxChange() {
    const previousCbState = this._checkboxState();
    if (previousCbState === 'indeterminate' || !previousCbState) {
      this._selectionModel.select(...this._filteredSortedPaginatedAdminDtos());
    } else {
      this._selectionModel.deselect(...this._filteredSortedPaginatedAdminDtos());
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
  sortAdmin(gender: string){
    return this.admin()?.filter((u) => u.role.name == gender).length
  }

  visibleToast= signal(false);
  toastState = signal(true);
  departmentDtos = signal<DepartementDto[]>([]);
  loading = signal(false)
  adminFrom: FormGroup = new  FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    role: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
  });

  async saveAdmin() {
    if (this.adminFrom.valid) {
      this.loading.set(true);
      let admin: AdminDto = {
        name: this.adminFrom.value.name,
        email: this.adminFrom.value.email,
        password: this.adminFrom.value.password,
        role: this.roles().find(role => role.id == this.adminFrom.value.role)!,
        space: this.user().space

      };
      (await this.adminService.createAdmin(admin!)).subscribe({
        next: (res) => {
          this.loading.set(false)
          this.visibleToast.set(true)
          this.toastState.set(true)
          timer(1000).subscribe(() => this.visibleToast.set(false));
          this.admin.update(
            value => [...value,res.adminDto]
          )
        },
        error: (error) => {
          this.loading.set(false)
          this.visibleToast.set(true)
          this.toastState.set(false)
          timer(1000).subscribe(() => this.visibleToast.set(false));
        }
      });

    }

  }

  protected readonly copy = copy;

  save(element: any) {
    localStorage.setItem('adminShared', JSON.stringify(element));

  }
}
