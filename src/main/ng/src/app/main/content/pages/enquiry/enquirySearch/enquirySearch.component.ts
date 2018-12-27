import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { fuseAnimations } from '@fuse/animations';
import { Router } from '@angular/router';
import { EnquiryAlertsService } from '../enquiryAlerts/enquiryAlerts.service';
import { BehaviorSubject } from 'rxjs';
import { EnquiryApplicationModel } from 'app/main/content/model/enquiryApplication.model';

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchComponent {

    enquirySearchForm: FormGroup;

    enquiryList: EnquiryApplicationModel[];

    expandPanel = true;

    /**
     * 
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _enquiryAlertsService 
     */
    constructor(_formBuilder: FormBuilder, public _service: LoanEnquiryService, private _router: Router,
        private _enquiryAlertsService: EnquiryAlertsService) {

        this.enquirySearchForm = _formBuilder.group({
            enquiryNoFrom: [],
            enquiryNoTo: [],
            enquiryDateFrom: [],
            enquiryDateTo: [],
            projectName: [],
            projectLocation: [],
            loanClass: [],
            projectType: [],
            financingType: [],
            assistanceType: []
        });

        _service.selectedLoanApplicationId = undefined;
    }

    /**
     * searchEnquiries()
     */
    searchEnquiries(): void {
        this._service.searchLoanEnquiries(this.enquirySearchForm.value).subscribe((result) => {
            const enquiryApplications = new Array<EnquiryApplicationModel>();
            result.map(loanApplicationResourceModel => {
                enquiryApplications.push(new EnquiryApplicationModel(loanApplicationResourceModel));
            });
            this.enquiryList = enquiryApplications;
            this.expandPanel = false;
        });
    }

    /**
     * 
     */
    redirectToEnquiryReview(): void {
        if (this._enquiryAlertsService.selectedLoanApplicationId !== undefined) {
            this._enquiryAlertsService.selectedLoanApplicationId.next(this._service.selectedLoanApplicationId.value);
        }
        else {
            this._enquiryAlertsService.selectedLoanApplicationId = new BehaviorSubject(this._service.selectedLoanApplicationId.value);
        }
        this._router.navigate(['/enquiryReview']);
    }
}
