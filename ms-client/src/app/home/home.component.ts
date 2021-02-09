import { Component, OnInit } from '@angular/core';
import {UserApiService} from '../services/user-api.service';
import {GetUsersResponseModel} from '../services/responses/get-users-response.model';
import {Observable, of} from 'rxjs';
import {UserModel} from '../models/user.model';
import {MatDialog} from '@angular/material/dialog';
import {AddDialogComponent} from './add-dialog/add-dialog.component';
import {concatMap, tap} from 'rxjs/operators';
import {CreateUserResponseModel} from '../services/responses/create-user-response.model';
import {GetUserResponseModel} from '../services/responses/get-user-response.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  loading$: Observable<boolean> = of(true);
  users: UserModel[] = [];

  constructor(
    private userApiService: UserApiService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.userApiService.getUsers().subscribe((response: GetUsersResponseModel) => {
      this.users = response.users;
    }, (error) => console.log(error),
      () => {
      this.loading$ = of(false);
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddDialogComponent, {
      width: '250px',
      data: {name: ''}
    });

    dialogRef.afterClosed().subscribe(name => {
      let newIdNumber = 0;
      this.loading$ = of(true);
      this.userApiService.create({name}).pipe(
        tap((response: CreateUserResponseModel) => {
          newIdNumber = response.id;
        }),
        concatMap( () => this.userApiService.getByiId(newIdNumber))
      ).subscribe((response: GetUserResponseModel) => {
        this.users.unshift(response.user);
        this.loading$ = of(false);
      }, error => console.log(error),
        () => {
        this.loading$ = of(false);
      });
    });
  }

  delete(id: number): void {
    this.loading$ = of(true);
    this.userApiService.delete(id).subscribe(() => {
      this.users = this.users.filter(user => user.id !== id);
    }, error => console.log(error) ,
      () => {
      this.loading$ = of(false);
    });
  }
}
