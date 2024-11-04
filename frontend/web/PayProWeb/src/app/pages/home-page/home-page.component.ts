import {Component, computed, effect, OnInit, signal, TrackByFunction, WritableSignal} from '@angular/core';
import {HlmCardContentDirective, HlmCardDirective, HlmCardImports} from '@spartan-ng/ui-card-helm';
import {HlmIconComponent} from '@spartan-ng/ui-icon-helm';
import {provideIcons} from '@ng-icons/core';
import {
  lucideMoreHorizontal,
  lucidePlus,
  lucideSearch,
  lucideUser,
  lucideUserMinus,
  lucideUserPlus
} from '@ng-icons/lucide';
import {HlmInputDirective} from '@spartan-ng/ui-input-helm';
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from '@spartan-ng/ui-select-brain';
import {
  HlmSelectContentDirective,
  HlmSelectImports,
  HlmSelectOptionComponent,
  HlmSelectValueDirective
} from '@spartan-ng/ui-select-helm';
import {HlmScrollAreaModule} from '@spartan-ng/ui-scrollarea-helm';
import {HlmButtonDirective} from '@spartan-ng/ui-button-helm';
import {HlmTableDirective, HlmTableImports, HlmTdComponent, HlmThComponent} from '@spartan-ng/ui-table-helm';
import {BrnTableImports, PaginatorState, useBrnColumnManager} from '@spartan-ng/ui-table-brain';
import {HlmCheckboxComponent, HlmCheckboxImports} from '@spartan-ng/ui-checkbox-helm';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemImports,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuStructureImports
} from '@spartan-ng/ui-menu-helm';
import {SelectionModel} from '@angular/cdk/collections';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map, of} from 'rxjs';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {DecimalPipe, TitleCasePipe} from '@angular/common';
import {
  HlmSheetComponent,
  HlmSheetContentComponent,
  HlmSheetDescriptionDirective,
  HlmSheetFooterComponent,
  HlmSheetHeaderComponent,
  HlmSheetTitleDirective
} from '@spartan-ng/ui-sheet-helm';
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';
import {HlmLabelDirective} from '@spartan-ng/ui-label-helm';
import {RouterLink} from '@angular/router';
import {UserDto} from '../../model/UserDto';
import {UserService} from '../../services/user/user.service';
import {DepartementDto} from '../../model/DepartementDto';
import {DepartementService} from '../../services/departement/departement.service';
import {GeneratePasswordService} from '../../services/generate-password/generate-password.service';
import {fakeOrganisation} from '../../environement/env';
import {v4} from 'uuid';
import {StorageService} from '../../services/storage/storage.service';


@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    HlmCardDirective,
    HlmCardContentDirective,
    HlmCardContentDirective,
    HlmIconComponent,
    HlmIconComponent,
    HlmIconComponent,
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
    HlmSheetComponent,
    HlmSheetComponent,
    HlmSheetContentComponent,
    BrnSheetContentDirective,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    HlmSheetDescriptionDirective,
    HlmLabelDirective,
    HlmSheetFooterComponent,
    BrnSheetTriggerDirective,
    HlmSheetTitleDirective,
    HlmSheetDescriptionDirective,
    RouterLink,
    HlmButtonDirective,
    ReactiveFormsModule,
    HlmSelectOptionComponent,
    HlmSelectImports,
    HlmSelectOptionComponent,
    HlmSelectImports,
    HlmSelectOptionComponent,
    HlmSelectImports,
    HlmSelectOptionComponent,
    HlmSelectImports,
    HlmSelectOptionComponent,
    HlmSelectImports
  ],
  providers: [
    provideIcons({
      lucideUser,
      lucideUserMinus,
      lucideUserPlus,
      lucideSearch,
      lucidePlus,
      lucideMoreHorizontal
    })
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {

   users: WritableSignal<UserDto[]> = signal([]);
  userFrom: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    department: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required]),
    status: new FormControl('USER', [Validators.required]),
    gender: new FormControl('', [Validators.required]),
    displayName: new FormControl('', [Validators.required]),
    cni: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    dateOfBirth: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
  });
  departmentDtos = signal<DepartementDto[]>([]);

  constructor(private userService: UserService, private departementService: DepartementService, private generatePasswordService: GeneratePasswordService,private storageService: StorageService) {
    // needed to sync the debounced filter to the name filter, but being able to override the
    // filter when loading new users without debounce
    effect(() => this._emailFilter.set(this._debouncedFilter() ?? ''), { allowSignalWrites: true });
  }
  protected readonly _rawFilterInput = signal('');
  protected readonly _emailFilter = signal('');
  private readonly _debouncedFilter = toSignal(toObservable(this._rawFilterInput).pipe(debounceTime(300)));

  private readonly _displayedIndices = signal({ start: 0, end: 0 });
  protected readonly _availablePageSizes = [5, 10, 20, 10000];
  protected readonly _pageSize = signal(this._availablePageSizes[0]);

  private readonly _selectionModel = new SelectionModel<UserDto>(true);
  protected readonly _isUserDtoSelected = (UserDto: UserDto) => this._selectionModel.isSelected(UserDto);
  protected readonly _selected = toSignal(this._selectionModel.changed.pipe(map((change) => change.source.selected)), {
    initialValue: [],
  });

  protected readonly _brnColumnManager = useBrnColumnManager({
    status: { visible: true, label: 'Status' },
    email: { visible: true, label: 'Email' },
    amount: { visible: true, label: 'Amount ($)' },
    departement: { visible: true, label: 'Departement' },
  });
  protected readonly _allDisplayedColumns = computed(() => [
    'select',
    ...this._brnColumnManager.displayedColumns(),
    'actions',
  ]);

  private readonly _UserDtos =this.users;
  private readonly _filteredUserDtos = computed(() => {
    const emailFilter = this._emailFilter()?.trim()?.toLowerCase();
    if (emailFilter && emailFilter.length > 0) {
      return this._UserDtos().filter((u) => u.email.toLowerCase().includes(emailFilter));
    }
    return this._UserDtos();
  });
  private readonly _emailSort = signal<'ASC' | 'DESC' | null>(null);
  protected readonly _filteredSortedPaginatedUserDtos = computed(() => {
    const sort = this._emailSort();
    const start = this._displayedIndices().start;
    const end = this._displayedIndices().end + 1;
    const UserDtos = this._filteredUserDtos();
    if (!sort) {
      return UserDtos.slice(start, end);
    }
    return [...UserDtos]
      .sort((p1, p2) => (sort === 'ASC' ? 1 : -1) * p1.email.localeCompare(p2.email))
      .slice(start, end);
  });
  protected readonly _allFilteredPaginatedUserDtosSelected = computed(() =>
    this._filteredSortedPaginatedUserDtos().every((UserDto) => this._selected().includes(UserDto)),
  );
  protected readonly _checkboxState = computed(() => {
    const noneSelected = this._selected().length === 0;
    const allSelectedOrIndeterminate = this._allFilteredPaginatedUserDtosSelected() ? true : 'indeterminate';
    return noneSelected ? false : allSelectedOrIndeterminate;
  });

  protected readonly _trackBy: TrackByFunction<UserDto> = (_: number, p: UserDto) => p.id;
  protected readonly _totalElements = computed(() => this._filteredUserDtos().length);
  protected readonly _onStateChange = ({ startIndex, endIndex }: PaginatorState) =>
    this._displayedIndices.set({ start: startIndex, end: endIndex });

  async ngOnInit() {
    this.userFrom.setControl('password', new FormControl(this.generatePasswordService.generatePassword()))
    console.log("hello home");
    (await this.userService.fetchUsers()).subscribe(
      (users) => this.users.set(users),
      (error) => console.error(error)
    );
    (await this.departementService.fetchDepatments()).subscribe(
      (users) => this.departmentDtos.set(users),
      (error) => console.error(error)
    )
  }

  protected toggleUserDto(UserDto: UserDto) {
    this._selectionModel.toggle(UserDto);
  }

  protected handleHeaderCheckboxChange() {
    const previousCbState = this._checkboxState();
    if (previousCbState === 'indeterminate' || !previousCbState) {
      this._selectionModel.select(...this._filteredSortedPaginatedUserDtos());
    } else {
      this._selectionModel.deselect(...this._filteredSortedPaginatedUserDtos());
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
  protected readonly lucideSearch = lucideSearch;
  protected readonly of = of;

  async saveUser() {
    let user: WritableSignal<UserDto>;
    console.log(this.userFrom.value);

    if (this.userFrom.valid) {
      console.log(this.userFrom.value);
      user = signal({
        id:  v4(),
        createdAt: '',
        organisation: fakeOrganisation,
        name: this.userFrom.value.name as string,
        email: this.userFrom.value.email as string,
        department: this.departmentDtos().find((d) => d.id === this.userFrom.value.department) as DepartementDto, //this.userFrom.value.department ,
        rule: this.userFrom.value.role as string,
        status: this.userFrom.value.status as string,
        gender: this.userFrom.value.gender as string,
        displayName: this.userFrom.value.displayName as string,
        cni: this.userFrom.value.cni as string,
        password: this.userFrom.value.password as string,
        dateBirth: this.userFrom.value.dateOfBirth as string,
        phone: this.userFrom.value.phone as string,
        address: this.userFrom.value.address as string,
      });
      (await this.userService.postUser(user())).subscribe(
        (user)=> {
          this.users.update((u) => [...u, user]);
        },
        (error) => console.error(error)
      )
    }
  }

  save(data: any) {
    this.storageService.setData(data);
  }
  sortGender(gender: string){
    return this.users().filter((u) => u.gender == gender).length
  }
}
