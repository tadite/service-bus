import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
import { HttpService} from './http.service';
import { User } from "./model.user";
import { Router} from "@angular/router";
import { CookieService } from "ngx-cookie-service";
import {userInfo} from "os";
//import { HttpClient} from '@angular/common/http';
import { Admin} from './admin';
import {ForkJoinObservable} from "rxjs/observable/ForkJoinObservable";


@Component({
    selector: 'form-app,',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css'],
    providers: [HttpService]
})



export class FormComponent{


    constructor(private httpService: HttpService,
                private router: Router,
                private cookie: CookieService){}
    //admin: Admin=new Admin(); // данные вводимого пользователя

    user: User=new User();
    receivedAdmin: User; // полученный пользователь

    done: boolean = false;
    incorrect: boolean = false;


    onSubmit(form: NgForm){
        //console.log(form);
        this.user.username = form.value.username;
        this.user.password = form.value.password;

        this.httpService.postData(this.user)
            .subscribe(data => {
                localStorage.setItem('current_user', JSON.stringify(data));
                this.cookie.set('current_user', data);
                this.router.navigate(['/monitoring']);
                 //console.log(this.cookie.get('current_user'));
            },
                error => {
                    if (error.status == 401){
                        this.incorrect = true;
                    }
                }
            );
    }
}

