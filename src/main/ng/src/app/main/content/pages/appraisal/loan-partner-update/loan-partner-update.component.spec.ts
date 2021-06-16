import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanPartnerUpdateComponent } from './loan-partner-update.component';

describe('LoanPartnerUpdateComponent', () => {
  let component: LoanPartnerUpdateComponent;
  let fixture: ComponentFixture<LoanPartnerUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoanPartnerUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanPartnerUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
