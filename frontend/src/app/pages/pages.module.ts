import { CommonModule } from "@angular/common";
import { ThemeModule } from "../theme/theme.module";
import { PagesRoutingModule } from "./pages.routing.module";
import { PagesComponent } from "./pages.component";
import { NgModule } from "@angular/core";


@NgModule({
  declarations: [
    PagesComponent, 
  ],
  imports: [
    CommonModule,
    ThemeModule,
    PagesRoutingModule
  ]
})
export class PagesModule { }
