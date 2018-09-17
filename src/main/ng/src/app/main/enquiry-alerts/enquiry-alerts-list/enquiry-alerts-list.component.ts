import { Component, OnInit } from '@angular/core';
import { fuseAnimations } from '../../../../@fuse/animations';
import { MatTableDataSource } from '@angular/material';
import { LoanApplication } from '../../model/loanapplication.model';
import { EnquiryAlertsService } from '../enquiry-alerts.service';
import { BehaviorSubject } from 'rxjs';

@Component({
    selector: 'fuse-enquiry-alerts-list',
    templateUrl: './enquiry-alerts-list.component.html',
    styleUrls: ['./enquiry-alerts-list.component.scss'],
    animations: fuseAnimations
})
export class EnquiryAlertsListComponent implements OnInit {

    dataSource: LoanApplicationDataSource;

    displayedColumns = [
        'createdOn', 'projectLocationState', 'projectType', 'loanClass', 'projectCapacity', 'assistanceType', 
        'projectCost', 'financingType'
    ];

    constructor(private _service: EnquiryAlertsService) {
        this.dataSource = new LoanApplicationDataSource(_service);
    }
    
    ngOnInit(): void {
    }
}

export class LoanApplicationDataSource extends MatTableDataSource<LoanApplication> {
    constructor(private _service: EnquiryAlertsService) {
        super();
    }

    connect(): BehaviorSubject<LoanApplication[]> {
        return this._service.loanApplications;
    }

    disconnect(): void {
    }
}
