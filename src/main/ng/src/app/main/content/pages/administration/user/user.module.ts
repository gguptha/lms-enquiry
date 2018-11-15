import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material';
import { AppService } from '../../../../../app.service';
import { UserListComponent } from './userList/userList.component';
import { UserService } from './user.service';

const routes = [
    {
        path      : 'users',
        component : UserListComponent,
    }
];

@NgModule({
    declarations: [
        UserListComponent
    ],
    imports     : [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        CommonModule,
        MatButtonModule
    ],
    exports     : [
        UserListComponent
    ],
    providers   : [
        AppService,
        UserService
    ]
})
export class UserModule
{
}
