import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StreamRadioOnlineSharedModule } from 'app/shared/shared.module';
import { SingleStationComponent } from './single-station.component';
import { SingleStationDetailComponent } from './single-station-detail.component';
import { SingleStationUpdateComponent } from './single-station-update.component';
import { SingleStationDeleteDialogComponent } from './single-station-delete-dialog.component';
import { singleStationRoute } from './single-station.route';

@NgModule({
  imports: [StreamRadioOnlineSharedModule, RouterModule.forChild(singleStationRoute)],
  declarations: [SingleStationComponent, SingleStationDetailComponent, SingleStationUpdateComponent, SingleStationDeleteDialogComponent],
  entryComponents: [SingleStationDeleteDialogComponent],
})
export class StreamRadioOnlineSingleStationModule {}
