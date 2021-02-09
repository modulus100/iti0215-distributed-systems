import { Component, OnInit } from '@angular/core';
import {UserApiServiceService} from '../services/user-api-service.service';
import {GetUsersResponseModel} from '../services/responses/get-users-response.model';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public loading$: Observable<boolean> = of(true);

  constructor(
    private userApiService: UserApiServiceService
  ) { }

  ngOnInit(): void {
    this.userApiService.getUser().subscribe((result: GetUsersResponseModel) => {
      console.log(result);
    }, (error) => console.log(error),
      () => {
      this.loading$ = of(true);
    });
  }
}
