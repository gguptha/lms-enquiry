import { NgModule } from '@angular/core';
import { MessageDialogComponent } from './messageDialog.component';
import { MatDialogModule, MatButtonModule, MatFormFieldModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        MessageDialogComponent
    ],
    imports: [
        MatDialogModule,
        MatButtonModule,
        MatFormFieldModule,
        BrowserModule,
        FormsModule
    ],
    entryComponents: [
        MessageDialogComponent
    ]
})
export class MessageDialogModule {
}
