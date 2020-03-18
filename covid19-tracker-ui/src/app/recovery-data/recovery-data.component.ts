import { Component, OnInit, ViewChild } from '@angular/core';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { RecoveryDataService, TotalRecovered, RecoveredLocationStats } from '../_shared';

@Component({
  selector: 'app-recovery-data',
  templateUrl: './recovery-data.component.html',
  styleUrls: ['./recovery-data.component.scss']
})
export class RecoveryDataComponent implements OnInit {

  totals: TotalRecovered;
  loading: boolean = false;

  screenHeight: number;
  screenWidth: number;
  lastColName: String = "Difference from Previous Day";
  secLastCol: string = "Latest Total Deaths";
  showDetail: boolean = false;  
  loadingIndicator: boolean = false;

  locationStats: RecoveredLocationStats[] = [];
  columns = [
    { prop: "state" },
    { name: "Country" },
    { name: "Latest Total Recoveries" },
    { prop: "diffFromPrevDay", name: "Difference from Previous Day" }
  ];
  filteredData = [];

  @ViewChild(DatatableComponent, { static: false }) table: DatatableComponent;

  constructor(private recoveryDataService: RecoveryDataService) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    // console.log(this.screenWidth)
    if(this.screenWidth < 1187){
      this.lastColName = "DFPD";
      this.secLastCol = "LTR";
      this.showDetail = true;
    }else{
      this.lastColName = "Difference from Previous Day";
      this.secLastCol = "Latest Total Recoveries";
      this.showDetail = false;
    }
  }

  ngOnInit(): void {
    this.loadingIndicator = true;
    this.recoveryDataService.getRecoveryLocationStats().subscribe(locationStats => {
      this.locationStats = locationStats;
      this.filteredData = [...locationStats];
      this.loadingIndicator = false;
      // console.log(this.locationStats)
    });

    this.loading = true;
    this.recoveryDataService.getTotalRecoveredCases().subscribe(totals => {
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

}
