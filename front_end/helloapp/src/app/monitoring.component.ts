import { Component } from '@angular/core';
import { MonitoringService } from './monitoring.service';
import { Chart } from 'chart.js';
import 'rxjs/add/operator/map';


@Component({
    selector:'monitoring-app',
    templateUrl: './monitoring.component.html',
    styleUrls: ['./monitoring.component.css']
})

export class MonitoringComponent {

    chart: any = []; // This will hold our chart info

    constructor(private _monitoring: MonitoringService) {}


    ngOnInit() {
        this._monitoring.getOverview()
        .subscribe(res => {
            let number_requests = res['list'].map(res => res.number_requests);
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
                            {   label: 'по всем сценариям',
                                data: number_requests,
                                borderColor: "#3c4aba",
                                backgroundColor: "#aab0e0",
                            },
                        ]
                    },
                    options: {
                        title: {
                            display: true,
                            fontSize: 20,
                            padding: 20,
                            text: 'Общее количество запросов'
                        },
                        legend: {
                            display: true,
                            position: 'top'
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
                this.chart = new Chart('canvas2', {
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
                            position: 'top'
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

    showRequest() {
        this._monitoring.getOverview()
            .subscribe(res => {
                let time_requests_beach = res['list'].map(res => res.time_requests_beach);
                let time_requests_excursions = res['list'].map(res => res.time_requests_excursions);
                let time_requests_sport= res['list'].map(res => res.time_requests_sport);
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
                                    data: time_requests_beach,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {   label: 'спорт',
                                    data: time_requests_sport,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {   label: 'экскурсии',
                                    data: time_requests_excursions,
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
                                text: 'Среднее время запросов по сценариям'
                            },
                            legend: {
                                display: true,
                                position: 'top'
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
                    this.chart = new Chart('canvas2', {
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
                                position: 'top'
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

    showResponse() {
        this._monitoring.getOverview()
            .subscribe(res => {
                let time_responses_beach = res['list'].map(res => res.time_responses_beach);
                let time_responses_excursions = res['list'].map(res => res.time_responses_excursions);
                let time_responses_sport= res['list'].map(res => res.time_responses_sport);
                let average_message_size_beach = res['list'].map(res => res.average_message_size_beach);
                let average_message_size_excursions = res['list'].map(res => res.average_message_size_excursions);
                let average_message_size_sport= res['list'].map(res => res.average_message_size_sport);
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
                                    data: time_responses_beach,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {   label: 'спорт',
                                    data: time_responses_sport,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {   label: 'экскурсии',
                                    data: time_responses_excursions,
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
                                text: 'Среднее время ответов по сценариям'
                            },
                            legend: {
                                display: true,
                                position: 'top'
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
                    this.chart = new Chart('canvas2', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {   label: 'пляжный отдых',
                                    data: average_message_size_beach,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {   label: 'спорт',
                                    data: average_message_size_sport,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {   label: 'экскурсии',
                                    data: average_message_size_excursions,
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
                                text: 'Средний размер сообщений по сценариям, количество знаков'
                            },
                            legend: {
                                display: true,
                                position: 'top'
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
    showErrors() {
        this._monitoring.getOverview()
            .subscribe(res => {
                let number_errors = res['list'].map(res => res.number_errors);
                let number_errors_beach = res['list'].map(res => res.number_errors_beach);
                let number_errors_excursions = res['list'].map(res => res.number_errors_excursions);
                let number_errors_sport= res['list'].map(res => res.number_errors_sport);
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
                                {   label: 'по всем сценариям',
                                    data: number_errors,
                                    borderColor: "#a8171c",
                                    backgroundColor: "#e26161",
                                },
                            ]
                        },
                        options: {
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Ошибки'
                            },
                            legend: {
                                display: true,
                                position: 'top'
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
                    this.chart = new Chart('canvas2', {
                        type: 'bar',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {   label: 'пляжный отдых',
                                    data: number_errors_beach,
                                    backgroundColor: "#95c6bc",
                                    borderColor: "#95c6bc",
                                    fill: false,
                                },
                                {   label: 'спорт',
                                    data: number_errors_sport,
                                    backgroundColor:"#90ed9c",
                                    borderColor: "#90ed9c",
                                    fill: false
                                },
                                {   label: 'экскурсии',
                                    data: number_errors_excursions,
                                    backgroundColor:"#edce55",
                                    borderColor: "#edce55",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Ошибки по сценариям'
                            },
                            legend: {
                                display: true,
                                position: 'top'
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