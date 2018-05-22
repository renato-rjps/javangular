import { NgModule } from "@angular/core";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { UsersComponent } from "./users/users.component";
import { ActuatorComponent } from "./actuator/actuator.component";
import { AuthModule } from "../auth/auth.module";
import { HttpClientModule } from "@angular/common/http";
import { BrowserModule } from "@angular/platform-browser";
import { PagesComponent } from "./pages.component";
import { AppRoutingModule } from "../app.routing.module";
import { PagesRoutingModule } from "./pages.routing.module";
import { ThemeModule } from "../theme/theme.module";

@NgModule({
  declarations: [
    DashboardComponent,
    UsersComponent,
    ActuatorComponent,
    PagesComponent, 
  ],
  imports: [
    BrowserModule,
    ThemeModule,
    AppRoutingModule,
    AuthModule,
    HttpClientModule,
    PagesRoutingModule
  ]
})
export class PagesModule { }
