import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { CommonModule, DatePipe } from '@angular/common';
import { MatButtonModule, MatTableModule, MatPaginatorModule, MatStepperModule, MatSelectModule, MatInputModule, MatIconModule, MatToolbarModule, MatDatepickerModule, MAT_DATE_LOCALE, MatSnackBarModule, MatSortModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { EnquiryApplicationRouteGuard } from 'app/enquiryApplication.guard';
import {EnquiryAlertsComponent} from "../enquiryAlerts.component";
import {EnquiryAlertsService} from "../enquiryAlerts.service";
import {EnquiryReviewComponent} from "../enquiryReview/enquiryReview.component";
import {EnquiryAlertsListComponent} from "../enquiryAlertsList/enquiryAlertsList.component";
import {EnquiryRejectDialogComponent} from "./enquiryReject.component";
import {AppService} from "../../../../../../app.service";
import {EnquiryApprovalDialogComponent} from "../enquiryApproval/enquiryApproval.component";

const routes = [
    {
        path      : 'enquiryAlerts',
        component : EnquiryAlertsComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        },
        canActivate: [
          EnquiryApplicationRouteGuard
        ]
    },
    {
        path      : 'enquiryReview',
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
        EnquiryRejectDialogComponent,
        EnquiryApprovalDialogComponent
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
        MatDatepickerModule,
        MatSnackBarModule,
        CdkTableModule,
        MatSortModule,
         MatAutocompleteModule
    ],
    exports     : [
        EnquiryAlertsComponent,
        EnquiryAlertsListComponent,
        EnquiryReviewComponent
    ],
    providers   : [
        AppService,
        EnquiryAlertsService,
        DatePipe,
        { provide: MAT_DATE_LOCALE, useValue: 'en-IN' }
    ],
    entryComponents: [
        EnquiryRejectDialogComponent,
        EnquiryApprovalDialogComponent
    ]
})
export class EnquiryRejectModule
{
}
