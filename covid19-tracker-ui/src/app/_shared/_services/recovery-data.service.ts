import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from 'src/app/config';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecoveryDataService {

  constructor(private http: HttpClient) { }

  getRecoveryLocationStats() {
    return this.http.get<any>(`${config.apiUrl}/getRecoveryLocationStats`)
        .pipe(map(locationStats => {
          // console.log(JSON.stringify(locationStats));
            return locationStats;
        }));
      }

  
  getTotalRecoveredCases() {
    return this.http.get<any>(`${config.apiUrl}/getTotalRecoveredCases`)
        .pipe(map(totals => {
          // console.log(JSON.stringify(locationStats));
            return totals;
        }));
      }
}
