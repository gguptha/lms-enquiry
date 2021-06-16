import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanPartnersComponent } from './loan-partners.component';

describe('LoanPartnersComponent', () => {
  let component: LoanPartnersComponent;
  let fixture: ComponentFixture<LoanPartnersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoanPartnersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanPartnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
