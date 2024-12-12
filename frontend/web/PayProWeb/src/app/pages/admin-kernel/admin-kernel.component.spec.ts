import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminKernelComponent } from './admin-kernel.component';

describe('AdminKernelComponent', () => {
  let component: AdminKernelComponent;
  let fixture: ComponentFixture<AdminKernelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminKernelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminKernelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
