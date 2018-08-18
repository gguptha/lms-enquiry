import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanEnquiryComponent } from './loan-enquiry.component';
import { RouterModule } from '../../../../node_modules/@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatStepperModule, MatSelectModule, MatButtonModule, MatInputModule } from '../../../../node_modules/@angular/material';
import { LoanEnquiryService } from './loan-enquiry.service';

const routes = [
    {
        path: 'loanenquiry',
        component: LoanEnquiryComponent,
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
        MatInputModule
    ],
    declarations: [
        LoanEnquiryComponent
    ],
    providers: [
        LoanEnquiryService
    ]
})
export class LoanEnquiryModule { 
}
