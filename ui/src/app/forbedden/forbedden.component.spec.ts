import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ForbeddenComponent } from './forbedden.component';

describe('ForbeddenComponent', () => {
  let component: ForbeddenComponent;
  let fixture: ComponentFixture<ForbeddenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ForbeddenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ForbeddenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
