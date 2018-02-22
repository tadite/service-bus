import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import {Observable} from "rxjs/Rx";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class MonitoringGuard implements CanActivate{

    constructor(private router: Router,
                private cookie: CookieService){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{
        if (this.cookie.get('current_user') != null) {
              // if (this.cookie.get('current_user')) {
                 // console.log(this.cookie.get('current_user'));
            // logged in so return true
            return true;

        }
            // not logged in so redirect to login page with the return url
            this.router.navigate(['/'], { queryParams: { returnUrl: state.url }});
            return false;
        }
}