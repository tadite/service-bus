import { Injectable, Injector} from "@angular/core";
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor
} from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { HttpService } from "./http.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(public injector: Injector){}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const auth = this.injector.get(HttpService);
        const authRequest = request.clone({
            setHeaders: {
                Authorization: `Bearer ${auth.getToken()}`
            }
        });

        return next.handle(authRequest);
    }
}
