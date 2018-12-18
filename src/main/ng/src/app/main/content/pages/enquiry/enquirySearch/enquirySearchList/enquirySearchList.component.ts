import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { ProjectTypeModel } from '../../../../model/projectType.model';
import { LoanClassModel } from '../../../../model/loanClass.model';
import { AssistanceTypeModel } from '../../../../model/assistanceType.model';
import { FunctionalStatusModel } from '../../../../model/functionalStatus.model';
import { LoanApplicationResourceModel } from 'app/main/content/model/loanApplicationResource.model';
import { LoanEnquiryService } from '../../enquiryApplication.service';
import { BehaviorSubject } from 'rxjs';

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
        this.dataSource = new MatTableDataSource(enquiryList);
    }

    displayedColumns = [
        'functionalStatus', 'createdOn', 'enquiryNo', 'loanContractId', 'busPartnerNumber', 'projectName', 'projectLocationState', 'projectType', 
        'loanClass', 'projectCapacity', 'assistanceType', 'projectCost', 'loanAmount'
    ];

    selectedEnquiry: LoanApplicationResourceModel;

    /**
     * constructor()
     */
    constructor(private _service: LoanEnquiryService) {
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

    /**
     * 
     * @param functionalStatus 
     */
    getFunctionalStatus(functionalStatus: number): string {
        return FunctionalStatusModel.getFunctionalStatus(functionalStatus);
    }

    /**
     * 
     * @param enquiry 
     */
    onSelect(enquiry: LoanApplicationResourceModel): void {
        this.selectedEnquiry = enquiry;
        this._service.selectedLoanApplicationId = new BehaviorSubject(enquiry.loanApplication.id);
    }
}
