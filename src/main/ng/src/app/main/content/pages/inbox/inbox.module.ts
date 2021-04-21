import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './inbox.component';
import { RouterModule } from '@angular/router';
import { InboxService } from './inbox.service';
import { MatButtonModule, MatTableModule,MatIconModule, MatDialogModule, MatToolbarModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';
import { InboxItemsComponent } from './inbox-items/inbox-items.component';

import {  MatSnackBar, MatSnackBarModule } from '@angular/material';
import { RejectMessageDialogComponent } from './rejectMessageDialog/rejectMessageDialog.component';
import { FormsModule } from '@angular/forms';


const routes = [
    {
        path: 'inbox',
        component: InboxComponent,
        resolve: {
            routeResolvedData: InboxService
        }
    }
];


@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        FuseSharedModule,
        MatButtonModule,
        MatTableModule,
		MatIconModule,
        MatSnackBarModule,
        FormsModule,
        MatDialogModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule
    ],
    declarations: [
        InboxComponent,
        InboxItemsComponent,
        RejectMessageDialogComponent
    ],
    entryComponents: [
        RejectMessageDialogComponent
    ]
})
export class InboxModule {
}
