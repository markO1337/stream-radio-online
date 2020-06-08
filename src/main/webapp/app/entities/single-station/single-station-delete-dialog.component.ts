import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISingleStation } from 'app/shared/model/single-station.model';
import { SingleStationService } from './single-station.service';

@Component({
  templateUrl: './single-station-delete-dialog.component.html',
})
export class SingleStationDeleteDialogComponent {
  singleStation?: ISingleStation;

  constructor(
    protected singleStationService: SingleStationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.singleStationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('singleStationListModification');
      this.activeModal.close();
    });
  }
}
