import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { EnquiryAlertsService } from '../enquiryAlerts.service';

@Component({
    selector: 'fuse-enquiry-alerts-list',
    templateUrl: './enquiryAlertsList.component.html',
    styleUrls: ['./enquiryAlertsList.component.scss'],
    animations: fuseAnimations
})
export class EnquiryAlertsListComponent implements OnInit {

    dataSource: LoanApplicationDataSource;
    
    selectedEnquiry: LoanApplicationModel;

    displayedColumns = [
        'createdOn', 'enquiryNo', 'bpCode', 'projectName', 'projectLocationState', 'projectType', 'loanClass', 'projectCapacity', 
        'assistanceType', 'projectCost', 'loanAmount'
    ];

    constructor(private _service: EnquiryAlertsService) {
        this.dataSource = new LoanApplicationDataSource(_service);
    }
    
    ngOnInit(): void {
    }

    onSelect(enquiry: LoanApplicationModel): void {
        this.selectedEnquiry = enquiry;
        this._service.selectedLoanApplicationId = new BehaviorSubject(enquiry.id);
    }
}

export class LoanApplicationDataSource extends MatTableDataSource<LoanApplicationModel> {
    constructor(private _service: EnquiryAlertsService) {
        super();
    }

    connect(): BehaviorSubject<LoanApplicationModel[]> {
        console.log('connect', this._service.loanApplications);
        return this._service.loanApplications;
    }

    disconnect(): void {
    }
}
