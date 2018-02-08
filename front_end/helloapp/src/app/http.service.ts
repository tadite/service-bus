import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from "@angular/forms";



@Injectable()
export class HttpService{

    constructor(private http: HttpClient){ }

    //http://localhost:60489/Home/PostUser  ASP.NET MVC 5
    //http://localhost:8080/angular/setUser.php
    // http://localhost:60820/api/values        ASP NET Wep API 2

    postData(form: NgForm){

        const body = {name: form.value.name, password: form.value.password};
        console.log (body);

        return this.http.post('http://travell/', body);

    }



}