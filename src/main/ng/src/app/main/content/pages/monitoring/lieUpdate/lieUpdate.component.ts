import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LIEModel } from 'app/main/content/model/lie.model';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-lie-update-dialog',
    templateUrl: './lieUpdate.component.html',
    styleUrls: ['./lieUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class LIEUpdateDialogComponent {

    dialogTitle = 'Add LIE';

    selectedLIE: LIEModel;

    lieUpdateForm: FormGroup;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<LIEUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedLIE !== undefined) {
            this.selectedLIE = _dialogData.selectedLIE;
        }
        else {
            this.selectedLIE = new LIEModel({});
        }

        this.lieUpdateForm = _formBuilder.group({
            bpCode: [this.selectedLIE.bpCode],
            name: [this.selectedLIE.name || ''],
            dateOfAppointment: [this.selectedLIE.dateOfAppointment || ''],
            contactPerson: [this.selectedLIE.contactPerson],
            contractPeriodFrom: [this.selectedLIE.contractPeriodFrom || ''],
            contractPeriodTo: [this.selectedLIE.contractPeriodTo || ''],
            email: [this.selectedLIE.email]
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.lieUpdateForm.valid) {
            const lie: LIEModel = new LIEModel(this.lieUpdateForm.value);
            if (this._dialogData.operation === 'addLIE') {
                this._loanMonitoringService.saveLIE(lie, this._dialogData.loanApplicationId).subscribe(() => {
                    this._matSnackBar.open('LIE added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close();
                });
            }
            else {
                console.log('updating', lie);
            }
        }
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close();
    }
}
