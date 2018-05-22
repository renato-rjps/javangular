import { Injectable } from "@angular/core";


const TOKEN_KEY = 'AuthToken';

@Injectable({
    providedIn: 'root'
})
export class TokenService {
    TOKEN_KEY = TOKEN_KEY;

    signOut() {
        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.clear();
    }

    saveToken(token: string) {
        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.setItem(TOKEN_KEY, token);
    }

    getToken(): string {
        return sessionStorage.getItem(TOKEN_KEY);
    }
    
}