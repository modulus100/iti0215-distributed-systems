import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GetUsersResponseModel} from './responses/get-users-response.model';
import {Observable} from 'rxjs';
import {CreateUserRequestModel} from './requests/create-user-request.model';
import {CreateUserResponseModel} from './responses/create-user-response.model';
import {GetUserResponseModel} from './responses/get-user-response.model';

@Injectable({
  providedIn: 'root'
})
export class UserApiService {
  private baseURL = 'http://18.192.45.2:8080/user-api/v1/user';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<GetUsersResponseModel> {
    return this.http.get<GetUsersResponseModel>(`${this.baseURL}`);
  }

  getByiId(id: number): Observable<GetUserResponseModel> {
    return this.http.get<GetUserResponseModel>(`${this.baseURL}/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseURL}/${id}`);
  }

  create(request: CreateUserRequestModel): Observable<CreateUserResponseModel> {
    return this.http.post<CreateUserResponseModel>(this.baseURL, request);
  }
}
