import { Component } from '@angular/core';
import { Admin} from './admin';
import {RegisterService} from "./register.service";


@Component({
    selector: 'register-app',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
    providers: [RegisterService]
})

export class RegisterComponent {


    name: string;
    password: string;
    eMail: string;

    admin: Admin = new  Admin(this.name, this.eMail, this.password);
    receivedAdmin:  Admin;
    done: boolean = false;

    constructor(private httpService: RegisterService){}

    submit(admin: Admin){
        this.httpService.postDataRegister(admin)
            .subscribe(
                (data: Admin) => {this.receivedAdmin=data; this.done=true;}
                //error => console.log(error)
            );
    }
}
