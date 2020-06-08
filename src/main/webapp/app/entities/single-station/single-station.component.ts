import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISingleStation } from 'app/shared/model/single-station.model';
import { SingleStationService } from './single-station.service';
import { SingleStationDeleteDialogComponent } from './single-station-delete-dialog.component';

@Component({
  selector: 'jhi-single-station',
  templateUrl: './single-station.component.html',
})
export class SingleStationComponent implements OnInit, OnDestroy {
  singleStations?: ISingleStation[];
  eventSubscriber?: Subscription;

  constructor(
    protected singleStationService: SingleStationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.singleStationService.query().subscribe((res: HttpResponse<ISingleStation[]>) => (this.singleStations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSingleStations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISingleStation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSingleStations(): void {
    this.eventSubscriber = this.eventManager.subscribe('singleStationListModification', () => this.loadAll());
  }

  delete(singleStation: ISingleStation): void {
    const modalRef = this.modalService.open(SingleStationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.singleStation = singleStation;
  }
}
