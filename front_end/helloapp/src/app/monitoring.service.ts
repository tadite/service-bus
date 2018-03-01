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
        return this._http.get<any>('/statistics/requestPerDay')
            .map(result => result);
    }

    getOverviewHour() {
          return this._http.get<any>('/statistics/requestPerHour')
              .map(result => result);

    }

    getOverviewMinute() {
        return this._http.get<any>('/statistics/requestPerMinute')
            .map(result => result);
    }

    getOverviewSecond() {
        return this._http.get<any>('/statistics/requestPerSecond')
            .map(result => result);
    }

    getResponseStats() {
        return this._http.get<any>('/statistics/responseStats')
            .map(result => result);
    }

    getErrorStats() {
        return this._http.get<any>('/statistics/errorStats')
            .map(result => result);
    }
}