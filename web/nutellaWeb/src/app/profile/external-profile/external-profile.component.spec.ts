import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalProfileComponent } from './external-profile.component';

describe('ExternalProfileComponent', () => {
  let component: ExternalProfileComponent;
  let fixture: ComponentFixture<ExternalProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExternalProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExternalProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
