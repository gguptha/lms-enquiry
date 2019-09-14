
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {TranslateModule} from "@ngx-translate/core";
import {FuseSharedModule} from "../../../../../../@fuse/shared.module";
import {CommonModule} from "@angular/common";
import {
  MatButtonModule,
  MatCheckboxModule, MatDatepicker, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatPaginatorModule,
  MatSelectModule, MatSortModule,
  MatTableModule,
  MatToolbarModule
} from "@angular/material";
import {CdkTableModule} from "@angular/cdk/table";
 import {MailRepoComponent} from "./mail-repo.component";
import {MailRepoService} from "./mail-repo.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";



const routes = [
  {
    path      : 'mailRepo',
    component : MailRepoComponent,
    resolve   : {
      routeResolvedData: MailRepoService
    }
  }
]

@NgModule({
  declarations: [
    MailRepoComponent
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
    MatPaginatorModule,
    MatDatepickerModule,
    BrowserAnimationsModule
  ],
  exports     : [
    MailRepoComponent
  ],
  providers   : [
    MailRepoService
  ],
  entryComponents: [
    MailRepoComponent

  ]
})
export class MailRepoModule
{
}
