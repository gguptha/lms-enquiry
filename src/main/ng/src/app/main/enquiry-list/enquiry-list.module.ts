import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import { EnquiryListComponent } from './enquiry-list.component';
import { MatExpansionModule, MatInputModule, MatButtonModule } from '@angular/material';

const routes = [
    {
        path: 'enquirylist',
        component: EnquiryListComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FuseSharedModule,
        MatExpansionModule,
        MatInputModule,
        MatButtonModule
    ],
    declarations: [
        EnquiryListComponent
    ],
    providers: [
    ]
})
export class EnquiryListModule { 
}
