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

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent {

    loanApplicationId: string;

    selectedLIE: LIEModel;
    lieList: any;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _dialogRef 
     */
    constructor(_formBuilder: FormBuilder, public _loanEnquiryService: LoanEnquiryService, private _router: Router, private _dialogRef: MatDialog,
            private _loanMonitoringService: LoanMonitoringService) {
            
        _loanEnquiryService.selectedLoanApplicationId.subscribe(data => {
            this.loanApplicationId = data;
            _loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                this.lieList = data;
            });
        });
        
        _loanMonitoringService.selectedLIE.subscribe(data => {
            console.log('data', data);
            this.selectedLIE = data;
        })
    }

    /**
     * redirectToMonitorLoan()
     */
    redirectToMonitorLoan(): void {
        this._router.navigate(['/enquiryReview']);
    }

    /**
     * addLIE()
     */
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
            this._loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                this.lieList = data;
            });
        });    
    }

    /**
     * addLIEReportAndFee()
     */
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
        });    
    }
}
