import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class MonitoringService {

    constructor(private _http: HttpClient) { }

    getOverview() {
        return this._http.get('text.json')
            .map(result => result);
    }

    getOverviewDay() {
        return this._http.get<any>('/statistics/requestPerDay');
    }

    getOverviewHour() {
        return this._http.get<any>('/statistics/requestPerHour');
    }

    getOverviewMinute() {
        return this._http.get<any>('/statistics/requestPerMinute');
    }

    getOverviewSecond() {
        return this._http.get<any>('/statistics/requestPerSecond');
    }

    getResponseStats() {
        return this._http.get<any>('/statistics/responseStats');
    }

    getErrorStats() {
        return this._http.get<any>('/statistics/errorStats');
    }
}