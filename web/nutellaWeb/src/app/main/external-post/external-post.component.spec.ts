import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalPostComponent } from './main.component';

describe('MainComponent', () => {
  let component: ExternalPostComponent;
  let fixture: ComponentFixture<ExternalPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExternalPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExternalPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
