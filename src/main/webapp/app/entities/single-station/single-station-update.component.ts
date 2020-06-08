import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISingleStation, SingleStation } from 'app/shared/model/single-station.model';
import { SingleStationService } from './single-station.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-single-station-update',
  templateUrl: './single-station-update.component.html',
})
export class SingleStationUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    url: [null, [Validators.required]],
    license: [null, [Validators.required]],
    userId: [null, Validators.required],
  });

  constructor(
    protected singleStationService: SingleStationService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ singleStation }) => {
      this.updateForm(singleStation);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(singleStation: ISingleStation): void {
    this.editForm.patchValue({
      id: singleStation.id,
      name: singleStation.name,
      url: singleStation.url,
      license: singleStation.license,
      userId: singleStation.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const singleStation = this.createFromForm();
    if (singleStation.id !== undefined) {
      this.subscribeToSaveResponse(this.singleStationService.update(singleStation));
    } else {
      this.subscribeToSaveResponse(this.singleStationService.create(singleStation));
    }
  }

  private createFromForm(): ISingleStation {
    return {
      ...new SingleStation(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      url: this.editForm.get(['url'])!.value,
      license: this.editForm.get(['license'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISingleStation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
