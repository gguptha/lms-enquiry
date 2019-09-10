import {Component, OnInit, ViewChild} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { fuseAnimations } from '@fuse/animations';
import {ActivatedRoute, Router} from '@angular/router';
import { EnquiryAlertsService } from '../enquiryAlerts/enquiryAlerts.service';
import { BehaviorSubject } from 'rxjs';
import { EnquiryApplicationModel } from 'app/main/content/model/enquiryApplication.model';
import {MatPaginator} from "@angular/material";

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss'],
    animations: fuseAnimations
})
export class EnquirySearchComponent {

    @ViewChild(MatPaginator ) paginator: MatPaginator;

    enquirySearchForm: FormGroup;

    enquiryList: EnquiryApplicationModel[];

    expandPanel = true;

  loanClasses: Array<any>;
  financingTypes: Array<any>;
  projectTypes: Array<any>;
  assistanceTypes: Array<any>;
  states: Array<string>;

   /**
     *
     * @param _formBuilder
     * @param _service
     * @param _router
     * @param _enquiryAlertsService
     */
    constructor(_route: ActivatedRoute,_formBuilder: FormBuilder,
                 public _service: LoanEnquiryService, private _router: Router,
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


     // Initialize dropdowns.
     this.loanClasses = _route.snapshot.data.routeResolvedData[0]._embedded.loanClasses;
     this.financingTypes = _route.snapshot.data.routeResolvedData[1]._embedded.financingTypes;
     this.projectTypes = _route.snapshot.data.routeResolvedData[2]._embedded.projectTypes;
     this.states = _route.snapshot.data.routeResolvedData[3];
     this.assistanceTypes = _route.snapshot.data.routeResolvedData[4]._embedded.assistanceTypes;

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
            this._enquiryAlertsService.selectedLoanApplicationPartyNumber.next(this._service.selectedLoanApplicationPartyNumber.value);
        }
        else {
            this._enquiryAlertsService.selectedLoanApplicationId = new BehaviorSubject(this._service.selectedLoanApplicationId.value);
            this._enquiryAlertsService.selectedLoanApplicationPartyNumber = new BehaviorSubject(this._service.selectedLoanApplicationPartyNumber.value);

        }
        this._router.navigate(['/enquiryReview']);
    }
}
