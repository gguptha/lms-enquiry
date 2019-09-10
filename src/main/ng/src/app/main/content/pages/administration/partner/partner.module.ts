
import {PartnerComponent} from "./partnerSearch/partnerSearch.component";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {TranslateModule} from "@ngx-translate/core";
import {FuseSharedModule} from "../../../../../../@fuse/shared.module";
import {CommonModule} from "@angular/common";
import {
  MatButtonModule,
  MatCheckboxModule, MatDialogModule, MatExpansionModule, MatFormFieldModule, MatIconModule, MatInputModule,
  MatPaginatorModule,
  MatSelectModule, MatSortModule,
  MatTableModule,
  MatToolbarModule
} from "@angular/material";
import {CdkTableModule} from "@angular/cdk/table";
import {PartnerService} from "./partner.service";



const routes = [
  {
    path      : 'partner',
    component : PartnerComponent,
    resolve   : {
      routeResolvedData: PartnerService
    }
  }
]

@NgModule({
  declarations: [
    PartnerComponent
  ],
  imports     : [
    RouterModule.forChild(routes),
    TranslateModule,
    FuseSharedModule,
    CommonModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    CdkTableModule,
    FuseSharedModule,
    MatExpansionModule,
    MatSortModule,
    MatPaginatorModule
  ],
  exports     : [
    PartnerComponent
  ],
  providers   : [
    PartnerService
  ],
  entryComponents: [
    PartnerComponent

  ]
})
export class PartnerModule
{
}
