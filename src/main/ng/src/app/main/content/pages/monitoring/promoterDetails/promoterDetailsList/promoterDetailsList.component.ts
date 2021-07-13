import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { MonitoringRegEx } from 'app/main/content/others/monitoring.regEx';
import { LoanEnquiryService } from '../../../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { PromoterDetailsUpdateDialogComponent } from '../promoterDetailsUpdate/promoterDetailsUpdate.component';

@Component({
    selector: 'fuse-promoter-details-list',
    templateUrl: './promoterDetailsList.component.html',
    styleUrls: ['./promoterDetailsList.component.scss'],
    animations: fuseAnimations
})
export class PromoterDetailsItemListComponent implements OnInit {

    loanApplicationId: string;
    selectedPromoterDetails: any;
    selectedPromoterDetailsItem: any;

    promoterHeaderDetailsForm: FormGroup;
    
    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    displayedColumns = [
        'serialNumber', 'shareHoldingCompany', 'paidupCapitalEquitySanction', 'paidupCapitalEquityCurrent','equityLinkInstrumentSanction', 
                'equityLinkInstrumentCurrent'
    ];

    /**
     * constructor()
     */
    constructor(_enquiryService: LoanEnquiryService, private _loanMonitoringService: LoanMonitoringService, private _dialog: MatDialog,
                        private _formBuilder: FormBuilder, private _matSnackBar: MatSnackBar) {

        this.loanApplicationId = _enquiryService.selectedLoanApplicationId.value;

        this.promoterHeaderDetailsForm = this._formBuilder.group({
            dateOfChange: [''],
            groupExposure: ['', [Validators.pattern(MonitoringRegEx.genericAmount)]]
        });

        _loanMonitoringService.getPromoterDetails(this.loanApplicationId).subscribe(data => {
            console.log('data', data);
            if (data.length > 0) {
                this.selectedPromoterDetails = data[0].promoterDetail;
                this.selectedPromoterDetails.promoterDetailItemSet.sort((a, b) => b.serialNumber - a.serialNumber);
                this.dataSource = new MatTableDataSource(this.selectedPromoterDetails.promoterDetailItemSet);
                this.dataSource.sort = this.sort;
                this.promoterHeaderDetailsForm.controls.dateOfChange.setValue(this.selectedPromoterDetails.dateOfChange);
                this.promoterHeaderDetailsForm.controls.groupExposure.setValue(this.selectedPromoterDetails.groupExposure);
            }
        });
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    /**
     * onSelect()
     * @param selectedPromoterDetailsItem
     */
    onSelect(selectedPromoterDetailsItem: any): void {
        this.selectedPromoterDetailsItem = selectedPromoterDetailsItem;
    }

    /**
     * updatePromoterDetails()
     */
    updatePromoterDetails(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedPromoterDetails': this.selectedPromoterDetails,
            'selectedPromoterDetailsItem': undefined
        };
        if (operation === 'updatePromoterDetails') {
            data.selectedPromoterDetailsItem = this.selectedPromoterDetailsItem;
        }
        const dialogRef = this._dialog.open(PromoterDetailsUpdateDialogComponent, {
            panelClass: 'fuse-promoter-details-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getPromoterDetails(this.loanApplicationId).subscribe(data => {
                    if (data.length > 0) {
                        this.selectedPromoterDetails = data[0].promoterDetail;
                        this.selectedPromoterDetails.promoterDetailItemSet.sort((a, b) => b.serialNumber - a.serialNumber);
                        this.dataSource.data = this.selectedPromoterDetails.promoterDetailItemSet;
                    }
                });
                this._loanMonitoringService.getLoanMonitor(this.loanApplicationId).subscribe(data => {
                    this._loanMonitoringService.loanMonitor.next(data);
                })
            }
        });
    }
   
    /**
     * savePromoterHeaderDetails()
     */
     savePromoterHeaderDetails(): void {
        if (this.promoterHeaderDetailsForm.valid) {
            this.selectedPromoterDetails.dateOfChange = this.promoterHeaderDetailsForm.value.dateOfChange;
            this.selectedPromoterDetails.groupExposure = this.promoterHeaderDetailsForm.value.groupExposure;
            if (this.selectedPromoterDetails.id) {
                this._loanMonitoringService.updatePromoterDetails(this.selectedPromoterDetails).subscribe((response) => {
                    this.selectedPromoterDetails = response;
                    console.log(this.selectedPromoterDetails);
                    this._matSnackBar.open('Promoter details updated successfully.', 'OK', { duration: 5000 });
                });
            }
            else {
                this._loanMonitoringService.savePromoterDetails(this.selectedPromoterDetails, this.loanApplicationId).subscribe((response) => {
                    this.selectedPromoterDetails = response;
                    console.log(this.selectedPromoterDetails);
                    this._matSnackBar.open('Promoter details updated successfully.', 'OK', { duration: 5000 });
                });
            }
        }
    }
}
