import { NgModule } from "@angular/core";
import {
  HeaderComponent,
  SidenavComponent
} from './components';
import { RouterModule } from "@angular/router";

const COMPONENTS = [
  HeaderComponent,
  SidenavComponent
]

@NgModule({
  declarations: [
    ...COMPONENTS
  ],
  imports: [
    RouterModule
  ],
  exports: [
    ...COMPONENTS
  ]
})
export class ThemeModule { }
