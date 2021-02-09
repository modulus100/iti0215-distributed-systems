import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {UserItemComponent} from './home/user-item/user-item.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    resolve: {
    }
  },
  {
    path: 'user-items/:id',
    component: UserItemComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
