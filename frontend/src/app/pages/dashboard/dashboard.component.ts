import { Component, OnInit } from '@angular/core';
import { ActuatorService } from '../actuator/actuator.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  appHealth;
  httpTrace;

  constructor(private actuatorService: ActuatorService) { }

  ngOnInit() {
    this.actuatorService.fetchHealth().subscribe(
      response => {
        this.appHealth = response;
      });

      this.actuatorService.fetchHttpTrace().subscribe(
        response => {
          this.httpTrace = response;
        });
  }

}
