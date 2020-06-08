import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISingleStation } from 'app/shared/model/single-station.model';

@Component({
  selector: 'jhi-single-station-detail',
  templateUrl: './single-station-detail.component.html',
})
export class SingleStationDetailComponent implements OnInit {
  singleStation: ISingleStation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ singleStation }) => (this.singleStation = singleStation));
  }

  previousState(): void {
    window.history.back();
  }
}
