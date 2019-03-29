import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule } from '@angular/material';

import { FuseSharedModule } from '@fuse/shared.module';
import { ForgotPassword2Component } from './forgotPassword.component';
import { ForgotPasswordService } from './forgotPassword.service';


const routes = [
    {
        path     : 'passwordReset',
        component: ForgotPassword2Component
    }
];

@NgModule({
    declarations: [
        ForgotPassword2Component
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
        ForgotPasswordService
    ]
})
export class ForgotPassword2Module
{
}
