import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { ProjectTypeModel } from '../../../../model/projectType.model';
import { LoanClassModel } from '../../../../model/loanClass.model';
import { AssistanceTypeModel } from '../../../../model/assistanceType.model';
import { FunctionalStatusModel } from '../../../../model/functionalStatus.model';

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
        'functionalStatus', 'createdOn', 'enquiryNo', 'bpCode', 'projectName', 'projectLocationState', 'projectType', 
        'loanClass', 'projectCapacity', 'assistanceType', 'projectCost', 'loanAmount'
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
    getProjectTypeDescription(projectType: string): string {
        return ProjectTypeModel.getProjectTypeDescription(projectType);
    }

    /**
     * getLoanClassDescription()
     * @param loanClass 
     */
    getLoanClassDescription(loanClass: string): string {
        return LoanClassModel.getLoanClassDescription(loanClass);
    }

    /**
     * getAssistanceTypeDescription()
     * @param assistanceType 
     */
    getAssistanceTypeDescription(assistanceType: string): string {
        return AssistanceTypeModel.getAssistanceTypeDescription(assistanceType);
    }

    getFunctionalStatus(functionalStatus: number): string {
        return FunctionalStatusModel.getFunctionalStatus(functionalStatus);
    }
}
