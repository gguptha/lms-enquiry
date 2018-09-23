import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { EnquiryAlertsComponent } from './enquiry-alerts.component';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatTableModule, MatPaginatorModule, MatHorizontalStepper, MatStepperModule, MatSelectModule, MatInputModule } from '@angular/material';
import { EnquiryAlertsListComponent } from './enquiry-alerts-list/enquiry-alerts-list.component';
import { CdkTableModule } from '@angular/cdk/table';
import { EnquiryAlertsService } from './enquiry-alerts.service';
import { EnquiryReviewComponent } from './enquiry-review/enquiry-review.component';

const routes = [
    {
        path      : 'enquiryalerts',
        component : EnquiryAlertsComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        }
    },
    {
        path      : 'enquiryreview',
        component : EnquiryReviewComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        }
    }
];

@NgModule({
    declarations: [
        EnquiryAlertsComponent,
        EnquiryAlertsListComponent,
        EnquiryReviewComponent
    ],
    imports     : [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        CommonModule,
        MatButtonModule,
        MatTableModule,
        MatPaginatorModule,
        MatStepperModule,
        MatSelectModule,
        MatInputModule,
        CdkTableModule
    ],
    exports     : [
        EnquiryAlertsComponent,
        EnquiryReviewComponent
    ],
    providers   : [
        EnquiryAlertsService
    ]
})
export class EnquiryAlertsModule
{
}
