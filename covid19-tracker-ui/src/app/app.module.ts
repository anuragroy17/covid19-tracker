import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from  '@angular/common/http';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TotalReportComponent } from './total-report/total-report.component';
import { TrackerDataComponent } from './tracker-data/tracker-data.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TotalReportComponent,
    TrackerDataComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxDatatableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
