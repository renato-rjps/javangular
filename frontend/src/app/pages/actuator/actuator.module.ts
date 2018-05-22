import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActuatorComponent } from './actuator.component';
import { ActuatorRoutingModule } from './actuator.routing.module';

@NgModule({
  declarations: [
    ActuatorComponent
  ],
  imports: [
    CommonModule,
    ActuatorRoutingModule
  ]
})
export class ActuatorModule { }
