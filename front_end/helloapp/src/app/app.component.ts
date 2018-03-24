import { Component} from '@angular/core';


@Component({
    selector: 'my-app',
    template: `<header>
        <div class= "row hed">
            
            <div class="col-xs-5 col-md-5">
              <img style="position:absolute;left:20px;top:15px; width: 44px; height: 40px; border-radius: 20px; " src="../../css/log4.jpg">
              <h1>Service   <br> Bus   </h1>
            </div>
            <div class="col-xs-7 col-md-7">
                <h2> Monitoring </h2>
            </div>
        
       </div>
        <router-outlet></router-outlet>
    </header>`
    ,
    styles: [`
        header{
            width: 100%;
            height: 70px;
            background: rgba(28, 54, 85, .8);
        }
        div h1{
            font-size:20px;
            margin-top: 0px;
            text-align: left;
            padding-top: 15px;
            padding-left: 60px;
            line-height: 1.1;
            text-transform: uppercase;
            color: #FFF;
            font-family: 'Raleway', 'Helvetica', sans-serif;
            font-style:oblique;
            font-weight: 400;
            text-shadow: 1px 1px #196380;
        }
        div h2{
            font-size:25px;
            margin-top: 0px;
            text-align: left;
            padding-top: 20px;
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