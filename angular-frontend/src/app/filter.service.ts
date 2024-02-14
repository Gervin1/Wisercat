import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { environment } from "../environments/environment"
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  constructor(private http: HttpClient) {
  }

  getFilters(): Observable<any> {
    return this.http.get(environment.apiUrl + '/filters');
  }

  addFilter(filter: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/filters', filter);
  }
}
