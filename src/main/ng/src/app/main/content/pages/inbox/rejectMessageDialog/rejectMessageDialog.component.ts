import { Component, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';


@Component({
    selector: 'fuse-rejection-reason-dialog',
    templateUrl: './rejectMessageDialog.component.html',
    styleUrls: ['./rejectMessageDialog.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class RejectMessageDialogComponent {

    dialogTitle = 'Reason for Rejection';

    rejectMessageForm: FormGroup;
  
    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, public _dialogRef: MatDialogRef<RejectMessageDialogComponent>, 
                @Inject(MAT_DIALOG_DATA) public _dialogData: any, private _matSnackBar: MatSnackBar) {

         this.rejectMessageForm = _formBuilder.group({
            rejectionReason: ['']
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.rejectMessageForm.valid) {
            var formValues: any = this.rejectMessageForm.value;
            this._dialogRef.close({ 
                'rejectionReason': formValues.rejectionReason,
                'cancel': false
            });
        }
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close({ 
            'rejectionReason': '',
            'cancel': true
        });
    }
}
