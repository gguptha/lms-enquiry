import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../loanMonitoring.service';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LFAModel } from 'app/main/content/model/lfa.model';
import { LFAReportAndFeeModel } from 'app/main/content/model/lfaReportAndFee.model';

@Component({
    selector: 'fuse-lfa-report-fee-update-dialog',
    templateUrl: './lfaReportAndFeeUpdate.component.html',
    styleUrls: ['./lfaReportAndFeeUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class LFAReportAndFeeUpdateDialogComponent {

    dialogTitle = 'Add LFA Report Submission';

    selectedLFA: LFAModel;
    selectedLFAReportAndFee: LFAReportAndFeeModel;

    lfaUpdateForm: FormGroup;

    reportTypes = LoanMonitoringConstants.reportTypes;
    feeReceiptStatuses = LoanMonitoringConstants.feeReceiptStatuses;
    feePaidStatuses = LoanMonitoringConstants.feePaidStatuses;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<LFAReportAndFeeUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        this.selectedLFA = _dialogData.selectedLFA;
        if (_dialogData.selectedLFAReportAndFee !== undefined) {
            console.log('_dialogData.selectedLFAReportAndFee', _dialogData.selectedLFAReportAndFee);
            this.selectedLFAReportAndFee = _dialogData.selectedLFAReportAndFee;
        }
        else {
            this.selectedLFAReportAndFee = new LFAReportAndFeeModel({});
        }

        this.lfaUpdateForm = _formBuilder.group({
            reportType: [this.selectedLFAReportAndFee.reportType],
            dateOfReceipt: [this.selectedLFAReportAndFee.dateOfReceipt || ''],
            invoiceDate: [this.selectedLFAReportAndFee.invoiceDate || ''],
            invoiceNo: [this.selectedLFAReportAndFee.invoiceNo],
            feeAmount: [this.selectedLFAReportAndFee.feeAmount],
            statusOfFeeReceipt: [this.selectedLFAReportAndFee.statusOfFeeReceipt],
            statusOfFeePaid: [this.selectedLFAReportAndFee.statusOfFeePaid],
            documentTitle: [this.selectedLFAReportAndFee.documentTitle],
            nextReportDate: [this.selectedLFAReportAndFee.nextReportDate || ''],
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.lfaUpdateForm.valid) {
            // To solve the utc time zone issue
            var lfaReportAndFee: LFAReportAndFeeModel = new LFAReportAndFeeModel(this.lfaUpdateForm.value);
            var dt = new Date(lfaReportAndFee.dateOfReceipt);
            lfaReportAndFee.dateOfReceipt = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(lfaReportAndFee.invoiceDate);
            lfaReportAndFee.invoiceDate = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(lfaReportAndFee.nextReportDate);
            lfaReportAndFee.nextReportDate = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));

            if (this._dialogData.operation === 'addLFAReportAndFee') {
                this._loanMonitoringService.saveLFAReportAndFee(lfaReportAndFee, this.selectedLFA.id).subscribe(() => {
                    this._matSnackBar.open('LFA report added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedLFAReportAndFee.reportType = lfaReportAndFee.reportType;
                this.selectedLFAReportAndFee.dateOfReceipt = lfaReportAndFee.dateOfReceipt;
                this.selectedLFAReportAndFee.invoiceDate = lfaReportAndFee.invoiceDate;
                this.selectedLFAReportAndFee.invoiceNo = lfaReportAndFee.invoiceNo;
                this.selectedLFAReportAndFee.feeAmount = lfaReportAndFee.feeAmount;
                this.selectedLFAReportAndFee.statusOfFeeReceipt = lfaReportAndFee.statusOfFeeReceipt;
                this.selectedLFAReportAndFee.statusOfFeePaid = lfaReportAndFee.statusOfFeePaid;
                this.selectedLFAReportAndFee.documentTitle = lfaReportAndFee.documentTitle;
                this.selectedLFAReportAndFee.nextReportDate = lfaReportAndFee.nextReportDate;
                this._loanMonitoringService.updateLFAReportAndFee(this.selectedLFAReportAndFee).subscribe(() => {
                    this._matSnackBar.open('LFA report updated successfully.', 'OK', { duration: 7000 });
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
