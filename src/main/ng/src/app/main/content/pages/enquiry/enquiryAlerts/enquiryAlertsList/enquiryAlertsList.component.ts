import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs';
import { fuseAnimations } from '@fuse/animations';
import { EnquiryAlertsService } from '../enquiryAlerts.service';
import { LoanApplicationResourceModel } from '../../../../model/loanApplicationResource.model';

@Component({
    selector: 'fuse-enquiry-alerts-list',
    templateUrl: './enquiryAlertsList.component.html',
    styleUrls: ['./enquiryAlertsList.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class EnquiryAlertsListComponent implements OnInit {

    dataSource: LoanApplicationDataSource;
    
    selectedEnquiry: LoanApplicationResourceModel;

    displayedColumns = [
        'functionalStatus', 'createdOn', 'enquiryNo', 'bpCode', 'projectName', 'projectLocationState', 'projectType', 
        'loanClass', 'projectCapacity', 'assistanceType', 'projectCost', 'loanAmount'
    ];

    constructor(private _service: EnquiryAlertsService) {
        this.dataSource = new LoanApplicationDataSource(_service);
    }
    
    ngOnInit(): void {
    }

    onSelect(enquiry: LoanApplicationResourceModel): void {
        this.selectedEnquiry = enquiry;
        this._service.selectedLoanApplicationId = new BehaviorSubject(enquiry.loanApplication.id);
    }
}

export class LoanApplicationDataSource extends MatTableDataSource<LoanApplicationResourceModel> {
    constructor(private _service: EnquiryAlertsService) {
        super();
    }

    connect(): BehaviorSubject<LoanApplicationResourceModel[]> {
        return this._service.loanApplications;
    }

    disconnect(): void {
    }
}
