import { Component, OnInit } from '@angular/core';
import { MonitoringService } from './monitoring.service';
import { Chart } from 'chart.js';
import 'rxjs/add/operator/map';
import { Router } from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {error} from "util";


@Component({
    selector:'request-app',
    templateUrl: './request.component.html',
    styleUrls: ['./request.component.css']
})

export class RequestComponent implements OnInit {
    public loading = false;
    condition: boolean = true;

    chart: any = []; // This will hold our chart info

    constructor(private _monitoring: MonitoringService,
                private cookie: CookieService,
                private router: Router) {
    }

    ngOnInit() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getOverviewDay()
            .subscribe(res => {
                    this.loading = false;
                    let time_requests_hotel = res.map(res => res.avgTimeHotelRequest);
                    let time_requests_excursionsTripster = res.map(res => res.avgTimeExcursionTripsterRequest);
                    let time_requests_excursionsWeatlas= res.map(res => res.avgTimeExcursionsWeatlasRequest);
                    let time_requests_ticket = res.map(res => res.avgTimeTicketRequest);
                    let time_requests_auto = res.map(res => res.avgTimeAutoRequest);
                    let time_requests_country= res.map(res => res.avgTimeCountryRequest);
                    let time_requests_coastLiving = res.map(res => res.avgTimeCoastLivingRequest);

                    let number_requests_hotel = res.map(res => res.hotelRequestCount);
                    let number_requests_excursionsTripster = res.map(res => res.excursionTripsterRequestCount);
                    let number_requests_excursionsWeatlas = res.map(res => res.excursionWeatlasRequestCount);
                    let number_requests_ticket = res.map(res => res.ticketRequestCount);
                    let number_requests_auto = res.map(res => res.autoRequestCount);
                    let number_requests_country = res.map(res => res.countryRequestCount);
                    let number_requests_coastLiving = res.map(res => res.coastLivingRequestCount);
                    let alltimes = res.map(res => res.time);

                    let monitoringTimes = [];
                    alltimes.forEach((res) => {
                        let jsdate = new Date(res);
                        monitoringTimes.push(jsdate.toLocaleDateString());

                        this.chart = new Chart('canvas', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'отели',
                                        data: time_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_requests_coastLiving,
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
                                    text: 'Среднее время запросов по интеграциям'
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
                                            labelString: "Среднее время в секундах"}
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'отели',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: number_requests_coastLiving,
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
                                    text: 'Количество запросов по интеграциям'
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
                                            labelString: "Запросов по интеграции в день"}
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_requests_hotel = null;
                    let time_requests_excursionsTripster = null;
                    let time_requests_excursionsWeatlas= null;
                    let time_requests_ticket = null;
                    let time_requests_auto = null;
                    let time_requests_country= null;
                    let time_requests_coastLiving = null;

                    let number_requests_hotel = null;
                    let number_requests_excursionsTripster = null;
                    let number_requests_excursionsWeatlas = null;
                    let number_requests_ticket = null;
                    let number_requests_auto = null;
                    let number_requests_country = null;
                    let number_requests_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_requests_coastLiving,
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
                                text: 'Среднее время запросов по интеграциям'
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
                                        labelString: "Среднее время в секундах"}
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: number_requests_coastLiving,
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
                                text: 'Количество запросов по интеграциям'
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
                                        labelString: "Запросов по интеграции в день"}
                                }]
                            }
                        }
                    });
                })
    }


    showOverviewHour() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getOverviewHour()
            .subscribe(res => {
                    this.loading = false;
                    let time_requests_hotel = res.map(res => res.avgTimeHotelRequest);
                    let time_requests_excursionsTripster = res.map(res => res.avgTimeExcursionTripsterRequest);
                    let time_requests_excursionsWeatlas= res.map(res => res.avgTimeExcursionsWeatlasRequest);
                    let time_requests_ticket = res.map(res => res.avgTimeTicketRequest);
                    let time_requests_auto = res.map(res => res.avgTimeAutoRequest);
                    let time_requests_country= res.map(res => res.avgTimeCountryRequest);
                    let time_requests_coastLiving = res.map(res => res.avgTimeCoastLivingRequest);

                    let number_requests_hotel = res.map(res => res.hotelRequestCount);
                    let number_requests_excursionsTripster = res.map(res => res.excursionTripsterRequestCount);
                    let number_requests_excursionsWeatlas = res.map(res => res.excursionWeatlasRequestCount);
                    let number_requests_ticket = res.map(res => res.ticketRequestCount);
                    let number_requests_auto = res.map(res => res.autoRequestCount);
                    let number_requests_country = res.map(res => res.countryRequestCount);
                    let number_requests_coastLiving = res.map(res => res.coastLivingRequestCount);
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
                                        label: 'отели',
                                        data: time_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_requests_coastLiving,
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
                                    text: 'Среднее время запросов по интеграциям'
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
                                            labelString: "Среднее время в секундах"}
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'отели',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: number_requests_coastLiving,
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
                                    text: 'Количество запросов по интеграциям'
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
                                            labelString: "Запросов по интеграции в час"}
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_requests_hotel = null;
                    let time_requests_excursionsTripster = null;
                    let time_requests_excursionsWeatlas= null;
                    let time_requests_ticket = null;
                    let time_requests_auto = null;
                    let time_requests_country= null;
                    let time_requests_coastLiving = null;

                    let number_requests_hotel = null;
                    let number_requests_excursionsTripster = null;
                    let number_requests_excursionsWeatlas = null;
                    let number_requests_ticket = null;
                    let number_requests_auto = null;
                    let number_requests_country = null;
                    let number_requests_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_requests_coastLiving,
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
                                text: 'Среднее время запросов по интеграциям'
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
                                        labelString: "Среднее время в секундах"}
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: number_requests_coastLiving,
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
                                text: 'Количество запросов по интеграциям'
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
                                        labelString: "Запросов по интеграции в час"}
                                }]
                            }
                        }
                    });
                })
    }


    showOverviewMinute() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getOverviewMinute()
            .subscribe(res => {
                    this.loading = false;
                    let time_requests_hotel = res.map(res => res.avgTimeHotelRequest);
                    let time_requests_excursionsTripster = res.map(res => res.avgTimeExcursionTripsterRequest);
                    let time_requests_excursionsWeatlas= res.map(res => res.avgTimeExcursionsWeatlasRequest);
                    let time_requests_ticket = res.map(res => res.avgTimeTicketRequest);
                    let time_requests_auto = res.map(res => res.avgTimeAutoRequest);
                    let time_requests_country= res.map(res => res.avgTimeCountryRequest);
                    let time_requests_coastLiving = res.map(res => res.avgTimeCoastLivingRequest);

                    let number_requests_hotel = res.map(res => res.hotelRequestCount);
                    let number_requests_excursionsTripster = res.map(res => res.excursionTripsterRequestCount);
                    let number_requests_excursionsWeatlas = res.map(res => res.excursionWeatlasRequestCount);
                    let number_requests_ticket = res.map(res => res.ticketRequestCount);
                    let number_requests_auto = res.map(res => res.autoRequestCount);
                    let number_requests_country = res.map(res => res.countryRequestCount);
                    let number_requests_coastLiving = res.map(res => res.coastLivingRequestCount);
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
                                        label: 'отели',
                                        data: time_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_requests_coastLiving,
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
                                    text: 'Среднее время запросов по интеграциям'
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
                                            labelString: "Среднее время в секундах"}
                                    }],
                                }
                            }
                        });
                        this.chart = new Chart('canvas2', {
                            type: 'line',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'отели',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: number_requests_coastLiving,
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
                                    text: 'Количество запросов по интеграциям'
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
                                            labelString: "Запросов по интеграции в минуту"}
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_requests_hotel = null;
                    let time_requests_excursionsTripster = null;
                    let time_requests_excursionsWeatlas= null;
                    let time_requests_ticket = null;
                    let time_requests_auto = null;
                    let time_requests_country= null;
                    let time_requests_coastLiving = null;

                    let number_requests_hotel = null;
                    let number_requests_excursionsTripster = null;
                    let number_requests_excursionsWeatlas = null;
                    let number_requests_ticket = null;
                    let number_requests_auto = null;
                    let number_requests_country = null;
                    let number_requests_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_requests_coastLiving,
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
                                text: 'Среднее время запросов по интеграциям'
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
                                        labelString: "Среднее время в секундах"}
                                }],
                            }
                        }
                    });
                    this.chart = new Chart('canvas2', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: number_requests_coastLiving,
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
                                text: 'Количество запросов по интеграциям'
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
                                        labelString: "Запросов по интеграции в минуту"}
                                }]
                            }
                        }
                    });
                })
    }


    exitMonitoring() {
        this.cookie.deleteAll();
        this.router.navigate(['/']);
    }

}