import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatIconModule, MatToolbarModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatTableModule, MatCheckboxModule } from '@angular/material';
import { AppService } from '../../../../../app.service';
import { UserComponent } from './user.component';
import { UserService } from './user.service';
import { UserUpdateDialogComponent } from './userUpdate/userUpdate.component';
import { UserListComponent } from './userList/userList.component';
import { CdkTableModule } from '@angular/cdk/table';

const routes = [
    {
        path      : 'userManagement',
        component : UserComponent,
    }
];

@NgModule({
    declarations: [
        UserComponent,
        UserListComponent,
        UserUpdateDialogComponent
    ],
    imports     : [
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
        MatTableModule,
        CdkTableModule
    ],
    exports     : [
        UserComponent
    ],
    providers   : [
        UserService
    ],
    entryComponents: [
        UserUpdateDialogComponent
    ]
})
export class UserModule
{
}
