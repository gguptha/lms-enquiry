import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule } from '@angular/material';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { EnquirySearchListComponent } from './enquirySearchList/enquirySearchList.component';
import { EnquirySearchComponent } from './enquirySearch.component';
import { CdkTableModule } from '@angular/cdk/table';

const routes = [
    {
        path: 'enquiryList',
        component: EnquirySearchComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatExpansionModule,
        MatInputModule,
        MatButtonModule,
        MatFormFieldModule,
        MatTableModule,
        CdkTableModule
    ],
    declarations: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ],
    providers: [
        LoanEnquiryService
    ],
    exports: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ]
})
export class EnquiryListModule { 
}
