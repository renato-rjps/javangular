import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  pessoas: any;
  ngOnInit() {
    this.httpClient.get('api/pessoa').subscribe(response => {
      this.pessoas = response;
    })
  }

}
