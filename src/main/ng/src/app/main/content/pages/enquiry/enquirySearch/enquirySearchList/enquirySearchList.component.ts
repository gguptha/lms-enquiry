import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';

@Component({
    selector: 'fuse-enquiry-search-list',
    templateUrl: './enquirySearchList.component.html',
    styleUrls: ['./enquirySearchList.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    
    @Input()
    set enquiryList(enquiryList: any) {
        console.log(enquiryList);
        this.dataSource = new MatTableDataSource(enquiryList);
    }

    displayedColumns = [
        'createdOn', 'enquiryNo', 'bpCode', 'projectName', 'projectLocationState', 'projectType', 'loanClass', 'projectCapacity', 
        'assistanceType', 'projectCost'
    ];

    constructor() {
    }
    
    ngOnInit(): void {
    }
}
