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
}