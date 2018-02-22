import { Component} from '@angular/core';


@Component({
    selector: 'my-app',
    template: `<header>
        <div>
            <img style="position:absolute;left:10px;top:10px; width: 80px; height: 80px; border-radius: 40px; " src="../../css/log4.jpg">
          <h1>Планировщик путешествий <br> мониторинг</h1>
        <router-outlet></router-outlet>
       </div>
    </header>`
    ,
    styles: [`
        header{
            width: 100%;
            height: 100px;
            background: rgba(28, 54, 85, .9);
        }
        div h1{
            font-size:20px;
            margin-top: 0px;
            text-align: left;
            padding-top: 15px;
            padding-left: 100px;
            line-height: 1.1;
            text-transform: uppercase;
            color: #FFF;
            font-family: 'Raleway', 'Helvetica', sans-serif;
            font-style:oblique;
            font-weight: 400;
            text-shadow: 1px 1px #196380;
        }
    `]
})

export class AppComponent{
}