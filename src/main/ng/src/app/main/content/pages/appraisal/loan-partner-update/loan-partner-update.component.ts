import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { LoanAppraisalService } from '../loanAppraisal.service';

@Component({
  selector: 'fuse-loan-partner-update',
  templateUrl: './loan-partner-update.component.html',
  styleUrls: ['./loan-partner-update.component.scss']
})
export class LoanPartnerUpdateComponent implements OnInit {

    dialogTitle = "Add Appraisal Officer";

    loanOfficerForm: FormGroup;

    selectedLoanOfficer: any;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanAppraisalService 
     * @param _dialogRef 
     * @param _dialogData 
     */
    constructor(_formBuilder: FormBuilder, 
                private _loanAppraisalService: LoanAppraisalService,
                public _dialogRef: MatDialogRef<LoanPartnerUpdateComponent>,
                @Inject(MAT_DIALOG_DATA) private _dialogData: any,
                private _matSnackBar: MatSnackBar) { 

        // Fetch selected loan officer details from the dialog's data attribute and initialize the form ...

        this.selectedLoanOfficer = _dialogData.loanOfficer;
        if (this._dialogData.operation === 'modifyOfficer') {
            this.dialogTitle = 'Modify Appraisal Officer';
        }
        
        this.loanOfficerForm = _formBuilder.group({
            serialNumber: [ this.selectedLoanOfficer.serialNumber || '' ],
            businessPartnerId: [ this.selectedLoanOfficer.businessPartnerId || '' ],
            roleType: [ this.selectedLoanOfficer.roleType || '' ],
            startDate: [ this.selectedLoanOfficer.startDate || '' ]
        });
        
    }

    /**
     * ngOnInit()
     */
    ngOnInit() {
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.loanOfficerForm.valid) {
            var formData = this.loanOfficerForm.value;
            this.selectedLoanOfficer.businessPartnerId = formData.businessPartnerId;
            this.selectedLoanOfficer.roleType = formData.roleType;
            this.selectedLoanOfficer.startDate = formData.startDate;
            if (this._dialogData.operation === 'modifyOfficer') {
                this._loanAppraisalService.updateLoanOfficer(this.selectedLoanOfficer).subscribe(response => {
                    this._matSnackBar.open('Loan partner updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedLoanOfficer.loanApplicationId = this._dialogData.loanApplicationId;
                this._loanAppraisalService.createLoanOfficer(this.selectedLoanOfficer).subscribe(response => {
                    this._matSnackBar.open('Loan partner created successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
        }
    }

    /**
     * closeDialog()
     */
    closeDialog(): void {
        this._dialogRef.close({ 'refresh': false });
    }
}
