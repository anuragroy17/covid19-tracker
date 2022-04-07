import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from 'src/app/config';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DeathDataService {

  constructor(private http: HttpClient) { }

  getDeathLocationStats() {
    return this.http.get<any>(`${config.apiUrl}/getDeathLocationStats`)
        .pipe(map(locationStats => {
          // console.log(JSON.stringify(locationStats));
            return locationStats;
        }));
      }

  
    getTotalDeathCases() {
    return this.http.get<any>(`${config.apiUrl}/getTotalDeathCases`)
        .pipe(map(totals => {
          // console.log(JSON.stringify(locationStats));
            return totals;
        }));
      }
}
