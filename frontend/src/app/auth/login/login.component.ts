import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  loginForm: FormGroup;
  errorMessage: string;
  hasErrorSubs: Subscription;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.buildForm();
    this.hasErrorSubs = this.authService.authError.subscribe(error => this.errorMessage = error);
  }

  onSubmit() {
    this.errorMessage = null;
    this.authService.login({
      username: this.loginForm.value.email,
      password: this.loginForm.value.password
    });
  }

  private buildForm() {
    this.loginForm = new FormGroup({
      email: new FormControl('', { validators: [Validators.required, Validators.email] }),
      password: new FormControl('', { validators: [Validators.required] }),
    });
  }

  ngOnDestroy() {
    if (this.hasErrorSubs) {
      this.hasErrorSubs.unsubscribe();
    }
  }

}
