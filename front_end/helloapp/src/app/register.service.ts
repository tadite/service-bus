import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Admin} from './admin';


@Injectable()
export class RegisterService{

    constructor(private http: HttpClient){ }

    //http://localhost:60489/Home/PostUser  ASP.NET MVC 5
    //http://localhost:8080/angular/setUser.php
    // http://localhost:60820/api/values        ASP NET Wep API 2


    postDataRegister(admin: Admin){
        const body = {login: admin.name, email: admin.eMail, password: admin.password};
        console.log (body);

        //const myHeaders = new HttpHeaders().set('Authorization', 'my-auth-token');

        //return this.http.post('http://localhost:60820/api/values', user, {headers:myHeaders});
        return this.http.post('/register', body, {responseType: 'text'});
    }


}