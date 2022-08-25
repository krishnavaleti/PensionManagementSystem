import { TestBed } from '@angular/core/testing';

import { PensiondetailsService } from './pensiondetails.service';

describe('PensiondetailsService', () => {
  let service: PensiondetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PensiondetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
