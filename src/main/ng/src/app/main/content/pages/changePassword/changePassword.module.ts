import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatToolbarModule } from '@angular/material';

import { FuseSharedModule } from '@fuse/shared.module';
import { ChangePasswordService } from './changePassword.service';
import { ChangePasswordComponent } from './changePassword.component';
import { UpdatePasswordComponent } from './updatePassword.component';

const routes = [
    {
        path     : 'forceChangePassword',
        component: ChangePasswordComponent
    }
];

@NgModule({
    declarations: [
        ChangePasswordComponent,
        UpdatePasswordComponent
    ],
    entryComponents: [
        UpdatePasswordComponent
    ],
    imports     : [
        RouterModule.forChild(routes),

        MatButtonModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatToolbarModule,
        FuseSharedModule,
    ],
    providers: [
        ChangePasswordService
    ]
})
export class ChangePasswordModule
{
}
