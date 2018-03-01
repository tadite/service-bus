import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {User} from "./model.user";
import { CookieService } from "ngx-cookie-service";



@Injectable()
export class HttpService{

    isLoggedIn = false;

    constructor(private http: HttpClient,
                private cookie: CookieService){ }
    //http://localhost:60489/Home/PostUser  ASP.NET MVC 5
    //http://localhost:8080/angular/setUser.php
    // http://localhost:60820/api/values        ASP NET Wep API 2

    postData(user: User){

        //const body = {name: form.value.username, password: form.value.password};
        //console.log (body);
        this.cookie.deleteAll();
        return this.http.post('/user', user, {responseType: 'text'});
    }
}