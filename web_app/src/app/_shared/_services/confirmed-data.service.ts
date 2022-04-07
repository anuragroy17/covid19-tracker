import { Injectable } from '@angular/core';
import { config } from 'src/app/config';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConfirmedDataService {

  constructor(private http: HttpClient) { }

  getConfirmedLocationStats() {
    return this.http.get<any>(`${config.apiUrl}/getConfirmedLocationStats`)
        .pipe(map(locationStats => {
          // console.log(JSON.stringify(locationStats));
            return locationStats;
        }));
      }

  
  getTotalConfirmedCases() {
    return this.http.get<any>(`${config.apiUrl}/getTotalConfirmedCases`)
        .pipe(map(totals => {
          // console.log(JSON.stringify(locationStats));
            return totals;
        }));
      }
}
