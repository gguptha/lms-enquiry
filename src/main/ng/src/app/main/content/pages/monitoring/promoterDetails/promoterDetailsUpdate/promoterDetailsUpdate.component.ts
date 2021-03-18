import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { PromoterDetailsModel } from 'app/main/content/model/promoterDetails.model';
import { PromoterDetailsItemModel } from 'app/main/content/model/promoterDetailsItem.model';
import { MonitoringRegEx } from 'app/main/content/others/monitoring.regEx';

@Component({
    selector: 'fuse-promoter-details-update-dialog',
    templateUrl: './promoterDetailsUpdate.component.html',
    styleUrls: ['./promoterDetailsUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class PromoterDetailsUpdateDialogComponent {

    dialogTitle = 'Add New Promoter Details';

    selectedPromoterDetails: PromoterDetailsModel;
    selectedPromoterDetailsItem: PromoterDetailsItemModel;

    promoterDetailsUpdateForm: FormGroup;
  
    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<PromoterDetailsUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected promoter details from the dialog's data attribute.
        if (_dialogData.selectedPromoterDetails !== undefined) {
            this.selectedPromoterDetails = _dialogData.selectedPromoterDetails;
            this.dialogTitle = 'Modify Promoter Details';
        }
        if (_dialogData.selectedPromoterDetailsItem !== undefined) {
            this.selectedPromoterDetailsItem = _dialogData.selectedPromoterDetailsItem;
        }
        else {
            this.selectedPromoterDetailsItem = new PromoterDetailsItemModel({});
        }

        this.promoterDetailsUpdateForm = _formBuilder.group({
            shareHoldingCompany: [this.selectedPromoterDetailsItem.shareHoldingCompany],
            paidupCapitalEquitySanction: [this.selectedPromoterDetailsItem.paidupCapitalEquitySanction, [Validators.pattern(MonitoringRegEx.genericAmount)]],
            paidupCapitalEquityCurrent: [this.selectedPromoterDetailsItem.paidupCapitalEquityCurrent, [Validators.pattern(MonitoringRegEx.genericAmount)]],
            equityLinkInstrumentSanction: [this.selectedPromoterDetailsItem.equityLinkInstrumentSanction, [Validators.pattern(MonitoringRegEx.genericAmount)]],
            equityLinkInstrumentCurrent: [this.selectedPromoterDetailsItem.equityLinkInstrumentCurrent, [Validators.pattern(MonitoringRegEx.genericAmount)]]
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.promoterDetailsUpdateForm.valid) {
            var promoterDetailsItem: PromoterDetailsItemModel = new PromoterDetailsItemModel(this.promoterDetailsUpdateForm.value);
            if (this._dialogData.operation === 'addPromoterDetails') {
                console.log('promoterDetails before', this.selectedPromoterDetails);
                if (this.selectedPromoterDetails === undefined) {
                    this.selectedPromoterDetails = new PromoterDetailsModel({});
                }
                this.selectedPromoterDetails.promoterDetailsItemSet.push(promoterDetailsItem);
                console.log('promoterDetails after', this.selectedPromoterDetails);
                console.log('promoterDetailsItem', this.selectedPromoterDetailsItem);

                // If promoter details information is not available
                if (this.selectedPromoterDetails.id === '') {
                    console.log('in add: save');
                    this._loanMonitoringService.savePromoterDetails(this.selectedPromoterDetails, this._dialogData.loanApplicationId).subscribe(() => {
                        this._matSnackBar.open('Promoter details added successfully.', 'OK', { duration: 7000 });
                        this._dialogRef.close({ 'refresh': true });
                    });
                }
                else { // If promoter details information is available, but new company details are being added
                    console.log('in add: update');
                    this._loanMonitoringService.updatePromoterDetails(this.selectedPromoterDetails).subscribe(() => {
                        this._matSnackBar.open('Promoter details added successfully.', 'OK', { duration: 7000 });
                        this._dialogRef.close({ 'refresh': true });
                    });
                }
            }
            else {
                this.selectedPromoterDetails.promoterDetailsItemSet.forEach(item => {
                    if (item.id === this.selectedPromoterDetailsItem.id) {
                        item.shareHoldingCompany = promoterDetailsItem.shareHoldingCompany;
                        item.paidupCapitalEquitySanction = promoterDetailsItem.paidupCapitalEquitySanction;
                        item.paidupCapitalEquityCurrent  = promoterDetailsItem.paidupCapitalEquityCurrent;
                        item.equityLinkInstrumentSanction  = promoterDetailsItem.equityLinkInstrumentSanction;
                        item.equityLinkInstrumentCurrent  = promoterDetailsItem.equityLinkInstrumentCurrent;
                    }
                })
                this._loanMonitoringService.updatePromoterDetails(this.selectedPromoterDetails).subscribe(() => {
                    this._matSnackBar.open('Promoter details updated successfully.', 'OK', { duration: 7000 });
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
