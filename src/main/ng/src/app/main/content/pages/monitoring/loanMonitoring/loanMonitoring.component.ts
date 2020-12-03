import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { LoanEnquiryService } from '../../enquiry/enquiryApplication.service';
import { MatDialog } from '@angular/material';
import { LieUpdateDialogComponent } from '../lieUpdate/lieUpdate.component';

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent {

    loanApplicationId: string;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _dialogRef 
     */
    constructor(_formBuilder: FormBuilder, public _service: LoanEnquiryService, private _router: Router, private _dialogRef: MatDialog) {
        _service.selectedLoanApplicationId.subscribe(data => {
            this.loanApplicationId = data;
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
        const dialogRef = this._dialogRef.open(LieUpdateDialogComponent, {
            panelClass: 'fuse-lie-update-dialog',
            width: '750px',
            data: {
                operation: 'addLIE',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { });    
    }
}
