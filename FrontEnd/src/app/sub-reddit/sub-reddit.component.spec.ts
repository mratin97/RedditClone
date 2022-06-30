import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubRedditComponent } from './sub-reddit.component';

describe('SubRedditComponent', () => {
  let component: SubRedditComponent;
  let fixture: ComponentFixture<SubRedditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubRedditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubRedditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
