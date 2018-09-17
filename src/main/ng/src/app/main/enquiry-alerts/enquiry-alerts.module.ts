import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { EnquiryAlertsComponent } from './enquiry-alerts.component';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatTableModule, MatPaginatorModule } from '@angular/material';
import { EnquiryAlertsListComponent } from './enquiry-alerts-list/enquiry-alerts-list.component';
import { CdkTableModule } from '@angular/cdk/table';
import { EnquiryAlertsService } from './enquiry-alerts.service';

const routes = [
    {
        path      : 'enquiryalerts',
        component : EnquiryAlertsComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        }
    }
];

@NgModule({
    declarations: [
        EnquiryAlertsComponent,
        EnquiryAlertsListComponent
    ],
    imports     : [
        RouterModule.forChild(routes),
        TranslateModule,
        FuseSharedModule,
        CommonModule,
        MatButtonModule,
        MatTableModule,
        MatPaginatorModule,
        CdkTableModule
    ],
    exports     : [
        EnquiryAlertsComponent
    ],
    providers   : [
        EnquiryAlertsService
    ]
})
export class EnquiryAlertsModule
{
}
