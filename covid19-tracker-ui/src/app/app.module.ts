import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from  '@angular/common/http';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ConfirmedDataComponent } from './confirmed-data/confirmed-data.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from './app-routing.module';
import { DeathDataComponent } from './death-data/death-data.component';
import { RecoveryDataComponent } from './recovery-data/recovery-data.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ConfirmedDataComponent,
    NavbarComponent,
    DeathDataComponent,
    RecoveryDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxDatatableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
