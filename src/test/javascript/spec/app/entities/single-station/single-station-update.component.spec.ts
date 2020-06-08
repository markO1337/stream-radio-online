import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StreamRadioOnlineTestModule } from '../../../test.module';
import { SingleStationUpdateComponent } from 'app/entities/single-station/single-station-update.component';
import { SingleStationService } from 'app/entities/single-station/single-station.service';
import { SingleStation } from 'app/shared/model/single-station.model';

describe('Component Tests', () => {
  describe('SingleStation Management Update Component', () => {
    let comp: SingleStationUpdateComponent;
    let fixture: ComponentFixture<SingleStationUpdateComponent>;
    let service: SingleStationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StreamRadioOnlineTestModule],
        declarations: [SingleStationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SingleStationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SingleStationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SingleStationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SingleStation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SingleStation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
