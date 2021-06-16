import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import {
  MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule,
  MatPaginatorModule, MatToolbarModule, MatIconModule, MatSelectModule, MatProgressSpinnerModule, MatDatepickerModule,
  MatTabsModule,
  MAT_DATE_LOCALE,
  DateAdapter,
  MatDialogModule
} from '@angular/material';
import { EnquiryApplicationRouteGuard } from 'app/enquiryApplication.guard';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';
import { MAT_DATE_FORMATS } from '@angular/material';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { LoanAppraisalService } from './loanAppraisal.service';
import { LoanAppraisalComponent } from './loanAppraisal.component';
import { LoanPartnerUpdateComponent } from './loan-partner-update/loan-partner-update.component';
import { LoanPartnersComponent } from './loan-partners/loan-partners.component';

const routes = [
    {
        path: 'loanAppraisal', 
        component: LoanAppraisalComponent,
        canActivate: [
            EnquiryApplicationRouteGuard
        ]
    }
];

const MY_FORMATS = {
    parse: {
        dateInput: ['DD/MM/YYYY'],
    },
    display: {
        dateInput: 'DD/MM/YYYY',
        monthYearLabel: 'MMM YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'MMMM YYYY',
    },
};

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
      MatTabsModule,
      MatDialogModule
    ],
    declarations: [
        LoanAppraisalComponent,
        LoanPartnerUpdateComponent,
        LoanPartnersComponent,
    ],
    providers: [
        LoanEnquiryService,
        LoanAppraisalService,
        {
            provide: MAT_DATE_LOCALE,
            useValue: 'en-in'
        },
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
    ],
    exports: [
        LoanAppraisalComponent,
    ],  
    entryComponents: [
        LoanPartnersComponent,
        LoanPartnerUpdateComponent
    ]
})
export class LoanAppraisalModule {
}
