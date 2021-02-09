import { Component, OnInit } from '@angular/core';
import {UserApiServiceService} from '../services/user-api-service.service';
import {GetUsersResponseModel} from '../services/responses/get-users-response.model';
import {Observable, of} from 'rxjs';
import {UserModel} from '../models/user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public loading$: Observable<boolean> = of(true);
  public users$: Observable<UserModel[]> = of([]);

  constructor(
    private userApiService: UserApiServiceService
  ) { }

  ngOnInit(): void {
    this.userApiService.getUser().subscribe((response: GetUsersResponseModel) => {
      this.users$ = of(response.users);
      console.log(response);
    }, (error) => console.log(error),
      () => {
      this.loading$ = of(false);
    });
  }
}
