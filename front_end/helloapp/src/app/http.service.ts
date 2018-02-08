import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {User} from "./model.user";



@Injectable()
export class HttpService{

    isLoggedIn = false;

    constructor(private http: HttpClient){ }

    //http://localhost:60489/Home/PostUser  ASP.NET MVC 5
    //http://localhost:8080/angular/setUser.php
    // http://localhost:60820/api/values        ASP NET Wep API 2

<<<<<<< HEAD
    postData(user: User){
=======
    postData(form: NgForm){

        const body = {name: form.value.username, password: form.value.password};
        console.log (body);
>>>>>>> 7b791e41c0945e5ce03b8a38c3e1ed8bef7098ba

        //const body = {name: form.value.username, password: form.value.password};
        //console.log (body);

        return this.http.post('/user', user);
    }



}