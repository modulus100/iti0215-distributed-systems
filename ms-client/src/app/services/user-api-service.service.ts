import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GetUsersResponseModel} from './responses/get-users-response.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserApiServiceService {
  private baseURL = 'http://localhost:8080/user-api/v1/user';

  constructor(private http: HttpClient) { }

  public getUser(): Observable<GetUsersResponseModel> {
    return this.http.get<GetUsersResponseModel>(`${this.baseURL}`);
  }
}
