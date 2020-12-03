import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import {
  MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule,
  MatPaginatorModule, MatToolbarModule, MatIconModule, MatSelectModule, MatProgressSpinnerModule, MatDatepickerModule,
  MatTabsModule
} from '@angular/material';
import {EnquiryApplicationRouteGuard} from "../../../../../enquiryApplication.guard";
import { LoanEnquiryService } from '../../enquiry/enquiryApplication.service';
import { LoanMonitoringComponent } from './loanMonitoring.component';
import { LoanMonitoringService } from '../loanMonitoring.service';
import { LieUpdateDialogComponent } from '../lieUpdate/lieUpdate.component';

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
        LieUpdateDialogComponent
    ],
    providers: [
        LoanEnquiryService,
        LoanMonitoringService
    ],
    exports: [
        LoanMonitoringComponent,
        LieUpdateDialogComponent
    ],  
    entryComponents: [
        LieUpdateDialogComponent
    ]
})
export class LoanMonitoringModule {
}
