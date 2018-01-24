import {Routes, RouterModule} from '@angular/router';
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { MonitoringComponent }   from './monitoring.component';
import { FormComponent }   from './form.component';
import { HttpClientModule }   from '@angular/common/http';

// определение маршрутов
const appRoutes: Routes =[
    { path: '', component: FormComponent},
    { path: 'monitoring', component: MonitoringComponent}
];

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes)],
    declarations: [ AppComponent,FormComponent, MonitoringComponent ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }