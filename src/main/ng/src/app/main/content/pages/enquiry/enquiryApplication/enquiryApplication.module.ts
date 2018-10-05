import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FuseSharedModule } from '@fuse/shared.module';
import { LoanEnquiryService } from './enquiryApplication.service';
import { RouterModule } from '@angular/router';
import { EnquiryApplicationComponent } from './enquiryApplication.component';
import { MatStepperModule, MatSelectModule, MatButtonModule, MatInputModule, MatDialogModule } from '@angular/material';

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
        FuseSharedModule,
        MatStepperModule,
        MatSelectModule,
        MatButtonModule,
        MatInputModule,
        MatDialogModule,
    ],
    declarations: [
        EnquiryApplicationComponent
    ],
    providers: [
        LoanEnquiryService
    ]
})
export class EnquiryApplicationModule { 
}