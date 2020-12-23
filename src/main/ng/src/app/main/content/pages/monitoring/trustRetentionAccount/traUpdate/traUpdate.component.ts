import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { TRAModel } from 'app/main/content/model/tra.model';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-tra-update-dialog',
    templateUrl: './traUpdate.component.html',
    styleUrls: ['./traUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class TRAUpdateDialogComponent {

    dialogTitle = 'Add New TRA Account';

    selectedTRA: TRAModel;

    traUpdateForm: FormGroup;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<TRAUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedTRA !== undefined) {
            this.selectedTRA = _dialogData.selectedTRA;
        }
        else {
            this.selectedTRA = new TRAModel({});
        }

        this.traUpdateForm = _formBuilder.group({
            bankKey: [this.selectedTRA.bankKey || ''],
            traBankName: [this.selectedTRA.traBankName || ''],
            branch: [this.selectedTRA.branch || ''],
            address: [this.selectedTRA.address || ''],
            ifscCode: [this.selectedTRA.ifscCode || ''],
            accountNumber: [this.selectedTRA.accountNumber || ''],
            contactName: [this.selectedTRA.contactName || ''],
            contactNumber: [this.selectedTRA.contactNumber || ''],
            typeOfAccount: [this.selectedTRA.typeOfAccount || ''],
            email: [this.selectedTRA.email || ''],
            pfsAuthorisedPersonBPCode: [this.selectedTRA.pfsAuthorisedPersonBPCode || ''],
            pfsAuthorisedPerson: [this.selectedTRA.pfsAuthorisedPerson || ''],
            beneficiaryName: [this.selectedTRA.beneficiaryName || '']
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.traUpdateForm.valid) {
            var tra: TRAModel = new TRAModel(this.traUpdateForm.value);
            if (this._dialogData.operation === 'addTRA') {
                this._loanMonitoringService.saveTRA(tra, this._dialogData.loanApplicationId).subscribe(() => {
                    this._matSnackBar.open('TRA added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedTRA.bankKey  = tra.bankKey;
                this.selectedTRA.traBankName  = tra.traBankName;
                this.selectedTRA.branch  = tra.branch;
                this.selectedTRA.address  = tra.address;
                this.selectedTRA.ifscCode  = tra.ifscCode;
                this.selectedTRA.accountNumber  = tra.accountNumber;
                this.selectedTRA.contactName  = tra.contactName;
                this.selectedTRA.contactNumber  = tra.contactNumber;
                this.selectedTRA.typeOfAccount  = tra.typeOfAccount;
                this.selectedTRA.email  = tra.email;
                this.selectedTRA.pfsAuthorisedPersonBPCode  = tra.pfsAuthorisedPersonBPCode;
                this.selectedTRA.pfsAuthorisedPerson  = tra.pfsAuthorisedPerson;
                this.selectedTRA.beneficiaryName  = tra.beneficiaryName;
                this._loanMonitoringService.updateTRA(this.selectedTRA).subscribe(() => {
                    this._matSnackBar.open('TRA updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });            
            }
        }
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close({ 'refresh': false });
    }
}
