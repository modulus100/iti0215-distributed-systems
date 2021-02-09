import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GetUsersResponseModel} from './responses/get-users-response.model';
import {GetUserResponseModel} from './responses/get-user-response.model';
import {CreateUserRequestModel} from './requests/create-user-request.model';
import {CreateUserResponseModel} from './responses/create-user-response.model';

@Injectable({
  providedIn: 'root'
})
export class ItemApiService {
  private baseURL = 'http://localhost:8081/inventory-api/v1/item';

  constructor(private http: HttpClient) { }

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
