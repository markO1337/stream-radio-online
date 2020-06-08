import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StreamRadioOnlineTestModule } from '../../../test.module';
import { SingleStationComponent } from 'app/entities/single-station/single-station.component';
import { SingleStationService } from 'app/entities/single-station/single-station.service';
import { SingleStation } from 'app/shared/model/single-station.model';

describe('Component Tests', () => {
  describe('SingleStation Management Component', () => {
    let comp: SingleStationComponent;
    let fixture: ComponentFixture<SingleStationComponent>;
    let service: SingleStationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StreamRadioOnlineTestModule],
        declarations: [SingleStationComponent],
      })
        .overrideTemplate(SingleStationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SingleStationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SingleStationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SingleStation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.singleStations && comp.singleStations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
