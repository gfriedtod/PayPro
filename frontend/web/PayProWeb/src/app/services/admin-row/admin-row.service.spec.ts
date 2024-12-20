import { TestBed } from '@angular/core/testing';

import { AdminRowService } from './admin-row.service';

describe('AdminRowService', () => {
  let service: AdminRowService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminRowService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
