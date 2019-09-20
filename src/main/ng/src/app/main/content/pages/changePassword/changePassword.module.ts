import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule } from '@angular/material';

import { FuseSharedModule } from '@fuse/shared.module';
import { ChangePasswordService } from './changePassword.service';
import { ChangePasswordComponent } from './changePassword.component';

const routes = [
    {
        path     : 'forceChangePassword',
        component: ChangePasswordComponent
    }
];

@NgModule({
    declarations: [
        ChangePasswordComponent
    ],
    imports     : [
        RouterModule.forChild(routes),

        MatButtonModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,

        FuseSharedModule,
    ],
    providers: [
        ChangePasswordService
    ]
})
export class ChangePasswordModule
{
}
