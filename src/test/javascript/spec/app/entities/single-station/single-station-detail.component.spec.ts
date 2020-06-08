import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StreamRadioOnlineTestModule } from '../../../test.module';
import { SingleStationDetailComponent } from 'app/entities/single-station/single-station-detail.component';
import { SingleStation } from 'app/shared/model/single-station.model';

describe('Component Tests', () => {
  describe('SingleStation Management Detail Component', () => {
    let comp: SingleStationDetailComponent;
    let fixture: ComponentFixture<SingleStationDetailComponent>;
    const route = ({ data: of({ singleStation: new SingleStation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StreamRadioOnlineTestModule],
        declarations: [SingleStationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SingleStationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SingleStationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load singleStation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.singleStation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
