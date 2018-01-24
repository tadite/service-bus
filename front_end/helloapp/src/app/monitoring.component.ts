import { Component} from '@angular/core';

@Component({
    selector: 'monitoring-app',
    template: `<h3>Мониторинг</h3>`,
    styles: [`
        h3{
            font-size:30px;
            font-family:Verdana;text-align: center;
            margin-top: 100px;
            line-height: 1.1;
            text-transform: uppercase;
            color: #FFF;
            font-family: 'Raleway', 'Helvetica', sans-serif;
            font-weight: 700;
            text-shadow: 1px 1px #196380;
        }
    `]
})
export class MonitoringComponent {
}