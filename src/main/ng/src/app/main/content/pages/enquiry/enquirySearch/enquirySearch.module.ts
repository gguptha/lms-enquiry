import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FuseSharedModule } from '@fuse/shared.module';
import {
  MatExpansionModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatSortModule,
  MatPaginatorModule, MatToolbarModule, MatIconModule, MatSelectModule, MatProgressSpinnerModule, MatDatepickerModule,
  MatDatepickerToggle
} from '@angular/material';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { EnquirySearchListComponent } from './enquirySearchList/enquirySearchList.component';
import { EnquirySearchComponent } from './enquirySearch.component';
import {EnquiryApplicationRouteGuard} from "../../../../../enquiryApplication.guard";

const routes = [
    {
        path: 'enquiryList',
        component: EnquirySearchComponent,
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
        EnquirySearchComponent,
        EnquirySearchListComponent
    ],
    providers: [
        LoanEnquiryService
    ],
    exports: [
        EnquirySearchComponent,
        EnquirySearchListComponent
    ]
})
export class EnquiryListModule {
}
