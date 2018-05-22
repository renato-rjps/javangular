import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment'
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class ActuatorService {

    private baseUrl: string = environment.backendUrl;

    constructor(private httpClient: HttpClient) { }

    fetchHealth(): Observable<Object> {
        return this.httpClient.get(`${this.baseUrl}/actuator/health`);
    }

    fetchHttpTrace(): Observable<Object> {
        return this.httpClient.get(`${this.baseUrl}/actuator/httptrace`);
    }

}