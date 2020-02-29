import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from 'src/app/config';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TotalReportsService {

  constructor(private http: HttpClient) { }

  getCovid19Cases() {
    return this.http.get<any>(`${config.apiUrl}/getTotalCases`)
        .pipe(map(totals => {
          // console.log(JSON.stringify(locationStats));
            return totals;
        }));
      }
}
