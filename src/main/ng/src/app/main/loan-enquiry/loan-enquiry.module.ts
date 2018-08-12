import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanEnquiryComponent } from './loan-enquiry.component';
import { RouterModule } from '../../../../node_modules/@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatStepperModule, MatSelectModule, MatButtonModule } from '../../../../node_modules/@angular/material';

const routes = [
    {
        path: 'loanenquiry',
        component: LoanEnquiryComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatStepperModule,
        MatSelectModule,
        MatButtonModule
    ],
    declarations: [
        LoanEnquiryComponent
    ]
})
export class LoanEnquiryModule { 
}
