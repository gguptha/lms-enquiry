import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import {
  MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule,
  MatPaginatorModule, MatToolbarModule, MatIconModule, MatSelectModule, MatProgressSpinnerModule, MatDatepickerModule,
  MatTabsModule
} from '@angular/material';
import { LoanMonitoringComponent } from './loanMonitoring.component';
import { EnquiryApplicationRouteGuard } from 'app/enquiryApplication.guard';
import { LIEUpdateDialogComponent } from './lieUpdate/lieUpdate.component';
import { LIEListComponent } from './lieList/lieList.component';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from './loanMonitoring.service';
import { LIEReportAndFeeUpdateDialogComponent } from './lieReportAndFeeUpdate/lieReportAndFeeUpdate.component';
import { LIEReportAndFeeListComponent } from './lieReportAndFeeList/lieReportAndFeeList.component';

const routes = [
    {
        path: 'loanMonitoring', component: LoanMonitoringComponent,
        canActivate: [
            EnquiryApplicationRouteGuard
        ]
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
      MatPaginatorModule,
      MatTableModule,
      MatToolbarModule,
      MatIconModule,
      MatSelectModule,
      MatSortModule,
      MatDatepickerModule,
      MatProgressSpinnerModule,
      MatTabsModule
    ],
    declarations: [
        LoanMonitoringComponent,
        LIEUpdateDialogComponent,
        LIEListComponent,
        LIEReportAndFeeUpdateDialogComponent,
        LIEReportAndFeeListComponent
    ],
    providers: [
        LoanEnquiryService,
        LoanMonitoringService
    ],
    exports: [
        LoanMonitoringComponent,
        LIEUpdateDialogComponent,
        LIEListComponent,
        LIEReportAndFeeUpdateDialogComponent,
        LIEReportAndFeeListComponent
    ],  
    entryComponents: [
        LIEUpdateDialogComponent,
        LIEListComponent,
        LIEReportAndFeeUpdateDialogComponent,
        LIEReportAndFeeListComponent
    ]
})
export class LoanMonitoringModule {
}
