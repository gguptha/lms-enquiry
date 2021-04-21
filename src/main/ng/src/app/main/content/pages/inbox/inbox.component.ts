import { Component, OnInit, ViewChild } from '@angular/core';
import { InboxService } from './inbox.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { InboxItemsComponent } from './inbox-items/inbox-items.component';
import { EnquiryAlertsService } from '../enquiry/enquiryAlerts/enquiryAlerts.service';
import { BehaviorSubject } from 'rxjs';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';

@Component({
    selector: 'app-inbox',
    templateUrl: './inbox.component.html',
    styleUrls: ['./inbox.component.scss'],
    animations: fuseAnimations
})
export class InboxComponent implements OnInit {

    @ViewChild(InboxItemsComponent) inboxItemsComponent: InboxItemsComponent;
    
    constructor(private _inboxService: InboxService ,
                private _matSnackBar: MatSnackBar,
                private _router: Router, private _loanEnquiryService: LoanEnquiryService) {
        
    }

    ngOnInit(): void {
    }

    /**
     * reviewTask()
     */
     reviewTask(): void {
        this._loanEnquiryService.getLoanApplicationByLoanContractId(this.inboxItemsComponent.selectedItem.lanContractId).subscribe(response => {
            this._loanEnquiryService.selectedEnquiry.next(response.loanApplication);
            if (this._loanEnquiryService.selectedLoanApplicationId !== undefined) {
                this._loanEnquiryService.selectedLoanApplicationId.next(response.loanApplication.id);
                this._loanEnquiryService.selectedLoanApplicationPartyNumber.next(response.loanApplication.busPartnerNumber);
            }
            else {
                this._loanEnquiryService.selectedLoanApplicationId = new BehaviorSubject(response.loanApplication.id);
                this._loanEnquiryService.selectedLoanApplicationPartyNumber = new BehaviorSubject(response.loanApplication.busPartnerNumber);
            }
            this._router.navigate(['/loanMonitoring']);
        });
    }

    /**
     * rejectTask()
     */
    rejectTask(): void {
        let selectedInboxItem = this.inboxItemsComponent.selectedItem;
        let workFlowProcessRequestResource = {
            'businessProcessId': selectedInboxItem.businessProcessId,
            'processName': selectedInboxItem.processName,
            'processInstanceId': selectedInboxItem.id,
            'rejectionReason': 'Rejected'
        }        
        this._inboxService.rejectTask(workFlowProcessRequestResource).subscribe(response => {
            this._matSnackBar.open( 'Selected task is rejected and email notification was sent to requestor', 'Ok', { duration: 7000 });
            this.inboxItemsComponent.refreshList();
        });
    }

    /**
     * approveTask()
     */
    approveTask(): void {
        let selectedInboxItem = this.inboxItemsComponent.selectedItem;
        let workFlowProcessRequestResource = {
            'businessProcessId': selectedInboxItem.businessProcessId,
            'processName': selectedInboxItem.processName,
            'processInstanceId': selectedInboxItem.id,
            'rejectionReason': ''
        }        
        this._inboxService.approveTask(workFlowProcessRequestResource).subscribe(response => {
            this._matSnackBar.open( 'Selected task is approved and email notification was sent to requestor', 'Ok', { duration: 7000 });
            this.inboxItemsComponent.refreshList();
        });
    }

    /**
     * refreshTasks()
     */
    refreshTasks(): void {
        this._inboxService.fetchTasks().subscribe(response => {
            this.inboxItemsComponent.inboxItems = response;
        });
    }
}
