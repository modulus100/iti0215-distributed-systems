import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {ItemApiService} from '../../services/item-api.service';
import {GetItemsResponseModel} from '../../services/responses/get-items-response.model';
import {ItemModel} from '../../models/item.model';
import {ActivatedRoute} from '@angular/router';
import {AddItemDialogComponent} from '../add-item-dialog/add-item-dialog.component';
import {CreateItemResponseModel} from '../../services/responses/create-item-response.model';
import {concatMap, tap} from 'rxjs/operators';
import {GetItemResponseModel} from '../../services/responses/get-item-response.model';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.scss']
})
export class UserItemComponent implements OnInit {
  loading$: Observable<boolean> = of(true);
  items: ItemModel[] = [];
  userId = 0;

  constructor(
    private activatedRoute: ActivatedRoute,
    private itemApiService: ItemApiService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.userId = parseInt(params.get('id') || '0', 10);
      this.itemApiService.getByUserId(this.userId).subscribe((response: GetItemsResponseModel) => {
          this.items = response.items;
        }, (error) => console.log(error),
        () => {
          this.loading$ = of(false);
        });
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddItemDialogComponent, {
      width: '250px',
      data: {name: '', description: ''}
    });

    dialogRef.afterClosed().subscribe(next => {
      let newItemId = 0;
      this.loading$ = of(true);
      this.itemApiService.create({
        name: next.name,
        description: next.description,
        userId: this.userId
      }).pipe(
        tap((response: CreateItemResponseModel) => {
          newItemId = response.id;
        }),
        concatMap(() => this.itemApiService.getById(newItemId))
      ).subscribe((response: GetItemResponseModel) => {
          this.items.unshift(response.item);
          this.loading$ = of(false);
        }, error => console.log(error),
        () => {
          this.loading$ = of(false);
        });
    });
  }

  delete(id: number): void {
    this.loading$ = of(true);
    this.itemApiService.delete(id).subscribe(() => {
        this.items = this.items.filter(item => item.id !== id);
      }, error => console.log(error) ,
      () => {
        this.loading$ = of(false);
      });
  }
}
