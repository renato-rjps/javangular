import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanLoad, Route } from "@angular/router";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

    constructor(private router: Router, private authService: AuthService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.canLoadPage();
    }

    canLoad(route: Route) {
        return this.canLoadPage();
    }

    private canLoadPage(): boolean {
        if (!this.authService.isAuth()) {
            this.router.navigate(["/login"]);
            return false;
        }

        return true;
    }
}