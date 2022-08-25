import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcesspensionComponent } from './processpension.component';

describe('ProcesspensionComponent', () => {
  let component: ProcesspensionComponent;
  let fixture: ComponentFixture<ProcesspensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcesspensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcesspensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
