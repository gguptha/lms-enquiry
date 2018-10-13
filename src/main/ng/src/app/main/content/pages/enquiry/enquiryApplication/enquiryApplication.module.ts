import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FuseSharedModule } from '@fuse/shared.module';
import { LoanEnquiryService } from './enquiryApplication.service';
import { RouterModule } from '@angular/router';
import { EnquiryApplicationComponent } from './enquiryApplication.component';
import {
    MatStepperModule, MatSelectModule, MatButtonModule, MatInputModule, MatDialogModule, MatDatepickerModule, MAT_DATE_LOCALE
} from '@angular/material';
import { FuseConfirmDialogModule } from '../../../../../../@fuse/components/confirm-dialog/confirm-dialog.module';

const routes = [
    {
        path: 'enquiryApplication',
        component: EnquiryApplicationComponent,
        resolve: {
            routeResolvedData: LoanEnquiryService
        }
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseConfirmDialogModule,
        FuseSharedModule,
        MatStepperModule,
        MatSelectModule,
        MatButtonModule,
        MatInputModule,
        MatDialogModule,
        MatDatepickerModule
    ],
    declarations: [
        EnquiryApplicationComponent
    ],
    providers: [
        LoanEnquiryService,
        { provide: MAT_DATE_LOCALE, useValue: 'en-in' }
    ]
})
export class EnquiryApplicationModule {
}
