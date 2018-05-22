import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { ActuatorComponent } from "./actuator.component";

const routes: Routes = [
  { path: '', component: ActuatorComponent, pathMatch: 'full' }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class ActuatorRoutingModule { }
