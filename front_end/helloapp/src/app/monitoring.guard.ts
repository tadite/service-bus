import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import {Observable} from "rxjs/Rx";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class MonitoringGuard implements CanActivate{

    constructor(private router: Router,
                private cookie: CookieService){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{
        const token =this.cookie.get('current_user');
        if (token) {
            // logged in so return true
            return true;

        }
            // not logged in so redirect to login page with the return url
            this.router.navigate(['/'], { queryParams: { returnUrl: state.url }});
            return false;
        }
}