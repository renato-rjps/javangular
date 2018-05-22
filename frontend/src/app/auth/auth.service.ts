import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenService } from "./token.service";
import { Credentials, Token, User } from "./auth.model";

import { environment } from '../../environments/environment'

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private isAuthenticated = false;
    private baseUrl: string = environment.backendUrl;

    authError = new Subject<string>();
    private authState = new Subject<void>();

    constructor(
        private router: Router,
        private httpClient: HttpClient,
        private tokenService: TokenService) { }


    initAuthListener() {
        // Evento que fica escutando o status da authenticação
        this.authState.subscribe(() => {
            const token = this.tokenService.getToken();
            if (token) {
                this.isAuthenticated = true;
                this.router.navigate(['/pages/dashboard']);
            } else {
                this.isAuthenticated = false;
                this.router.navigate(['/login']);
            }
        });

        // Força a execução na primeira vez;
        this.authState.next();
    }

    logout() {
        this.tokenService.signOut();
        this.authState.next();
    }

    login(credentials: Credentials) {
        this.httpClient
            .post(`${this.baseUrl}/auth/login`, credentials)
            .subscribe(
                (response: Token) => {
                    this.tokenService.saveToken(response.token);
                    this.authState.next();
                },
                error => {
                    if (error.status === 400) {
                        this.authError.next('Usuário ou senha inválido');
                    } else {
                        this.authError.next('Ocorreu um problema e seu login não pode ser efetuado.Tente novamente mais tarde. Obrigado!');
                    }
                });
    }

    isAuth() {
        return this.isAuthenticated;
    }

}