import { Component } from '@angular/core';
import { MonitoringService } from './monitoring.service';
import { Chart } from 'chart.js';


@Component({
    selector:'monitoring-app',
    templateUrl: './monitoring.component.html',
    styleUrls: ['./monitoring.component.css'],
    styleUrls: ['./monitoring.component.css']
})

export class MonitoringComponent {

    chart: any = []; // This will hold our chart info

    constructor(private _monitoring: MonitoringService) {}

    ngOnInit() {
        this._monitoring.getOverview()
            .subscribe(res => {
                let number_requests_beach = res['list'].map(res => res.number_requests_beach);
                let number_requests_excursions = res['list'].map(res => res.number_requests_excursions);
                let number_requests_sport= res['list'].map(res => res.number_requests_sport);
                let alltimes = res['list'].map(res => res.dt);

                let monitoringTimes = [];
                alltimes.forEach((res) => {
                    let jsdate = new Date(res * 1000);
                    monitoringTimes.push(jsdate.toLocaleTimeString('ru', { year: 'numeric', month: 'short', day: 'numeric' }))
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {   label: 'пляжный отдых',
                                    data: number_requests_beach,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {   label: 'спорт',
                                    data: number_requests_sport,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {   label: 'экскурсии',
                                    data: number_requests_excursions,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Количество запросов по сценариям'
                            },
                            legend: {
                                display: true,
                                position: 'top',
                                padding: 20,
                            },
                            scales: {
                                xAxes: [{
                                    display: true
                                }],
                                yAxes: [{
                                    display: true
                                }],
                            }
                        }
                    });

                })
            })
    }
}