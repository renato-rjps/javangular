import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { PagesComponent } from './pages/pages.component';

const routes: Routes = [
  { path: 'pages', canLoad: [AuthGuard], runGuardsAndResolvers: 'always', loadChildren: './pages/pages.module#PagesModule' },
  { path: 'login', component: LoginComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { useHash: true })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
