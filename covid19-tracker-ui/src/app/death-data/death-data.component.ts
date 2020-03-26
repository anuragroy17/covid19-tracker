import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { DeathDataService, TotalDeaths, DeathLocationStats } from '../_shared';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-death-data',
  templateUrl: './death-data.component.html',
  styleUrls: ['./death-data.component.scss']
})
export class DeathDataComponent implements OnInit, OnDestroy {  
  private unsubscribe$ = new Subject<void>();

  totals: TotalDeaths;
  loading: boolean = false;

  screenHeight: number;
  screenWidth: number;
  lastColName: String = "Difference from Previous Day";
  secLastCol: string = "Latest Total Deaths";
  showDetail: boolean = false;  
  loadingIndicator: boolean = false;

  locationStats: DeathLocationStats[] = [];
  columns = [
    { prop: "state" },
    { name: "Country" },
    { name: "Latest Total Deaths" },
    { prop: "diffFromPrevDay", name: "Difference from Previous Day" }
  ];
  filteredData = [];

  @ViewChild(DatatableComponent, { static: false }) table: DatatableComponent;

  constructor(private deathDataService: DeathDataService) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    // console.log(this.screenWidth)
    if(this.screenWidth < 1187){
      this.lastColName = "DFPD";
      this.secLastCol = "LTD";
      this.showDetail = true;
    }else{
      this.lastColName = "Difference from Previous Day";
      this.secLastCol = "Latest Total Deaths";
      this.showDetail = false;
    }
  }

  ngOnInit(): void {
    this.loadingIndicator = true;
    this.deathDataService.getDeathLocationStats()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(locationStats => {
      this.locationStats = locationStats;
      this.filteredData = [...locationStats];
      this.loadingIndicator = false;
      // console.log(this.locationStats)
    });

    this.loading = true;
    this.deathDataService.getTotalDeathCases()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(totals => {
      this.totals = totals;
      this.loading = false;
    });
  }

  // filters results
  filterDatatable(event) {
    // get the value of the key pressed and make it lowercase
    let val = event.target.value.toLowerCase();
    // get the amount of columns in the table
    let colsAmt = this.columns.length;
    // get the key names of each column in the dataset
    let keys = Object.keys(this.filteredData[0]);
    // assign filtered matches to the active datatable
    this.locationStats = this.filteredData.filter(function(item) {
      // iterate through each row's column data
      for (let i = 0; i < colsAmt; i++) {
        // check for a match
        if (
          item[keys[i]]
            .toString()
            .toLowerCase()
            .indexOf(val) !== -1 ||
          !val
        ) {
          // found match, return true to add to result set
          return true;
        }
      }
    });
    // whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }


}
