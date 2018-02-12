import { Component} from '@angular/core';
import { NgForm} from '@angular/forms';
import { HttpService} from './http.service';
import { Admin} from './admin';


@Component({
    selector: 'form-app,',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.css'],
    providers: [HttpService]
})



export class FormComponent{

    constructor(private httpService: HttpService){}
    //admin: Admin=new Admin(); // данные вводимого пользователя
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

