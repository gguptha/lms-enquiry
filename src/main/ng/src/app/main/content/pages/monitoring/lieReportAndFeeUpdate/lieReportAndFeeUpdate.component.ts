import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LIEModel } from 'app/main/content/model/lie.model';
import { LoanMonitoringService } from '../loanMonitoring.service';
import { LIEReportAndFeeModel } from 'app/main/content/model/lieReportAndFee.model';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';

@Component({
    selector: 'fuse-lie-report-fee-update-dialog',
    templateUrl: './lieReportAndFeeUpdate.component.html',
    styleUrls: ['./lieReportAndFeeUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class LIEReportAndFeeUpdateDialogComponent {

    dialogTitle = 'Add LIE Report Submission';

    selectedLIE: LIEModel;
    selectedLIEReportAndFee: LIEReportAndFeeModel;

    lieUpdateForm: FormGroup;

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
        public _dialogRef: MatDialogRef<LIEReportAndFeeUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        this.selectedLIE = _dialogData.selectedLIE;
        if (_dialogData.selectedLIEReportAndFee !== undefined) {
            console.log('_dialogData.selectedLIEReportAndFee', _dialogData.selectedLIEReportAndFee);
            this.selectedLIEReportAndFee = _dialogData.selectedLIEReportAndFee;
        }
        else {
            this.selectedLIEReportAndFee = new LIEReportAndFeeModel({});
        }

        this.lieUpdateForm = _formBuilder.group({
            reportType: [this.selectedLIEReportAndFee.reportType],
            dateOfReceipt: [this.selectedLIEReportAndFee.dateOfReceipt || ''],
            invoiceDate: [this.selectedLIEReportAndFee.invoiceDate || ''],
            invoiceNo: [this.selectedLIEReportAndFee.invoiceNo],
            feeAmount: [this.selectedLIEReportAndFee.feeAmount],
            statusOfFeeReceipt: [this.selectedLIEReportAndFee.statusOfFeeReceipt],
            statusOfFeePaid: [this.selectedLIEReportAndFee.statusOfFeePaid],
            documentTitle: [this.selectedLIEReportAndFee.documentTitle],
            nextReportDate: [this.selectedLIEReportAndFee.nextReportDate || ''],
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.lieUpdateForm.valid) {
            const lieReportAndFee: LIEReportAndFeeModel = new LIEReportAndFeeModel(this.lieUpdateForm.value);
            if (this._dialogData.operation === 'addLIEReportAndFee') {
                this._loanMonitoringService.saveLIEReportAndFee(lieReportAndFee, this.selectedLIE.id).subscribe(() => {
                    this._matSnackBar.open('LIE report added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close();
                });
            }
            else {
                this.selectedLIEReportAndFee.reportType = lieReportAndFee.reportType;
                this.selectedLIEReportAndFee.dateOfReceipt = lieReportAndFee.dateOfReceipt;
                this.selectedLIEReportAndFee.invoiceDate = lieReportAndFee.invoiceDate;
                this.selectedLIEReportAndFee.invoiceNo = lieReportAndFee.invoiceNo;
                this.selectedLIEReportAndFee.feeAmount = lieReportAndFee.feeAmount;
                this.selectedLIEReportAndFee.statusOfFeeReceipt = lieReportAndFee.statusOfFeeReceipt;
                this.selectedLIEReportAndFee.statusOfFeePaid = lieReportAndFee.statusOfFeePaid;
                this.selectedLIEReportAndFee.documentTitle = lieReportAndFee.documentTitle;
                this.selectedLIEReportAndFee.nextReportDate = lieReportAndFee.nextReportDate;

                this._loanMonitoringService.updateLIEReportAndFee(this.selectedLIEReportAndFee).subscribe(() => {
                    this._matSnackBar.open('LIE report updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close();
                });
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
