import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { CommonModule, DatePipe } from '@angular/common';
import { MatButtonModule, MatTableModule, MatPaginatorModule, MatStepperModule, MatSelectModule, MatInputModule, MatIconModule, MatToolbarModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';
import { EnquiryAlertsService } from './enquiryAlerts.service';
import { EnquiryAlertsComponent } from './enquiryAlerts.component';
import { EnquiryAlertsListComponent } from './enquiryAlertsList/enquiryAlertsList.component';
import { EnquiryReviewComponent } from './enquiryReview/enquiryReview.component';
import { EnquiryRejectDialogComponent } from './enquiryReject/enquiryReject.component';

const routes = [
    {
        path      : 'enquiryAlerts',
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
        EnquiryReviewComponent,
        EnquiryRejectDialogComponent
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
        MatIconModule,
        MatToolbarModule,
        CdkTableModule
    ],
    exports     : [
        EnquiryAlertsComponent,
        EnquiryAlertsListComponent,
        EnquiryReviewComponent
    ],
    providers   : [
        EnquiryAlertsService,
        DatePipe
    ],
    entryComponents: [
        EnquiryRejectDialogComponent
    ]
})
export class EnquiryAlertsModule
{
}