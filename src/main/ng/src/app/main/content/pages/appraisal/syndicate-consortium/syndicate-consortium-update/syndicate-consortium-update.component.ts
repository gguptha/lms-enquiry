import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { LoanAppraisalRegEx } from '../../loanAppraisal.regEx';
import { LoanAppraisalService } from '../../loanAppraisal.service';

@Component({
  selector: 'fuse-syndicate-consortium-update',
  templateUrl: './syndicate-consortium-update.component.html',
  styleUrls: ['./syndicate-consortium-update.component.scss']
})
export class SyndicateConsortiumUpdateComponent implements OnInit {

    dialogTitle = "Add Syndicate Consortium";

    syndicateConsortiumForm: FormGroup;

    selectedSyndicateConsortium: any;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanAppraisalService 
     * @param _dialogRef 
     * @param _dialogData 
     */
    constructor(private _formBuilder: FormBuilder, 
                private _loanAppraisalService: LoanAppraisalService,
                public _dialogRef: MatDialogRef<SyndicateConsortiumUpdateComponent>,
                @Inject(MAT_DIALOG_DATA) private _dialogData: any,
                private _matSnackBar: MatSnackBar) { 

        // Fetch selected loan officer details from the dialog's data attribute
        console.log('_dialogData', _dialogData);
        this.selectedSyndicateConsortium = _dialogData.syndicateConsortium;

        // Change diglog title and fetch partners based on the role assigned ...
        if (this._dialogData.operation === 'modifySyndicateConsortium') {
            this.dialogTitle = 'Modify Syndicate Consortium';
        }
    }

    /**
     * ngOnInit()
     */
    ngOnInit() {
        this.syndicateConsortiumForm = this._formBuilder.group({
            serialNumber: [ this.selectedSyndicateConsortium.serialNumber || '' ],
            sanctionedAmount: [ this.selectedSyndicateConsortium.sanctionedAmount || '0', [Validators.pattern(LoanAppraisalRegEx.genericAmount)] ],
            currency: [ this.selectedSyndicateConsortium.currency || 'INR' ],
            lead: [ (this.selectedSyndicateConsortium.lead === true ? 'true' : 'false') || '' ],
            approvalStatus: [ this.selectedSyndicateConsortium.approvalStatus || '' ],
            documentStage: [ this.selectedSyndicateConsortium.documentStage || '' ],
            disbursedAmount: [ this.selectedSyndicateConsortium.disbursedAmount || '0', [Validators.pattern(LoanAppraisalRegEx.genericAmount)] ],
            disbursementStatus: [ this.selectedSyndicateConsortium.disbursementStatus || '' ]
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.syndicateConsortiumForm.valid) {
            var formValues = this.syndicateConsortiumForm.value;
            this.selectedSyndicateConsortium.sanctionedAmount = formValues.sanctionedAmount;            
            this.selectedSyndicateConsortium.currency = formValues.currency;
            this.selectedSyndicateConsortium.lead = formValues.lead;
            this.selectedSyndicateConsortium.approvalStatus = formValues.approvalStatus;
            this.selectedSyndicateConsortium.documentStage = formValues.documentStage;
            this.selectedSyndicateConsortium.disbursedAmount = formValues.disbursedAmount;
            this.selectedSyndicateConsortium.disbursementStatus = formValues.disbursementStatus;
            if (this._dialogData.operation === 'modifySyndicateConsortium') {
                this._loanAppraisalService.updateSyndicateConsortium(this.selectedSyndicateConsortium).subscribe(() => {
                    this._matSnackBar.open('Syndicate consortium updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedSyndicateConsortium.loanApplicationId = this._dialogData.loanApplicationId;
                this._loanAppraisalService.createSyndicateConsortium(this.selectedSyndicateConsortium).subscribe(() => {
                    this._matSnackBar.open('Syndicate consortium added successfully.', 'OK', { duration: 7000 });
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
