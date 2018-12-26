import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule } from '@angular/material';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { EnquirySearchListComponent } from './enquirySearchList/enquirySearchList.component';
import { EnquirySearchComponent } from './enquirySearch.component';

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
        MatSortModule
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
