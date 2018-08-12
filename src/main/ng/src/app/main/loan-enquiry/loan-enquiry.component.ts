import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-loan-enquiry',
    templateUrl: './loan-enquiry.component.html',
    styleUrls: ['./loan-enquiry.component.scss']
})
export class LoanEnquiryComponent implements OnInit {

    loanEnquiryFormStep1: FormGroup;

    constructor(private _formBuilder: FormBuilder) { 
        this.loanEnquiryFormStep1 = this._formBuilder.group({
            loanClass: [''],
            financingType: ['']
        });
    }

    ngOnInit(): void {
    }

}
