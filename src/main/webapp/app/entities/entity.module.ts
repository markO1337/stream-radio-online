import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'single-station',
        loadChildren: () => import('./single-station/single-station.module').then(m => m.StreamRadioOnlineSingleStationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class StreamRadioOnlineEntityModule {}
