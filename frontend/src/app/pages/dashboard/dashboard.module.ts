import { NgModule } from "@angular/core";
import { DashboardComponent } from "../dashboard/dashboard.component";
import { DashboardRoutingModule } from "./dashboard.routing.module";
import { CommonModule } from "@angular/common";


@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
