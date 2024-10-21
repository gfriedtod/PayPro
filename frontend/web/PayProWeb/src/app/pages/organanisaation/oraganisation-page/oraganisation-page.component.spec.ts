import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OraganisationPageComponent } from './oraganisation-page.component';

describe('OraganisationPageComponent', () => {
  let component: OraganisationPageComponent;
  let fixture: ComponentFixture<OraganisationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OraganisationPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OraganisationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
