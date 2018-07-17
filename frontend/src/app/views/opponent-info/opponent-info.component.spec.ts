import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpponentInfoComponent } from './opponent-info.component';

describe('OpponentInfoComponent', () => {
  let component: OpponentInfoComponent;
  let fixture: ComponentFixture<OpponentInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpponentInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpponentInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});