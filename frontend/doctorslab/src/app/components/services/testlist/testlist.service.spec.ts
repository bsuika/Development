import { TestBed } from '@angular/core/testing';

import { TestlistService } from './testlist.service';

describe('TestlistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TestlistService = TestBed.get(TestlistService);
    expect(service).toBeTruthy();
  });
});
