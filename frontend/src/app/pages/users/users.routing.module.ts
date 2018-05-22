import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { UsersComponent } from "./users.component";

const routes: Routes = [
  { path: '', component: UsersComponent, pathMatch: 'full' }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class UsersRoutingModule { }
