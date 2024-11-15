import {Component, computed, effect, OnInit, signal, TrackByFunction, WritableSignal} from '@angular/core';
import {provideIcons} from '@ng-icons/core';
import {
  lucideArrowLeft,
  lucideChevronDown,
  lucideMoreHorizontal,
  lucidePlus,
  lucideUser,
  lucideFile
} from '@ng-icons/lucide';

import { RouterLink } from '@angular/router';

import {DatePipe, DecimalPipe, TitleCasePipe, UpperCasePipe} from '@angular/common';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, map, timer} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';

import {UserDto} from '../../model/UserDto';
import {Storage, StorageService} from '../../services/storage/storage.service';
import {StorageImageService} from '../../services/storage-image/storage-image.service';
import {FileDto} from '../../model/FileDto';
import {v4} from 'uuid';
import {data} from 'autoprefixer';
import {FileService} from '../../services/file/file.service';
import {DepartementDto} from '../../model/DepartementDto';
import {DepartementService} from '../../services/departement/departement.service';
import {fakeOrganisation} from '../../environement/env';
import {UserService} from '../../services/user/user.service';
import {HlmButtonDirective} from '../../components/lib/ui-button-helm/src';
import {HlmIconComponent} from '../../components/lib/ui-icon-helm/src';
import {HlmCardContentDirective, HlmCardDirective, HlmCardHeaderDirective} from '../../components/lib/ui-card-helm/src';
import {
  HlmTableDirective,
  HlmTableImports,
  HlmTdComponent,
  HlmThComponent
} from '../../components/lib/ui-table-helm/src';
import {HlmCheckboxComponent, HlmCheckboxImports} from '../../components/lib/ui-checkbox-helm/src';
import {HlmSelectImports, HlmSelectOptionComponent} from '../../components/lib/ui-select-helm/src';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent, HlmMenuItemCheckboxDirective, HlmMenuItemCheckComponent, HlmMenuItemImports,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent
} from '../../components/lib/ui-menu-helm/src';
import {BrnMenuTriggerDirective} from '@spartan-ng/ui-menu-brain';
import {
  HlmSheetComponent,
  HlmSheetContentComponent, HlmSheetDescriptionDirective,
  HlmSheetFooterComponent,
  HlmSheetHeaderComponent, HlmSheetImports
} from '../../components/lib/ui-sheet-helm/src';
import {HlmInputDirective} from '../../components/lib/ui-input-helm/src';
import {HlmLabelDirective} from '../../components/lib/ui-label-helm/src';
import {HlmSpinnerComponent} from '../../components/lib/ui-spinner-helm/src';
import {
  BrnCellDefDirective,
  BrnColumnDefComponent,
  BrnPaginatorDirective,
  BrnTableComponent, BrnTableImports, PaginatorState, useBrnColumnManager
} from '@spartan-ng/ui-table-brain';
import {BrnSelectComponent, BrnSelectContentComponent, BrnSelectValueComponent} from '@spartan-ng/ui-select-brain';
import {BrnSheetContentDirective, BrnSheetTriggerDirective} from '@spartan-ng/ui-sheet-brain';

@Component({
  imports: [
    HlmButtonDirective,
    HlmIconComponent,
    HlmCardDirective,
    HlmCardHeaderDirective,
    HlmCardDirective,
    HlmCardContentDirective,
    BrnCellDefDirective,
    BrnColumnDefComponent,
    BrnPaginatorDirective,
    BrnSelectComponent,
    BrnSelectContentComponent,
    BrnSelectValueComponent,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    BrnTableComponent,
    DecimalPipe,
    ReactiveFormsModule,
    RouterLink,
    TitleCasePipe,
    HlmTableDirective,
    BrnTableComponent,
    HlmTableDirective,
    BrnColumnDefComponent,
    BrnTableImports,
    HlmThComponent,
    HlmCheckboxComponent,
    HlmCheckboxImports,
    HlmCheckboxComponent,
    HlmCheckboxImports,
    HlmTdComponent,
    HlmCheckboxComponent,
    HlmTableImports,
    HlmThComponent,
    HlmTableImports,
    HlmSelectOptionComponent,
    HlmSelectOptionComponent,
    HlmSelectOptionComponent,
    FormsModule,
    HlmSelectImports,
    HlmSelectOptionComponent,
    HlmMenuGroupComponent,
    HlmMenuGroupComponent,
    HlmMenuSeparatorComponent,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmMenuGroupComponent,
    HlmMenuComponent,
    HlmMenuComponent,
    BrnMenuTriggerDirective,
    HlmMenuItemCheckComponent,
    HlmMenuItemCheckComponent,
    HlmMenuItemCheckboxDirective,
    HlmMenuItemCheckboxDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetContentComponent,
    HlmSheetComponent,
    HlmSheetComponent,
    HlmSheetImports,
    HlmIconComponent,
    HlmMenuComponent,
    HlmMenuItemCheckComponent,
    HlmIconComponent,
    HlmSheetDescriptionDirective,
    HlmSheetContentComponent,
    HlmInputDirective,
    HlmLabelDirective,
    HlmInputDirective,
    HlmSheetFooterComponent,
    HlmIconComponent,
    DatePipe,
    UpperCasePipe,
    HlmMenuItemCheckComponent,
    HlmMenuItemCheckboxDirective,
    HlmMenuItemImports,
    HlmMenuItemCheckboxDirective,
    HlmMenuItemCheckComponent,
    HlmMenuItemImports,
    HlmIconComponent,
    HlmIconComponent,
    HlmIconComponent,
    HlmSpinnerComponent,
    HlmSpinnerComponent
  ],
  providers: [
    provideIcons({
      lucideArrowLeft,
      lucideUser,
      lucideChevronDown,
      lucidePlus,
      lucideMoreHorizontal,
      lucideFile
    })
  ],
  selector: 'app-user-detail',
  standalone: true,
  styleUrl: './user-detail.component.css',
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit{
  user : WritableSignal<UserDto> =signal({} as UserDto);
  files: WritableSignal<FileDto[]> = signal([]);
  departmentDtos = signal<DepartementDto[]>([]);

  documentForm = new FormGroup({
    document: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
  });
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

  protected readonly _rawFilterInput = signal('');
  protected readonly _emailFilter = signal('');
  private readonly _debouncedFilter = toSignal(toObservable(this._rawFilterInput).pipe(debounceTime(300)));

  private readonly _displayedIndices = signal({ start: 0, end: 0 });
  protected readonly _availablePageSizes = [5, 10, 20, 10000];
  protected readonly _pageSize = signal(this._availablePageSizes[0]);

  private readonly _selectionModel = new SelectionModel<FileDto>(true);
  protected readonly _isFileDtoSelected = (FileDto: FileDto) => this._selectionModel.isSelected(FileDto);
  protected readonly _selected = toSignal(this._selectionModel.changed.pipe(map((change) => change.source.selected)), {
    initialValue: [],
  });

  protected readonly _brnColumnManager = useBrnColumnManager({
    date: { visible: true, label: 'Date' },
    name: { visible: true, label: 'Name' },
  });
  protected readonly _allDisplayedColumns = computed(() => [
    'select',
    ...this._brnColumnManager.displayedColumns(),
    'actions',
  ]);

  private readonly _FileDtos = this.files;
  private readonly _filteredFileDtos = computed(() => {
    const emailFilter = this._emailFilter()?.trim()?.toLowerCase();
    if (emailFilter && emailFilter.length > 0) {
      return this._FileDtos().filter((u) => u.name.toLowerCase().includes(emailFilter));
    }
    return this._FileDtos();
  });
  private readonly _emailSort = signal<'ASC' | 'DESC' | null>(null);
  protected readonly _filteredSortedPaginatedFileDtos = computed(() => {
    const sort = this._emailSort();
    const start = this._displayedIndices().start;
    const end = this._displayedIndices().end + 1;
    const FileDtos = this._filteredFileDtos();
    if (!sort) {
      return FileDtos.slice(start, end);
    }
    return [...FileDtos]
      .sort((p1, p2) => (sort === 'ASC' ? 1 : -1) * p1.name.localeCompare(p2.name))
      .slice(start, end);
  });
  protected readonly _allFilteredPaginatedFileDtosSelected = computed(() =>
    this._filteredSortedPaginatedFileDtos().every((FileDto) => this._selected().includes(FileDto)),
  );
  protected readonly _checkboxState = computed(() => {
    const noneSelected = this._selected().length === 0;
    const allSelectedOrIndeterminate = this._allFilteredPaginatedFileDtosSelected() ? true : 'indeterminate';
    return noneSelected ? false : allSelectedOrIndeterminate;
  });

  protected readonly _trackBy: TrackByFunction<FileDto> = (_: number, p: FileDto) => p.id;
  protected readonly _totalElements = computed(() => this._filteredFileDtos().length);
  protected readonly _onStateChange = ({ startIndex, endIndex }: PaginatorState) =>
    this._displayedIndices.set({ start: startIndex, end: endIndex });
   image!: File;
  loading = signal<boolean>(false);

  constructor(private userService: UserService,private storage: StorageImageService, private fileService: FileService, private departementService: DepartementService) {
    // needed to sync the debounced filter to the name filter, but being able to override the
    // filter when loading new users without debounce
    effect(() => this._emailFilter.set(this._debouncedFilter() ?? ''), { allowSignalWrites: true });
  }

  protected toggleFileDto(FileDto: FileDto) {
    this._selectionModel.toggle(FileDto);
  }

  protected handleHeaderCheckboxChange() {
    const previousCbState = this._checkboxState();
    if (previousCbState === 'indeterminate' || !previousCbState) {
      this._selectionModel.select(...this._filteredSortedPaginatedFileDtos());
    } else {
      this._selectionModel.deselect(...this._filteredSortedPaginatedFileDtos());
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
  goBack() {


  }

  selectedImage($event: any) {
    this.documentForm.get('document')?.setValue($event.target!.files![0])
    // if ($event.target!.files![0].type != 'image/png' || $event.target!.files![0].type != 'image/jpeg') {
    //   // this.state.set(-3)
    //   // timer(1000).subscribe(() => this.state.set(0))
    //
    // }
    this.image = $event.target!.files![0]
  }

  async subMitDocument() {
    let file : WritableSignal<FileDto>
    if(this.documentForm.valid){
      this.loading.set(true);
      const data = (await this.storage.storeFile(this.image))
      file = signal<FileDto>({
        dateFile: this.documentForm.value.date as string,
        user: this.user(),
        id: v4(),
        createdAt: new Date().toISOString(),
        organisation: this.user().organisation,
        name: this.documentForm.value.name as string,
        link: data?.data.publicUrl!
      })

      this.fileService.saveDoc(file()).subscribe(
        (data) => {

          this._FileDtos.update(()=>[...this.files(), data]);
          this.loading.set(false)
        },
        (error) => console.error(error)
      )
      this.loading.set(false)

    }


  }

  async ngOnInit() {
    this.loading.set(true);
    this.user.set(JSON.parse(localStorage.getItem(Storage.sharedData)!) as UserDto);
    this.userFrom.setControl('name', new FormControl(this.user().name));
    this.userFrom.setControl('email', new FormControl(this.user().email));
    this.userFrom.setControl('department', new FormControl(this.user().department.id));
    this.userFrom.setControl('role', new FormControl(this.user().rule));
    this.userFrom.setControl('status', new FormControl(this.user().rule));
    this.userFrom.setControl('gender', new FormControl(this.user().gender));
    this.userFrom.setControl('displayName', new FormControl(this.user().displayName));
    this.userFrom.setControl('cni', new FormControl(this.user().cni));
    this.userFrom.setControl('dateOfBirth', new FormControl(this.user().dateBirth));
    this.userFrom.setControl('phone', new FormControl(this.user().phone));
    this.userFrom.setControl('address', new FormControl(this.user().address));
    this.fileService.fetchDocs(this.user()).subscribe(
      (data) => {
        this.loading.set(false);
        this._FileDtos.set(data);
      },
    );

    (await this.departementService.fetchDepatments()).subscribe(
      (users) => this.departmentDtos.set(users),
      (error) => console.error(error)
    )
  }

  async saveUser() {
    let user: WritableSignal<UserDto>;
    console.log(this.userFrom.value);

    if (this.userFrom.valid) {
      console.log(this.userFrom.value);
      user = signal({
        id:  this.user().id,
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

        },
        (error) => console.error(error)
      )
    }
  }
  openLink(link:string) {
    window.open(link, '_blank');

  }
}
