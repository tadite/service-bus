import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
import { HttpService} from './http.service';
import { User } from "./model.user";
import { Router} from "@angular/router";
import {userInfo} from "os";
//import { HttpClient} from '@angular/common/http';
import { Admin} from './admin';
import {ForkJoinObservable} from "rxjs/observable/ForkJoinObservable";

//import { HttpClient} from '@angular/common/http';


@Component({
    selector: 'form-app,',
    styleUrls: ['./form.component.css'],
    template: `<form #myForm="ngForm" (ngSubmit)="onSubmit(myForm)" novalidate>
        <div class="main">
            <h1>АВТОРИЗАЦИЯ</h1>
            <div [hidden]="incorrect == false" class="alert alert-danger" style="font-size: small">
                Username or password is incorrect
            </div>
            <div class="form-group">
                <label>Имя</label>
                <input class="form-control" name="username" ngModel required />
            </div>
            <div class="form-group">
                <label>Пароль</label>
                <input type="password" class="form-control" name="password" ngModel required />
            </div>
            <div class="form-group">
                <button [disabled]="myForm.invalid" class="btn btn-default">
                    <nav>
                        Войти
                    </nav>
                </button>
            </div>
        </div>
    </form>`,
    providers: [HttpService]
})



export class FormComponent {
    /*admin: Admin=new Admin(); // данные вводимого пользователя

     receivedAdmin: Admin; // полученный пользователь
     done: boolean = false;
     constructor(private httpService: HttpService){}
     onSubmit(form: NgForm){
     this.httpService.postData(form)
     .subscribe(
     (data: Admin) => {this.receivedAdmin=data; this.done=true;}
     // error => console.log(error)
     );
     }*/

    constructor(private httpService: HttpService, private router: Router){}
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
                this.router.navigate(['/monitoring']);
                console.log(localStorage.getItem('current_user'));
            },
                error => {
                    if (error.status == 401){
                        this.incorrect = true;
                    }
                }
            );
    }
}

