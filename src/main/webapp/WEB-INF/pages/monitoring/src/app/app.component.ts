import { Component} from '@angular/core';

@Component({
    selector: 'my-app',
    template: `<header>
        <div>
          <h1>Мониторинг приложения <br> Планировщик путешествий</h1>
        <router-outlet></router-outlet>
       </div>
    </header>`,
    styles: [`
        header{
            width: 100%;
            height: 100px;
            background: rgba(28, 54, 85, .9);
        }
        div h1{
            font-size:30px;
            font-family:Verdana;
            margin-top: 0px;
            text-align: center;
            padding-top: 15px;
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