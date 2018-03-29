import { Component, OnInit } from '@angular/core';
import { MonitoringService } from './monitoring.service';
import { Chart } from 'chart.js';
import 'rxjs/add/operator/map';
import { Router } from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {error} from "util";


@Component({
    selector:'showerror-app',
    templateUrl: './showerror.component.html',
    styleUrls: ['./showerror.component.css']
})

export class ShowErrorComponent implements OnInit {
    showDialog = false;
    public loading = false;
    condition: boolean = true;
    errors: Array<Error>;
    chart: any = []; // This will hold our chart info

    constructor(private _monitoring: MonitoringService,
                private cookie: CookieService,
                private router: Router) {
    }

    ngOnInit() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getErrorMinute()
            .subscribe(res => {
                    this.loading = false;
                    let number_errors = res.map(res => res.errorCount);
                    let number_errors_hotel = res.map(res => res.hotelErrorCount);
                    let number_errors_excursionsTripster = res.map(res => res.excursionsTripsterErrorCount);
                    let number_errors_excursionsWeatlas = res.map(res => res.excursionsWeatlasErrorCount);
                    let number_errors_ticket = res.map(res => res.ticketErrorCount);
                    let number_errors_auto = res.map(res => res.autoErrorCount);
                    let number_errors_country = res.map(res => res.countryErrorCount);
                    let number_errors_coastLiving = res.map(res => res.coastLivingErrorCount);

                    let alltimes = res.map(res => res.time);

                    let monitoringTimes = [];
                    alltimes.forEach((res) => {
                        let jsdate = new Date(res);
                        monitoringTimes.push(jsdate.toLocaleTimeString());

                        this.chart = new Chart('canvas', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'по всем интеграциям',
                                        data: number_errors,
                                        borderColor: "#a8171c",
                                        backgroundColor: "#e26161",
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Количество ошибок"
                                        }
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data:number_errors_hotel,
                                        borderColor: "#3cba9f",
                                        backgroundColor: "#3cba9f"
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_errors_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b"
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_errors_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00"
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_errors_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_errors_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce"
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_errors_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba"
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_errors_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf"
                                    },
                                ]
                            },
                            options: {
                                events: [],
                                title: {
                                    display: true,
                                    fontSize: 20,
                                    padding: 20,
                                    text: 'Ошибки по интеграциям'
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Ошибок по интеграции"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let number_errors = null;
                    let number_errors_hotel = null;
                    let number_errors_excursionsTripster = null;
                    let number_errors_excursionsWeatlas = null;
                    let number_errors_ticket = null;
                    let number_errors_auto = null;
                    let number_errors_country = null;
                    let number_errors_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'по всем интеграциям',
                                    data: number_errors,
                                    borderColor: "#a8171c",
                                    backgroundColor: "#e26161",
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Количество ошибок"
                                    }
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'bar',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'hotels-find-hotels',
                                    data:number_errors_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_errors_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_errors_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_errors_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_errors_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_errors_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
                                    data: number_errors_coastLiving,
                                    borderColor: "#5bb5bf",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Ошибки по интеграциям'
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Ошибок по интеграции"
                                    }
                                }]
                            }
                        }
                    });
                })
    }



    showOverviewHour() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getErrorHour()
            .subscribe(res => {
                    this.loading = false;
                    let number_errors = res.map(res => res.errorCount);
                    let number_errors_hotel = res.map(res => res.hotelErrorCount);
                    let number_errors_excursionsTripster = res.map(res => res.excursionsTripsterErrorCount);
                    let number_errors_excursionsWeatlas = res.map(res => res.excursionsWeatlasErrorCount);
                    let number_errors_ticket = res.map(res => res.ticketErrorCount);
                    let number_errors_auto = res.map(res => res.autoErrorCount);
                    let number_errors_country = res.map(res => res.countryErrorCount);
                    let number_errors_coastLiving = res.map(res => res.coastLivingErrorCount);

                    let alltimes = res.map(res => res.time);

                    let monitoringTimes = [];
                    alltimes.forEach((res) => {
                        let jsdate = new Date(res);
                        monitoringTimes.push(jsdate.toLocaleTimeString());
                        if(typeof this.chart !== "undefined") {
                            this.chart.destroy();
                        }
                        this.chart = new Chart('canvas', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'по всем интеграциям',
                                        data: number_errors,
                                        borderColor: "#a8171c",
                                        backgroundColor: "#e26161",
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Количество ошибок"
                                        }
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data:number_errors_hotel,
                                        borderColor: "#3cba9f",
                                        backgroundColor: "#3cba9f"
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_errors_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b"
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_errors_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00"
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_errors_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_errors_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce"
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_errors_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba"
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_errors_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf"
                                    },
                                ]
                            },
                            options: {
                                events: [],
                                title: {
                                    display: true,
                                    fontSize: 20,
                                    padding: 20,
                                    text: 'Ошибки по интеграциям'
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Ошибок по интеграции"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let number_errors = null;
                    let number_errors_hotel = null;
                    let number_errors_excursionsTripster = null;
                    let number_errors_excursionsWeatlas = null;
                    let number_errors_ticket = null;
                    let number_errors_auto = null;
                    let number_errors_country = null;
                    let number_errors_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'по всем интеграциям',
                                    data: number_errors,
                                    borderColor: "#a8171c",
                                    backgroundColor: "#e26161",
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Количество ошибок"
                                    }
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'bar',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'hotels-find-hotels',
                                    data:number_errors_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_errors_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_errors_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_errors_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_errors_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_errors_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
                                    data: number_errors_coastLiving,
                                    borderColor: "#5bb5bf",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Ошибки по интеграциям'
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Ошибок по интеграции"
                                    }
                                }]
                            }
                        }
                    });
                })
    }

    showOverviewDay() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getErrorDay()
            .subscribe(res => {
                    this.loading = false;
                    let number_errors = res.map(res => res.errorCount);
                    let number_errors_hotel = res.map(res => res.hotelErrorCount);
                    let number_errors_excursionsTripster = res.map(res => res.excursionsTripsterErrorCount);
                    let number_errors_excursionsWeatlas = res.map(res => res.excursionsWeatlasErrorCount);
                    let number_errors_ticket = res.map(res => res.ticketErrorCount);
                    let number_errors_auto = res.map(res => res.autoErrorCount);
                    let number_errors_country = res.map(res => res.countryErrorCount);
                    let number_errors_coastLiving = res.map(res => res.coastLivingErrorCount);

                    let alltimes = res.map(res => res.time);

                    let monitoringTimes = [];
                    alltimes.forEach((res) => {
                        let jsdate = new Date(res);
                        monitoringTimes.push(jsdate.toLocaleDateString());
                        if(typeof this.chart !== "undefined") {
                            this.chart.destroy();
                        }
                        this.chart = new Chart('canvas', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'по всем интеграциям',
                                        data: number_errors,
                                        borderColor: "#a8171c",
                                        backgroundColor: "#e26161",
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Количество ошибок"
                                        }
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data:number_errors_hotel,
                                        borderColor: "#3cba9f",
                                        backgroundColor: "#3cba9f"
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_errors_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b"
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_errors_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00"
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_errors_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_errors_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce"
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_errors_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba"
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_errors_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf"
                                    },
                                ]
                            },
                            options: {
                                events: [],
                                title: {
                                    display: true,
                                    fontSize: 20,
                                    padding: 20,
                                    text: 'Ошибки по интеграциям'
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
                                        display: true,
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Ошибок по интеграции"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let number_errors = null;
                    let number_errors_hotel = null;
                    let number_errors_excursionsTripster = null;
                    let number_errors_excursionsWeatlas = null;
                    let number_errors_ticket = null;
                    let number_errors_auto = null;
                    let number_errors_country = null;
                    let number_errors_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'по всем интеграциям',
                                    data: number_errors,
                                    borderColor: "#a8171c",
                                    backgroundColor: "#e26161",
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Количество ошибок"
                                    }
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'bar',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'hotels-find-hotels',
                                    data:number_errors_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_errors_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_errors_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_errors_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_errors_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_errors_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
                                    data: number_errors_coastLiving,
                                    borderColor: "#5bb5bf",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            tooltips: {
                                enabled: false
                            },
                            title: {
                                display: true,
                                fontSize: 20,
                                padding: 20,
                                text: 'Ошибки по интеграциям'
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Ошибок по интеграции"
                                    }
                                }]
                            }
                        }
                    });
                })
    }

    chartClear(){
        if(typeof this.chart !== "undefined") {
            this.chart.destroy();
        }
    }

    showErrorLog(){
    this.showDialog = true;
        this.loading = true;
        this._monitoring.getErrorLog().subscribe((data: Error[]) => {
                        this.loading = false;
                        this.errors = data;
                        console.log(this.errors);
                    },
                    error => {console.log(error); }
                );
            }


        exitMonitoring() {
        this.cookie.deleteAll();
        this.router.navigate(['/']);
    }

}


export class Error{
    constructor(
        public time: string,
        public name: string,
        public url: string,
        public description: string,
        ) { }

}
