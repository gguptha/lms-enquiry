import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { DatePipe } from '@angular/common';
import { EnquiryAlertsService } from '../enquiryAlerts.service';

@Component({
    selector: 'fuse-enquiry-reject-dialog',
    templateUrl: './enquiryReject.component.html',
    styleUrls: ['./enquiryReject.component.scss'],
    animations: fuseAnimations
})
export class EnquiryRejectDialogComponent implements OnInit {

    dialogTitle = 'Reject Enquiry';

    loanApplication: LoanApplicationModel;
    rejectDate: any;
    rejectMessage: string;

    /**
     * constructor()
     * @param _dialogRef 
     * @param _data 
     * @param _datePipe 
     */
    constructor(private _dialogRef: MatDialogRef<EnquiryRejectDialogComponent>,
        @Inject(MAT_DIALOG_DATA) _data: any, _datePipe: DatePipe, private _service: EnquiryAlertsService) {

        this.loanApplication = _data.loanApplication;
        this.rejectDate = _datePipe.transform(new Date(), 'dd/MM/yyyy');
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    /**
     * 
     */
    reject(): void {
        console.log(this.loanApplication);
        this._service.rejectEnquiry(this.loanApplication, this.rejectMessage).subscribe((result) => {
            console.log(result);
            this._dialogRef.close({ action: 'Rejected' });
        });
    }

    /**
     * cancel()
     */
    cancel(): void {
        this._dialogRef.close({ action: 'Cancel' });
    }
}
