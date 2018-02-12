import { Component } from '@angular/core';
import { Admin} from './admin';
import {RegisterService} from "./register.service";
import { Router } from "@angular/router";


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
    router: Router;
    emailExist: boolean = false;
    nameExist: boolean = false;

    constructor(private httpService: RegisterService){}

    submit(admin: Admin){
        this.httpService.postDataRegister(admin)
            .subscribe(
                data => {
                    if (data == "ADDED"){
                        this.router.navigate(['/monitoring']);
                    }
                    if (data == "NAME_EXIST"){
                        this.nameExist = true;
                    }
                    if (data == "EMAIL_EXIST"){
                        this.emailExist = true;
                    }
                }
                //error => console.log(error)
            );
    }
}
