import { Component, OnInit } from "@angular/core";
import { TotalReportsService, Totals } from "../_shared";

@Component({
  selector: "app-total-report",
  templateUrl: "./total-report.component.html",
  styleUrls: ["./total-report.component.scss"]
})
export class TotalReportComponent implements OnInit {
  totals: Totals;
  loading: boolean = false;

  constructor(private totalReportsService: TotalReportsService) {}

  ngOnInit(): void {
    this.loading = true;
    this.totalReportsService.getCovid19Cases().subscribe(totals => {
      this.totals = totals;
      this.loading = false;
    });
  }
}
