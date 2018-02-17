import {Routes, RouterModule} from '@angular/router';
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AppComponent }   from './app.component';
import { MonitoringComponent }   from './monitoring.component';
import { FormComponent }   from './form.component';
import { HttpClientModule }   from '@angular/common/http';
import { MonitoringService } from './monitoring.service';
import { RegisterComponent} from './register.component';
import { HttpService} from "./http.service";
import { RegisterService} from "./register.service";
import { CookieService } from "ngx-cookie-service";
import { TokenInterceptor } from "./token.interceptor";
import {DialogComponent} from "./dialog.component";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';




// определение маршрутов
const appRoutes: Routes =[
    { path: '', component: FormComponent},
    { path: 'monitoring', component: MonitoringComponent},
    { path: 'register', component: RegisterComponent}
];

@NgModule({
    imports:      [ BrowserModule,  BrowserAnimationsModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes, {useHash: true})],
    declarations: [ AppComponent,FormComponent, MonitoringComponent, RegisterComponent, DialogComponent ],
    bootstrap:    [ AppComponent ],
    providers: [MonitoringService, HttpService, RegisterService, CookieService, {
        provide: HTTP_INTERCEPTORS,
        useClass: TokenInterceptor,
        multi: true
    }]
})
export class AppModule { }