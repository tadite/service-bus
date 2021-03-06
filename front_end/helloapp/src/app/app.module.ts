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
import {MonitoringGuard} from "./monitoring.guard";
import {RequestComponent} from "./request.component";
import {ResponseComponent} from "./response.component";
import {ShowErrorComponent} from "./showerror.component";
import { LoadingModule } from 'ngx-loading';




// определение маршрутов
const appRoutes: Routes =[
    { path: '', component: FormComponent},
    { path: 'monitoring', component: MonitoringComponent, canActivate: [MonitoringGuard]},
    { path: 'request', component: RequestComponent, canActivate: [MonitoringGuard]},
    { path: 'response', component: ResponseComponent, canActivate: [MonitoringGuard]},
    { path: 'showerror', component: ShowErrorComponent, canActivate: [MonitoringGuard]},
    { path: 'register', component: RegisterComponent, canActivate: [MonitoringGuard]}
];

@NgModule({
    imports:      [ BrowserModule,  BrowserAnimationsModule, FormsModule, LoadingModule, HttpClientModule, RouterModule.forRoot(appRoutes, {useHash: true})],
    declarations: [ AppComponent,FormComponent, MonitoringComponent, RequestComponent, ResponseComponent, ShowErrorComponent, RegisterComponent, DialogComponent ],
    bootstrap:    [ AppComponent ],
    providers: [MonitoringService, HttpService, RegisterService, CookieService, MonitoringGuard, {
        provide: HTTP_INTERCEPTORS,
        useClass: TokenInterceptor,
        multi: true
    }]
})
export class AppModule { }