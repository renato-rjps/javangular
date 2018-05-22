import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { PreLoaderService } from './shared/pre-loader.service';

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit, AfterViewInit {
  constructor(private authService: AuthService, private preLoader: PreLoaderService) { }

  ngOnInit() {
    this.authService.initAuthListener();
  }

  public ngAfterViewInit(): void {
    this.preLoader.hide();
  }
}
