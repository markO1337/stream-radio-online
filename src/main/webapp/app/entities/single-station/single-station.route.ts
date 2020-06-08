import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISingleStation, SingleStation } from 'app/shared/model/single-station.model';
import { SingleStationService } from './single-station.service';
import { SingleStationComponent } from './single-station.component';
import { SingleStationDetailComponent } from './single-station-detail.component';
import { SingleStationUpdateComponent } from './single-station-update.component';

@Injectable({ providedIn: 'root' })
export class SingleStationResolve implements Resolve<ISingleStation> {
  constructor(private service: SingleStationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISingleStation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((singleStation: HttpResponse<SingleStation>) => {
          if (singleStation.body) {
            return of(singleStation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SingleStation());
  }
}

export const singleStationRoute: Routes = [
  {
    path: '',
    component: SingleStationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'streamRadioOnlineApp.singleStation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SingleStationDetailComponent,
    resolve: {
      singleStation: SingleStationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'streamRadioOnlineApp.singleStation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SingleStationUpdateComponent,
    resolve: {
      singleStation: SingleStationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'streamRadioOnlineApp.singleStation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SingleStationUpdateComponent,
    resolve: {
      singleStation: SingleStationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'streamRadioOnlineApp.singleStation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
