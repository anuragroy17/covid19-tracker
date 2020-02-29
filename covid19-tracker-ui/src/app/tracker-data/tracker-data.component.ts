import { Component, OnInit, ViewChild } from '@angular/core';
import { CovidDataService, LocationStats } from '../_shared';
import { DatatableComponent } from '@swimlane/ngx-datatable';

@Component({
  selector: 'app-tracker-data',
  templateUrl: './tracker-data.component.html',
  styleUrls: ['./tracker-data.component.scss']
})
export class TrackerDataComponent implements OnInit {  

  loading: boolean = false;
  locationStats: LocationStats[] = [];
  columns = [
    { prop: 'state' },
    { name: 'Country' },
    { name: 'Latest Total Cases' },
    { prop: 'diffFromPrevDay', name: 'Difference from Previous Day'}
  ];
  filteredData = [];

  @ViewChild(DatatableComponent, { static: false }) table: DatatableComponent;

  constructor(private covidDataService:CovidDataService) { }

  ngOnInit(): void {
    this.loading = true;
    this.covidDataService.getCovid19Data().subscribe(locationStats => {
      this.locationStats = locationStats;
      this.filteredData = [...locationStats];
      this.loading = false;
      // console.log(this.locationStats)
  });
  }

  // filters results
filterDatatable(event){
  // get the value of the key pressed and make it lowercase
  let val = event.target.value.toLowerCase();
  // get the amount of columns in the table
  let colsAmt = this.columns.length;
  // get the key names of each column in the dataset
  let keys = Object.keys(this.filteredData[0]);
  // assign filtered matches to the active datatable
  this.locationStats = this.filteredData.filter(function(item){
    // iterate through each row's column data
    for (let i=0; i<colsAmt; i++){
      // check for a match
      if (item[keys[i]].toString().toLowerCase().indexOf(val) !== -1 || !val){
        // found match, return true to add to result set
        return true;
      }
    }
  });
  // whenever the filter changes, always go back to the first page
  this.table.offset = 0;
}


}
