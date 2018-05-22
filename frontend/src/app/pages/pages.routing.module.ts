import { Routes, RouterModule } from "@angular/router";
import { PagesComponent } from "./pages.component";
import { AuthGuard } from "../auth/auth.guard";
import { ActuatorComponent } from "./actuator/actuator.component";
import { UsersComponent } from "./users/users.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
  {
    path: '', component: PagesComponent, children: [
      {
        path: 'dashboard',
        canLoad: [AuthGuard],
        runGuardsAndResolvers: 'always',
        loadChildren: './dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'actuator',
        canLoad: [AuthGuard],
        runGuardsAndResolvers: 'always',
        loadChildren: './actuator/actuator.module#ActuatorModule'
      },
      {
        path: 'users',
        canLoad: [AuthGuard],
        runGuardsAndResolvers: 'always',
        loadChildren: './users/users.module#UsersModule'
      }
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class PagesRoutingModule { }
