import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConfirmedDataComponent } from './confirmed-data/confirmed-data.component';
import { DeathDataComponent } from './death-data/death-data.component';
import { RecoveryDataComponent } from './recovery-data/recovery-data.component';


const routes: Routes = [
  { path: 'confirmed', component: ConfirmedDataComponent },
  { path: 'deaths', component: DeathDataComponent },
  { path: 'recoveries', component: RecoveryDataComponent },
  // otherwise redirect to home
  {path: '', redirectTo: 'confirmed',  pathMatch: 'full' },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
