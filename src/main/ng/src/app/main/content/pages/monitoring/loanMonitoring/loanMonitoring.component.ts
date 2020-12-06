import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { LoanEnquiryService } from '../../enquiry/enquiryApplication.service';
import { MatDialog } from '@angular/material';
import { LieUpdateDialogComponent } from '../lieUpdate/lieUpdate.component';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent {

    loanApplicationId: string;

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
        const dialogRef = this._dialogRef.open(LieUpdateDialogComponent, {
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
}
