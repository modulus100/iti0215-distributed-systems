import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreateUserResponseModel} from './responses/create-user-response.model';
import {GetItemsResponseModel} from './responses/get-items-response.model';
import {CreateItemRequestModel} from './requests/create-item-request.model';
import {CreateItemResponseModel} from './responses/create-item-response.model';
import {GetItemResponseModel} from './responses/get-item-response.model';

@Injectable({
  providedIn: 'root'
})
export class ItemApiService {
  private baseURL = 'http://18.192.45.2:8081/inventory-api/v1/item';

  constructor(private http: HttpClient) { }

  getByUserId(userId: number): Observable<GetItemsResponseModel> {
    return this.http.get<GetItemsResponseModel>(`${this.baseURL}?userId=${userId}`);
  }

  getById(id: number): Observable<GetItemResponseModel> {
    return this.http.get<GetItemResponseModel>(`${this.baseURL}/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseURL}/${id}`);
  }

  create(request: CreateItemRequestModel): Observable<CreateItemResponseModel> {
    return this.http.post<CreateUserResponseModel>(this.baseURL, request);
  }
}
