import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MailRepoComponent } from './mail-repo.component';

describe('MailRepoComponent', () => {
  let component: MailRepoComponent;
  let fixture: ComponentFixture<MailRepoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MailRepoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MailRepoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
