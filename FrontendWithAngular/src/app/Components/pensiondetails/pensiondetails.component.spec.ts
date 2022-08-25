import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensiondetailsComponent } from './pensiondetails.component';

describe('PensiondetailsComponent', () => {
  let component: PensiondetailsComponent;
  let fixture: ComponentFixture<PensiondetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensiondetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensiondetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
