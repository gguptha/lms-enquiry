import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {TranslateModule} from '@ngx-translate/core';

import {FuseSharedModule} from '@fuse/shared.module';
import {CommonModule} from '@angular/common';
import {
  MatButtonModule, MatIconModule, MatToolbarModule, MatDialogModule, MatFormFieldModule, MatInputModule,
  MatSelectModule, MatTableModule, MatCheckboxModule, MatProgressSpinnerModule, MatExpansionModule, MatPaginatorModule,
  MatSortModule
} from '@angular/material';
import {AppService} from '../../../../../app.service';
import {UserComponent} from './user.component';
import {UserService} from './user.service';
import {UserUpdateDialogComponent} from './userUpdate/userUpdate.component';
import {UserListComponent} from './userList/userList.component';
import {CdkTableModule} from '@angular/cdk/table';
import { UserMgmtComponent } from './user-mgmt/user-mgmt.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

const routes = [
  {
    path: 'userManagement',
    component: UserComponent,
    resolve: {
      routeResolvedData: UserService
    }
  }
];



@NgModule({
  declarations: [
    UserComponent,
    UserListComponent,
    UserUpdateDialogComponent,
    UserMgmtComponent 
  ],
  imports: [
    RouterModule.forChild(routes),
    TranslateModule,
    FuseSharedModule,
    CommonModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    CdkTableModule,
    MatExpansionModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatProgressSpinnerModule,
    BrowserAnimationsModule
  ],
  exports: [
    UserComponent
  ],
  providers: [
    UserService
  ],
  entryComponents: [
    UserUpdateDialogComponent
  ]
})
export class UserModule {
}
