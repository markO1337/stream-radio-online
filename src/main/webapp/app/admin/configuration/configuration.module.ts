import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { StreamRadioOnlineSharedModule } from 'app/shared/shared.module';

import { ConfigurationComponent } from './configuration.component';

import { configurationRoute } from './configuration.route';

@NgModule({
  imports: [StreamRadioOnlineSharedModule, RouterModule.forChild([configurationRoute])],
  declarations: [ConfigurationComponent],
})
export class ConfigurationModule {}
