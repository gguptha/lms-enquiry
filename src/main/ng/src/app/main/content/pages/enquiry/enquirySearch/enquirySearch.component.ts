import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoanEnquiryService } from '../enquiryApplication.service';

@Component({
    selector: 'fuse-enquiry-search',
    templateUrl: './enquirySearch.component.html',
    styleUrls: ['./enquirySearch.component.scss']
})
export class EnquirySearchComponent implements OnInit {

    enquirySearchForm: FormGroup;

    enquiryList: any;

    expandPanel = true;

    constructor(_formBuilder: FormBuilder, private _service: LoanEnquiryService) {

        this.enquirySearchForm = _formBuilder.group({
            enquiryNoFrom: [],
            enquiryNoTo: []
        });
    }

    ngOnInit(): void {
    }

    searchEnquiries(): void {
        this._service.searchLoanEnquiries(this.enquirySearchForm.value).subscribe((result) => {
            this.enquiryList = result;
            this.expandPanel = false;
        });
    }
}
