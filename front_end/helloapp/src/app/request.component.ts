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

                        this.chart = new Chart('canvas', {
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data: time_requests_hotel,
                                        backgroundColor: "#3cba9f",
                                        borderColor: "#3cba9f",

                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b",
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00",
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: time_requests_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"

                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce",
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba",
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: time_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf",
                                    },
                                ]
                            },
                            options: {
                                events: [],

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
                                        barPercentage: 1,
                                        categoryPercentage: 0.6
                                    }],
                                    yAxes: [{
                                        display: true,
                                        ticks: {
                                            min: 0
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Среднее время, мс"}
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
                                        label: 'hotels-find-hotels',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        fill: false
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                    label: 'hotels-find-hotels',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
                                    data: time_requests_coastLiving,
                                    borderColor: "#5bb5bf",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            events: [],
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
                                        labelString: "Среднее время, мс"}
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
                                    label: 'hotels-find-hotels',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
                                    data: number_requests_coastLiving,
                                    borderColor: "#5bb5bf",
                                    fill: false
                                },
                            ]
                        },
                        options: {
                            events: [],
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
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data: time_requests_hotel,
                                        backgroundColor: "#3cba9f",
                                        borderColor: "#3cba9f",

                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b",
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00",
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: time_requests_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"

                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce",
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba",
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: time_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf",
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                        barPercentage: 1,
                                        categoryPercentage: 0.6
                                    }],
                                    yAxes: [{
                                        display: true,
                                        ticks: {
                                            min: 0
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Среднее время, мс"}
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
                                        label: 'hotels-find-hotels',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        fill: false
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                    label: 'hotels-find-hotels',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
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
                                        labelString: "Среднее время, мс"}
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
                                    label: 'hotels-find-hotels',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
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


    showOverviewDay() {
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
                        if(typeof this.chart !== "undefined") {
                            this.chart.destroy();
                        }
                        this.chart = new Chart('canvas', {
                            type: 'bar',
                            data: {
                                labels: monitoringTimes,
                                datasets: [
                                    {
                                        label: 'hotels-find-hotels',
                                        data: time_requests_hotel,
                                        backgroundColor: "#3cba9f",
                                        borderColor: "#3cba9f",

                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: time_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        backgroundColor: "#7cef8b",
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: time_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        backgroundColor: "#ffcc00",
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: time_requests_ticket,
                                        borderColor: "#ef8426",
                                        backgroundColor: "#ef8426"

                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: time_requests_auto,
                                        borderColor: "#7efcce",
                                        backgroundColor: "#7efcce",
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: time_requests_country,
                                        borderColor: "#3c4aba",
                                        backgroundColor: "#3c4aba",
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: time_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        backgroundColor: "#5bb5bf",
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                        barPercentage: 1,
                                        categoryPercentage: 0.6
                                    }],
                                    yAxes: [{
                                        display: true,
                                        ticks: {
                                            min: 0
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Среднее время, мс"}
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
                                        label: 'hotels-find-hotels',
                                        data: number_requests_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'tripster-excursion',
                                        data: number_requests_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'weatlas-get-excursions',
                                        data: number_requests_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'air-tickets-search',
                                        data: number_requests_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'auto-tickets-find-cars',
                                        data: number_requests_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'vk-get-countries-city',
                                        data: number_requests_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'coast-of-living',
                                        data: number_requests_coastLiving,
                                        borderColor: "#5bb5bf",
                                        fill: false
                                    },
                                ]
                            },
                            options: {
                                events: [],
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
                                    label: 'hotels-find-hotels',
                                    data: time_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: time_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: time_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: time_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: time_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: time_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
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
                                        labelString: "Среднее время, мс"}
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
                                    label: 'hotels-find-hotels',
                                    data: number_requests_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'tripster-excursion',
                                    data: number_requests_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'weatlas-get-excursions',
                                    data: number_requests_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'air-tickets-search',
                                    data: number_requests_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'auto-tickets-find-cars',
                                    data: number_requests_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'vk-get-countries-city',
                                    data: number_requests_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'coast-of-living',
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

    chartClear(){
        if(typeof this.chart !== "undefined") {
            this.chart.destroy();
        }
    }

    exitMonitoring() {
        this.cookie.deleteAll();
        this.router.navigate(['/']);
    }

}