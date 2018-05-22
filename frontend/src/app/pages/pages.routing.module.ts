import { Routes, RouterModule } from "@angular/router";
import { PagesComponent } from "./pages.component";
import { AuthGuard } from "../auth/auth.guard";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { ActuatorComponent } from "./actuator/actuator.component";
import { UsersComponent } from "./users/users.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
  {
    path: 'pages', component: PagesComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always',
    children: [
      { path: 'dashboard', component: DashboardComponent , canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
      { path: 'actuator', component: ActuatorComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
      { path: 'users', component: UsersComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
    ]
  },
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
