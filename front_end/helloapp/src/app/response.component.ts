import { Component, OnInit } from '@angular/core';
import { MonitoringService } from './monitoring.service';
import { Chart } from 'chart.js';
import 'rxjs/add/operator/map';
import { Router } from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {error} from "util";


@Component({
    selector:'response-app',
    templateUrl: './response.component.html',
    styleUrls: ['./response.component.css']
})

export class ResponseComponent implements OnInit {
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
        this._monitoring.getResponseDay()
            .subscribe(res => {
                    this.loading = false;
                    let time_responses_hotel = res.map(res => res.avgTimeHotelResponse);
                    let time_responses_excursionsTripster = res.map(res => res.avgTimeExcursionsTripsterResponse);
                    let time_responses_excursionsWeatlas = res.map(res => res.avgTimeExcursionsWeatlasResponse);
                    let time_responses_ticket = res.map(res => res.avgTimeTicketResponse);
                    let time_responses_auto = res.map(res => res.avgTimeAutoResponse);
                    let time_responses_country = res.map(res => res.avgTimCountryResponse);
                    let time_responses_coastLiving = res.map(res => res.avgTimeCoastLivingResponse);

                    let average_message_size_hotel = res.map(res => res.avgSizeHotelResponse);
                    let average_message_size_excursionsTripster = res.map(res => res.avgSizeExcursionsTripsterResponse);
                    let average_message_size_excursionsWeatlas = res.map(res => res.avgSizeExcursionsWeatlasResponse);
                    let average_message_size_ticket = res.map(res => res.avgSizeTicketResponse);
                    let average_message_size_auto = res.map(res => res.avgSizeAutoResponse);
                    let average_message_size_country = res.map(res => res.avgSizeCountryResponse);
                    let average_message_size_coastLiving = res.map(res => res.avgSizeCoastLivingResponse);

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
                                        data: time_responses_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_responses_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_responses_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_responses_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_responses_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_responses_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_responses_coastLiving,
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
                                    text: 'Среднее время ответов по интеграциям'
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
                                            labelString: "Среднее время ответа в секундах"
                                        }
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
                                        data: average_message_size_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: average_message_size_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: average_message_size_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: average_message_size_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: average_message_size_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: average_message_size_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: average_message_size_coastLiving,
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
                                    text: 'Средний размер сообщений по интеграциям'
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
                                            labelString: "Количество знаков"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_responses_hotel = null;
                    let time_responses_excursionsTripster = null;
                    let time_responses_excursionsWeatlas = null;
                    let time_responses_ticket = null;
                    let time_responses_auto = null;
                    let time_responses_country = null;
                    let time_responses_coastLiving = null;

                    let average_message_size_hotel = null;
                    let average_message_size_excursionsTripster = null;
                    let average_message_size_excursionsWeatlas = null;
                    let average_message_size_ticket = null;
                    let average_message_size_auto = null;
                    let average_message_size_country = null;
                    let average_message_size_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_responses_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_responses_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_responses_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_responses_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_responses_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_responses_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_responses_coastLiving,
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Среднее время ответа в секундах"
                                    }
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
                                    data: average_message_size_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: average_message_size_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: average_message_size_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: average_message_size_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: average_message_size_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: average_message_size_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: average_message_size_coastLiving,
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
                                text: 'Средний размер сообщений по интеграциям'
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
                                        labelString: "Количество знаков"
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
        this._monitoring.getResponseHour()
            .subscribe(res => {
                    this.loading = false;
                    let time_responses_hotel = res.map(res => res.avgTimeHotelResponse);
                    let time_responses_excursionsTripster = res.map(res => res.avgTimeExcursionsTripsterResponse);
                    let time_responses_excursionsWeatlas = res.map(res => res.avgTimeExcursionsWeatlasResponse);
                    let time_responses_ticket = res.map(res => res.avgTimeTicketResponse);
                    let time_responses_auto = res.map(res => res.avgTimeAutoResponse);
                    let time_responses_country = res.map(res => res.avgTimCountryResponse);
                    let time_responses_coastLiving = res.map(res => res.avgTimeCoastLivingResponse);

                    let average_message_size_hotel = res.map(res => res.avgSizeHotelResponse);
                    let average_message_size_excursionsTripster = res.map(res => res.avgSizeExcursionsTripsterResponse);
                    let average_message_size_excursionsWeatlas = res.map(res => res.avgSizeExcursionsWeatlasResponse);
                    let average_message_size_ticket = res.map(res => res.avgSizeTicketResponse);
                    let average_message_size_auto = res.map(res => res.avgSizeAutoResponse);
                    let average_message_size_country = res.map(res => res.avgSizeCountryResponse);
                    let average_message_size_coastLiving = res.map(res => res.avgSizeCoastLivingResponse);

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
                                        data: time_responses_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_responses_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_responses_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_responses_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_responses_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_responses_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_responses_coastLiving,
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
                                    text: 'Среднее время ответов по интеграциям'
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
                                            labelString: "Среднее время ответа в секундах"
                                        }
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
                                        data: average_message_size_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: average_message_size_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: average_message_size_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: average_message_size_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: average_message_size_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: average_message_size_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: average_message_size_coastLiving,
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
                                    text: 'Средний размер сообщений по интеграциям'
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
                                            labelString: "Количество знаков"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_responses_hotel = null;
                    let time_responses_excursionsTripster = null;
                    let time_responses_excursionsWeatlas = null;
                    let time_responses_ticket = null;
                    let time_responses_auto = null;
                    let time_responses_country = null;
                    let time_responses_coastLiving = null;

                    let average_message_size_hotel = null;
                    let average_message_size_excursionsTripster = null;
                    let average_message_size_excursionsWeatlas = null;
                    let average_message_size_ticket = null;
                    let average_message_size_auto = null;
                    let average_message_size_country = null;
                    let average_message_size_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_responses_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_responses_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_responses_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_responses_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_responses_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_responses_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_responses_coastLiving,
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Среднее время ответа в секундах"
                                    }
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
                                    data: average_message_size_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: average_message_size_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: average_message_size_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: average_message_size_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: average_message_size_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: average_message_size_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: average_message_size_coastLiving,
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
                                text: 'Средний размер сообщений по интеграциям'
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
                                        labelString: "Количество знаков"
                                    }
                                }]
                            }
                        }
                    });
                })
    }


    showOverviewMinute() {
        this.loading = true;
        this.condition = true;
        this._monitoring.getResponseMinute()
            .subscribe(res => {
                    this.loading = false;
                    let time_responses_hotel = res.map(res => res.avgTimeHotelResponse);
                    let time_responses_excursionsTripster = res.map(res => res.avgTimeExcursionsTripsterResponse);
                    let time_responses_excursionsWeatlas = res.map(res => res.avgTimeExcursionsWeatlasResponse);
                    let time_responses_ticket = res.map(res => res.avgTimeTicketResponse);
                    let time_responses_auto = res.map(res => res.avgTimeAutoResponse);
                    let time_responses_country = res.map(res => res.avgTimCountryResponse);
                    let time_responses_coastLiving = res.map(res => res.avgTimeCoastLivingResponse);

                    let average_message_size_hotel = res.map(res => res.avgSizeHotelResponse);
                    let average_message_size_excursionsTripster = res.map(res => res.avgSizeExcursionsTripsterResponse);
                    let average_message_size_excursionsWeatlas = res.map(res => res.avgSizeExcursionsWeatlasResponse);
                    let average_message_size_ticket = res.map(res => res.avgSizeTicketResponse);
                    let average_message_size_auto = res.map(res => res.avgSizeAutoResponse);
                    let average_message_size_country = res.map(res => res.avgSizeCountryResponse);
                    let average_message_size_coastLiving = res.map(res => res.avgSizeCoastLivingResponse);

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
                                        data: time_responses_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: time_responses_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: time_responses_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: time_responses_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: time_responses_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: time_responses_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: time_responses_coastLiving,
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
                                    text: 'Среднее время ответов по интеграциям'
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
                                            labelString: "Среднее время ответа в секундах"
                                        }
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
                                        data: average_message_size_hotel,
                                        borderColor: "#3cba9f",
                                        fill: false,
                                    },
                                    {
                                        label: 'экскурсии Tripster',
                                        data: average_message_size_excursionsTripster,
                                        borderColor: "#7cef8b",
                                        fill: false
                                    },
                                    {
                                        label: 'экскурсии Weatlas',
                                        data: average_message_size_excursionsWeatlas,
                                        borderColor: "#ffcc00",
                                        fill: false
                                    },
                                    {
                                        label: 'билеты',
                                        data: average_message_size_ticket,
                                        borderColor: "#fcfa7e",
                                        fill: false
                                    },
                                    {
                                        label: 'аренда авто',
                                        data: average_message_size_auto,
                                        borderColor: "#7efcce",
                                        fill: false
                                    },
                                    {
                                        label: 'страны, города',
                                        data: average_message_size_country,
                                        borderColor: "#3c4aba",
                                        fill: false
                                    },
                                    {
                                        label: 'стоимость жизни',
                                        data: average_message_size_coastLiving,
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
                                    text: 'Средний размер сообщений по интеграциям'
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
                                            labelString: "Количество знаков"
                                        }
                                    }],
                                }
                            }
                        });
                    })
                },
                error => {
                    let time_responses_hotel = null;
                    let time_responses_excursionsTripster = null;
                    let time_responses_excursionsWeatlas = null;
                    let time_responses_ticket = null;
                    let time_responses_auto = null;
                    let time_responses_country = null;
                    let time_responses_coastLiving = null;

                    let average_message_size_hotel = null;
                    let average_message_size_excursionsTripster = null;
                    let average_message_size_excursionsWeatlas = null;
                    let average_message_size_ticket = null;
                    let average_message_size_auto = null;
                    let average_message_size_country = null;
                    let average_message_size_coastLiving = null;

                    let monitoringTimes = [];
                    this.chart = new Chart('canvas', {
                        type: 'line',
                        data: {
                            labels: monitoringTimes,
                            datasets: [
                                {
                                    label: 'отели',
                                    data: time_responses_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: time_responses_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: time_responses_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: time_responses_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: time_responses_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: time_responses_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: time_responses_coastLiving,
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
                                    display: true,
                                    ticks: {
                                        min: 0
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: "Среднее время ответа в секундах"
                                    }
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
                                    data: average_message_size_hotel,
                                    borderColor: "#3cba9f",
                                    fill: false,
                                },
                                {
                                    label: 'экскурсии Tripster',
                                    data: average_message_size_excursionsTripster,
                                    borderColor: "#7cef8b",
                                    fill: false
                                },
                                {
                                    label: 'экскурсии Weatlas',
                                    data: average_message_size_excursionsWeatlas,
                                    borderColor: "#ffcc00",
                                    fill: false
                                },
                                {
                                    label: 'билеты',
                                    data: average_message_size_ticket,
                                    borderColor: "#fcfa7e",
                                    fill: false
                                },
                                {
                                    label: 'аренда авто',
                                    data: average_message_size_auto,
                                    borderColor: "#7efcce",
                                    fill: false
                                },
                                {
                                    label: 'страны, города',
                                    data: average_message_size_country,
                                    borderColor: "#3c4aba",
                                    fill: false
                                },
                                {
                                    label: 'стоимость жизни',
                                    data: average_message_size_coastLiving,
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
                                text: 'Средний размер сообщений по интеграциям'
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
                                        labelString: "Количество знаков"
                                    }
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