import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from "rxjs";
import { environment } from "../environments/environment"
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Filter } from "./models/filter.model";

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  constructor(private http: HttpClient) {
  }

  getFilters(): Observable<any> {
    return this.http.get<Filter[]>(environment.apiUrl + '/filters')
      .pipe(
        catchError(this.handleError)
      );
  }

  addFilter(filter: any): Observable<any> {
    return this.http.post<Filter>(environment.apiUrl + '/filters', filter)
      .pipe(
        catchError(this.handleError)
      );
  }
  private handleError(error: HttpErrorResponse) {
    //Here is a very basic implementation of error handling
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

}
