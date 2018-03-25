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

    getResponseDay() {
        return this._http.get<any>('/statistics/responsePerDay')
            .map(result => result);
    }

    getResponseHour() {
        return this._http.get<any>('/statistics/responsePerHour')
            .map(result => result);
    }

    getResponseMinute() {
        return this._http.get<any>('/statistics/responsePerMinute')
            .map(result => result);
    }



    getErrorDay() {
        return this._http.get<any>('/statistics/errorPerDay')
            .map(result => result);
    }
    getErrorHour() {
        return this._http.get<any>('/statistics/errorPerHour')
            .map(result => result);
    }

    getErrorMinute() {
        return this._http.get<any>('/statistics/errorPerMinute')
            .map(result => result);
    }

}