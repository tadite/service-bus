import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
import { HttpService} from './http.service';
//import { HttpClient} from '@angular/common/http';


export class Admin{
    name: string;
    password: string;
}

@Component({
    selector: 'my-app',
    styleUrls: ['./app.component.css'],
    template: `<form #myForm="ngForm" novalidate (ngSubmit)="onSubmit(myForm)">
        <div class="main">
            <h1>АВТОРИЗАЦИЯ</h1>
        <div class="form-group">
            <label>Имя</label>
            <input class="form-control" name="name" ngModel required />
        </div>
        <div class="form-group">
            <label>Пароль</label>
            <input class="form-control" name="password" ngModel required />
        </div>
        <div class="form-group">
            <button [disabled]="myForm.invalid" class="btn btn-default" (click)="onSubmit(myForm)">Войти</button>
        </div>
        </div>
    </form>`,
    providers: [HttpService]
})



export class AppComponent {
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
    constructor(private httpService: HttpService){}
    admin: Admin=new Admin(); // данные вводимого пользователя

    receivedAdmin: Admin; // полученный пользователь
    done: boolean = false;

   onSubmit(form: NgForm){
    //console.log(form);
       this.httpService.postData(form)
           .subscribe(
               (data: Admin) => {this.receivedAdmin=data; this.done=true;}
               // error => console.log(error)
           );

   }
}