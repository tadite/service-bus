import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
import { HttpService} from './http.service';
import { User } from "./model.user";
import { Router} from "@angular/router";
import {userInfo} from "os";
//import { HttpClient} from '@angular/common/http';


export class Admin{
    name: string;
    password: string;
}

@Component({
    selector: 'form-app,',
    styleUrls: ['./form.component.css'],
    template: `<form #myForm="ngForm" novalidate (ngSubmit)="onSubmit(myForm)">
        <div class="main">
            <h1>АВТОРИЗАЦИЯ</h1>
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

    router: Router;

    constructor(private httpService: HttpService){}
    //admin: Admin=new Admin(); // данные вводимого пользователя

    user: User=new User();
    receivedAdmin: User; // полученный пользователь
    done: boolean = false;

    onSubmit(form: NgForm){
        //console.log(form);
        this.user.username = form.value.username;
        this.user.password = form.value.password;

        this.httpService.postData(this.user)
            .subscribe(data => {
                localStorage.setItem('current_user', JSON.stringify(data));
                this.router.navigate(['/monitoring']);
            }
                // error => console.log(error)
            );
    }
}

