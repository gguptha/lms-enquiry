import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { DatePipe } from '@angular/common';
import { EnquiryAlertsService } from '../enquiryAlerts.service';
import {ActivatedRoute} from "@angular/router";

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
    rejectionCategory: string;

    rejectionCategories: Array<any>;


  /**
     * constructor()
     * @param _dialogRef
     * @param _data
     * @param _datePipe
     */
    constructor(public _dialogRef: MatDialogRef<EnquiryRejectDialogComponent>,_route: ActivatedRoute,
        @Inject(MAT_DIALOG_DATA) _data: any, _datePipe: DatePipe, private _service: EnquiryAlertsService) {

        this.loanApplication = _data.loanApplication;
        this.rejectDate = _datePipe.transform(new Date(), 'dd/MM/yyyy');

        this.rejectionCategories  = [
                                {code:'1',value:'Rejected by Borrower'},
                                {code:'2',value:'Rejected by BD'},
                                {code:'3',value:'Rejected by ICC'},
                                {code:'4',value:'Rejected by Appraisal Officer'},
                                {code:'5',value:'Rejected by Board'}]; //_route.snapshot.data.routeResolvedData[9]._embedded.rejectionCategories;


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

        this.loanApplication.rejectionCategory = this.rejectionCategory;
        this.loanApplication.rejectionReason = this.rejectMessage;
        this.loanApplication.rejectionDate = this.rejectDate;

        this._service.rejectEnquiry(this.loanApplication,  this.rejectMessage, this.rejectionCategory, this.rejectDate).subscribe((result) => {
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
