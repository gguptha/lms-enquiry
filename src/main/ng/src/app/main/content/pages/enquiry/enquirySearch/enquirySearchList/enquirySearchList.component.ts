import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { ProjectTypeModel } from '../../../../model/projectType.model';
import { LoanClassModel } from '../../../../model/loanClass.model';
import { AssistanceTypeModel } from '../../../../model/assistanceType.model';

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

    /**
     * constructor()
     */
    constructor() {
    }
    
    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    /**
     * getProjectTypeDescription()
     * @param projectType 
     */
    getProjectTypeDescription(projectType: number): string {
        return ProjectTypeModel.getProjectTypeDescription(projectType);
    }

    /**
     * getLoanClassDescription()
     * @param loanClass 
     */
    getLoanClassDescription(loanClass: number): string {
        return LoanClassModel.getLoanClassDescription(loanClass);
    }

    /**
     * getAssistanceTypeDescription()
     * @param assistanceType 
     */
    getAssistanceTypeDescription(assistanceType: string): string {
        return AssistanceTypeModel.getAssistanceTypeDescription(assistanceType);
    }
}
