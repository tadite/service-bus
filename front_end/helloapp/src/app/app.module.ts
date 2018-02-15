import {Routes, RouterModule} from '@angular/router';
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { MonitoringComponent }   from './monitoring.component';
import { FormComponent }   from './form.component';
import { HttpClientModule }   from '@angular/common/http';
import { MonitoringService } from './monitoring.service';
import { RegisterComponent} from './register.component';
import { HttpService} from "./http.service";
import { RegisterService} from "./register.service";




// определение маршрутов
const appRoutes: Routes =[
    { path: '', component: FormComponent},
    { path: 'monitoring', component: MonitoringComponent},
    { path: 'register', component: RegisterComponent}
];

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes)],
    declarations: [ AppComponent,FormComponent, MonitoringComponent, RegisterComponent ],
    bootstrap:    [ AppComponent ],
    providers: [MonitoringService, HttpService, RegisterService]
})
export class AppModule { }