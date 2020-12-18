import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { MatDialog } from '@angular/material';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from './loanMonitoring.service';
import { LIEUpdateDialogComponent } from './lieUpdate/lieUpdate.component';
import { LIEReportAndFeeUpdateDialogComponent } from './lieReportAndFeeUpdate/lieReportAndFeeUpdate.component';
import { LIEModel } from '../../model/lie.model';
import { LIEReportAndFeeModel } from '../../model/lieReportAndFee.model';
import { LFAModel } from '../../model/lfa.model';
import { LFAUpdateDialogComponent } from './lfaUpdate/lfaUpdate.component';
import { LFAReportAndFeeModel } from '../../model/lfaReportAndFee.model';
import { LFAReportAndFeeUpdateDialogComponent } from './lfaReportAndFeeUpdate/lfaReportAndFeeUpdate.component';

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent {

    loanApplicationId: string;

    selectedLIE: LIEModel;
    selectedLIEReportAndFee: LIEReportAndFeeModel;
    selectedLFA: LFAModel;
    selectedLFAReportAndFee: LFAReportAndFeeModel;

    lieList: any;
    lieReportAndFeeList: any;
    lfaList: any;
    lfaReportAndFeeList: any;
    
    /**
     * constructor()
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _dialogRef 
     */
    constructor(_formBuilder: FormBuilder, public _loanEnquiryService: LoanEnquiryService, private _router: Router, private _dialogRef: MatDialog,
            private _loanMonitoringService: LoanMonitoringService) {
        
        // All about LIE

        this.lieReportAndFeeList = [];

        _loanEnquiryService.selectedLoanApplicationId.subscribe(data => {
            this.loanApplicationId = data;
            _loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                this.lieList = data;
            });
        });
        
        _loanMonitoringService.selectedLIE.subscribe(data => {
            this.selectedLIE = new LIEModel(data);
            if (this.selectedLIE.id !== '') {
                _loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        })

        _loanMonitoringService.selectedLIEReportAndFee.subscribe(data => {
            this.selectedLIEReportAndFee = new LIEReportAndFeeModel(data);
        });

        // All about LFA

        this.lfaReportAndFeeList = [];

        _loanEnquiryService.selectedLoanApplicationId.subscribe(data => {
            this.loanApplicationId = data;
            _loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                this.lfaList = data;
            });
        });

        _loanMonitoringService.selectedLFA.subscribe(data => {
            this.selectedLFA = new LFAModel(data);
            if (this.selectedLFA.id !== '') {
                _loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        })
        
        _loanMonitoringService.selectedLFAReportAndFee.subscribe(data => {
            this.selectedLFAReportAndFee = new LFAReportAndFeeModel(data);
        });
    }

    /**
     * redirectToMonitorLoan()
     */
    redirectToMonitorLoan(): void {
        this._router.navigate(['/enquiryReview']);
    }

    addLIE(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEUpdateDialogComponent, {
            panelClass: 'fuse-lie-update-dialog',
            width: '750px',
            data: {
                operation: 'addLIE',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                    this.lieList = data;
                });
            }
        });    
    }

    updateLIE(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEUpdateDialogComponent, {
            panelClass: 'fuse-lie-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLIE',
                loanApplicationId: this.loanApplicationId,
                selectedLIE: this.selectedLIE
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                    this.lieList = data;
                });
            }
        });    
    }

    addLIEReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lie-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'addLIEReportAndFee',
                selectedLIE: this.selectedLIE
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        });    
    }

    updateLIEReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lie-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLIEReportAndFee',
                selectedLIE: this.selectedLIE,
                selectedLIEReportAndFee: this.selectedLIEReportAndFee
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        });    
    }

    addLFA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAUpdateDialogComponent, {
            panelClass: 'fuse-lfa-update-dialog',
            width: '750px',
            data: {
                operation: 'addLFA',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                    this.lfaList = data;
                });
            }
        });    
    }

    updateLFA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAUpdateDialogComponent, {
            panelClass: 'fuse-lfa-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLFA',
                loanApplicationId: this.loanApplicationId,
                selectedLFA: this.selectedLFA
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                    this.lfaList = data;
                });
            }
        });    
    }

    addLFAReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lfa-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'addLFAReportAndFee',
                selectedLFA: this.selectedLFA
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        });    
    }

    updateLFAReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lfa-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLFAReportAndFee',
                selectedLFA: this.selectedLFA,
                selectedLFAReportAndFee: this.selectedLFAReportAndFee
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        });    
    }
}
