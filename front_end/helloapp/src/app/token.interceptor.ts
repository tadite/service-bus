import { Injectable, Injector} from "@angular/core";
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { CookieService } from "ngx-cookie-service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private cookie: CookieService){}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = this.cookie.get('current_user');

        if (token) {
            const authRequest = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            });

            return next.handle(authRequest);
        } else {
            return next.handle(request);
        }
    }
}
