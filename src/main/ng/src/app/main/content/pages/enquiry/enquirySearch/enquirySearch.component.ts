import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { LoanApplicationModel } from '../../../model/loanApplication.model';

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss']
})
export class EnquirySearchComponent implements OnInit {

    enquirySearchForm: FormGroup;

    enquiryList: any;

    constructor(_formBuilder: FormBuilder, private _service: LoanEnquiryService) {

        this.enquirySearchForm = _formBuilder.group({
            enquiryNumberFrom: [''],
            enquiryNumberTo: ['']
        });
    }

    ngOnInit(): void {
    }

    searchEnquiries(): void {
        const request = this.enquirySearchForm.value;
        this._service.searchLoanEnquiries(request).subscribe((result) => {
            this.enquiryList = result;
        });
    }
}
