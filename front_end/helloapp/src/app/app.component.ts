import { Component} from '@angular/core';

@Component({
    selector: 'my-app',
    template: `<div>
        <h1>Мониторинг приложения <br> Планировщик путешествий</h1>
        <router-outlet></router-outlet>
    </div>`,
    styles: [`
        h1{
            font-size:30px;
            font-family:Verdana;text-align: center;
            margin-top: 25px;
            line-height: 1.1;
            text-transform: uppercase;
            color: #FFF;
            font-family: 'Raleway', 'Helvetica', sans-serif;
            font-weight: 700;
            text-shadow: 1px 1px #196380;
        }
    `]
})
export class AppComponent {}