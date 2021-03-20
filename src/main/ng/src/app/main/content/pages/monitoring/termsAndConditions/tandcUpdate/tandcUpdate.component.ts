import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { TandCModel } from 'app/main/content/model/tandc.model';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';

@Component({
    selector: 'fuse-tandc-update-dialog',
    templateUrl: './tandcUpdate.component.html',
    styleUrls: ['./tandcUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class TandCUpdateDialogComponent {

    dialogTitle = 'Add New T&C';

    selectedTandC: TandCModel;

    tandcUpdateForm: FormGroup;

    communications = LoanMonitoringConstants.communications;
    documentTypes = LoanMonitoringConstants.documentTypes;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<TandCUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedTandC !== undefined) {
            this.selectedTandC = _dialogData.selectedTandC;
            this.dialogTitle = 'Modify T&C';
        }
        else {
            this.selectedTandC = new TandCModel({});
        }

        this.tandcUpdateForm = _formBuilder.group({
            documentType: [this.selectedTandC.documentType || ''],
            documentTitle: [this.selectedTandC.documentTitle || ''],
            communication: [this.selectedTandC.communication || ''],
            remarks: [this.selectedTandC.remarks || ''],
            borrowerRequestLetterDate: [this.selectedTandC.borrowerRequestLetterDate || ''],
            dateofIssueofAmendedSanctionLetter: [this.selectedTandC.dateofIssueofAmendedSanctionLetter || ''],
            file: ['']
        });
    }

    /**
     * onFileSelect()
     * @param event 
     */
    onFileSelect(event) {
        if (event.target.files.length > 0) {
            const file = event.target.files[0];
            this.tandcUpdateForm.get('file').setValue(file);
        }
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.tandcUpdateForm.valid) {
            var tandc: TandCModel = new TandCModel(this.tandcUpdateForm.value);
            var dt = new Date(tandc.borrowerRequestLetterDate);
            tandc.borrowerRequestLetterDate = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(tandc.dateofIssueofAmendedSanctionLetter);
            tandc.dateofIssueofAmendedSanctionLetter = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            if (this._dialogData.operation === 'addT&C') {
                if (this.tandcUpdateForm.get('file').value !== '') {
                    var formData = new FormData();
                    formData.append('file', this.tandcUpdateForm.get('file').value);      
                    this._loanMonitoringService.uploadVaultDocument(formData).subscribe(
                        (response) => {
                            tandc.fileReference = response.fileReference;
                            this._loanMonitoringService.saveTandC(tandc, this._dialogData.loanApplicationId).subscribe(() => {
                                this._matSnackBar.open('T&C added successfully.', 'OK', { duration: 7000 });
                                this._dialogRef.close({ 'refresh': true });
                            });
                        },
                        (error) => {
                            this._matSnackBar.open('Unable to upload the file. Pls try again after sometime or contact your system administrator', 
                                'OK', { duration: 7000 });
                        }
                    );
                }
                else
                {
                    this._loanMonitoringService.saveTandC(tandc, this._dialogData.loanApplicationId).subscribe(() => {
                        this._matSnackBar.open('T&C added successfully.', 'OK', { duration: 7000 });
                        this._dialogRef.close({ 'refresh': true });
                    });
                }
            }
            else {
                if (this.tandcUpdateForm.get('file').value !== '') {
                    var formData = new FormData();
                    formData.append('file', this.tandcUpdateForm.get('file').value);      
                    this._loanMonitoringService.uploadVaultDocument(formData).subscribe(
                        (response) => {
                            this.selectedTandC.documentType  = tandc.documentType;
                            this.selectedTandC.documentTitle  = tandc.documentTitle;
                            this.selectedTandC.communication  = tandc.communication;
                            this.selectedTandC.borrowerRequestLetterDate  = tandc.borrowerRequestLetterDate;
                            this.selectedTandC.dateofIssueofAmendedSanctionLetter  = tandc.dateofIssueofAmendedSanctionLetter;
                            this.selectedTandC.remarks  = tandc.remarks;
                            this.selectedTandC.fileReference = response.fileReference;
                            this._loanMonitoringService.updateTandC(this.selectedTandC).subscribe(() => {
                                this._matSnackBar.open('T&C updated successfully.', 'OK', { duration: 7000 });
                                this._dialogRef.close({ 'refresh': true });
                            });            
                        },
                        (error) => {
                            this._matSnackBar.open('Unable to upload the file. Pls try again after sometime or contact your system administrator', 
                                'OK', { duration: 7000 });
                        }
                    );
                }
                else
                {
                    this.selectedTandC.documentType  = tandc.documentType;
                    this.selectedTandC.documentTitle  = tandc.documentTitle;
                    this.selectedTandC.communication  = tandc.communication;
                    this.selectedTandC.borrowerRequestLetterDate  = tandc.borrowerRequestLetterDate;
                    this.selectedTandC.dateofIssueofAmendedSanctionLetter  = tandc.dateofIssueofAmendedSanctionLetter;
                    this.selectedTandC.remarks  = tandc.remarks;
                    this._loanMonitoringService.updateTandC(this.selectedTandC).subscribe(() => {
                        this._matSnackBar.open('T&C updated successfully.', 'OK', { duration: 7000 });
                        this._dialogRef.close({ 'refresh': true });
                    });            
                }
            }
        }
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close({ 'refresh': false });
    }
    
    /**
     * getFileURL()
     * @param fileReference
     */
    getFileURL(fileReference: string): string {
        return 'enquiry/api/download/' + fileReference;
    }    
}
