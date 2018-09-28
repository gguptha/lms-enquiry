import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatTableModule, MatPaginatorModule, MatStepperModule, MatSelectModule, MatInputModule, MatIconModule, MatToolbarModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';
import { EnquiryAlertsService } from './enquiryAlerts.service';
import { EnquiryAlertsComponent } from './enquiryAlerts.component';
import { EnquiryAlertsListComponent } from './enquiryAlertsList/enquiryAlertsList.component';

const routes = [
    {
        path      : 'enquiryAlerts',
        component : EnquiryAlertsComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        }
    },
    /*{
        path      : 'enquiryreview',
        component : EnquiryReviewComponent,
        resolve   : {
            routeResolvedData: EnquiryAlertsService
        }
    }*/
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
        MatStepperModule,
        MatSelectModule,
        MatInputModule,
        MatIconModule,
        MatToolbarModule,
        CdkTableModule
    ],
    exports     : [
        EnquiryAlertsComponent,
        EnquiryAlertsListComponent
    ],
    providers   : [
        EnquiryAlertsService
    ],
    entryComponents: [
    ]
})
export class EnquiryAlertsModule
{
}
