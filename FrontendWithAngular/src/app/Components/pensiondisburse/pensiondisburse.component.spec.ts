import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PensiondisburseComponent } from './pensiondisburse.component';

describe('PensiondisburseComponent', () => {
  let component: PensiondisburseComponent;
  let fixture: ComponentFixture<PensiondisburseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PensiondisburseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PensiondisburseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
