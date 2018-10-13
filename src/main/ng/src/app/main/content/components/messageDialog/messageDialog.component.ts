import {Component, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

export interface MessageDialogData {
    /**
     * message
     */
    message: string;

    /**
     * response
     */
    response: string;
}

@Component({
    selector: 'message-dialog-component',
    templateUrl: 'messageDialog.component.html',
    styleUrls: ['./messageDialog.component.scss']
})
export class MessageDialogComponent {

    constructor(
        public dialogRef: MatDialogRef<MessageDialogComponent>, 
        @Inject(MAT_DIALOG_DATA) public _data: any) { 
            
    }

    onNoClick(): void {
        this.dialogRef.close();
    }
}
