import { Injectable } from '@angular/core';
import { config } from 'src/app/config';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CovidDataService {

  constructor(private http: HttpClient) { }

  getCovid19Data() {
    return this.http.get<any>(`${config.apiUrl}/getCovid19Data`)
        .pipe(map(locationStats => {
          // console.log(JSON.stringify(locationStats));
            return locationStats;
        }));
      }
}
