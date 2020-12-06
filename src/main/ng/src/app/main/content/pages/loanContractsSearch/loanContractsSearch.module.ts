import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import {
  MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule,
  MatPaginatorModule, MatToolbarModule, MatIconModule, MatSelectModule, MatProgressSpinnerModule, MatDatepickerModule,
  MatDatepickerToggle
} from '@angular/material';
import {EnquiryApplicationRouteGuard} from "../../../../enquiryApplication.guard";
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';
import { LoanContractListComponent } from './loanContractList/loanContractList.component';
import { LoanContractsSearchComponent } from './loanContractsSearch.component';

const routes = [
    {
        path: 'loanContractsList',
        component: LoanContractsSearchComponent,
      resolve: {
        routeResolvedData: LoanEnquiryService
      },
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
      MatProgressSpinnerModule
    ],
    declarations: [
        LoanContractsSearchComponent,
        LoanContractListComponent
    ],
    providers: [
        LoanEnquiryService
    ],
    exports: [
        LoanContractsSearchComponent,
        LoanContractListComponent
    ]
})
export class LoanContractsSearchModule {
}
