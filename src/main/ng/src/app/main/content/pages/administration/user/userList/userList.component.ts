import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs';
import { fuseAnimations } from '@fuse/animations';

@Component({
    selector: 'fuse-user-list',
    templateUrl: './userList.component.html',
    styleUrls: ['./userList.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class UserListComponent implements OnInit {

    // dataSource: LoanApplicationDataSource;
    
    // selectedEnquiry: LoanApplicationResourceModel;

    displayedColumns = [
        'functionalStatus', 'createdOn', 'enquiryNo', 'bpCode', 'projectName', 'projectLocationState', 'projectType', 
        'loanClass', 'projectCapacity', 'assistanceType', 'projectCost', 'loanAmount'
    ];

    // constructor(private _service: EnquiryAlertsService) {
    //     this.dataSource = new LoanApplicationDataSource(_service);
    // }
    
    ngOnInit(): void {
    }

}

/* export class LoanApplicationDataSource extends MatTableDataSource<LoanApplicationResourceModel> {
    constructor(private _service: EnquiryAlertsService) {
        super();
    }

    connect(): BehaviorSubject<LoanApplicationResourceModel[]> {
        console.log('loanApplications', this._service.loanApplications);
        return this._service.loanApplications;
    }

    disconnect(): void {
    }
}*/

