import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-loan-enquiry',
    templateUrl: './loan-enquiry.component.html',
    styleUrls: ['./loan-enquiry.component.scss']
})
export class LoanEnquiryComponent implements OnInit {

    loanEnquiryFormStep1: FormGroup;
    loanEnquiryFormStep2: FormGroup;
    loanEnquiryFormStep3: FormGroup;

    /**
     * constructor()
     * @param _formBuilder 
     */
    constructor(private _formBuilder: FormBuilder) { 

        this.loanEnquiryFormStep1 = this._formBuilder.group({
            loanClass: [''],
            financingType: [''],
            projectType: [''],
            projectCapacity: [''],
            assistanceType: [''],
            tenorYear: [''],
            tenorMonth: [''],
            projectLocationState: [''],
            projectDistrict: [''],
            projectCost: [''],
            equity: [''],
            projectDebtAmount: [''],
            pfsDebtAmount: [''],
            expectedSubDebt: [''],
            pfsSubDebtAmount: [''],
            loanPurpose: [''],
            leadFIName: [''],
            leadFILoanAmount: [''],
            expectedInterestRate: [''],
            scheduledCOD: ['']
        });

        this.loanEnquiryFormStep2 = this._formBuilder.group({
            partyName1: [''],
            partyName2: [''],
            contactPersonName: [''],
            addressLine1: [''],
            addressLine2: [''],
            street: [''],
            city: [''],
            state: [''],
            postalCode: [''],
            email: [''],
            contactNumber: [''],
            pan: [''],
            groupCompany: ['']
        });

        this.loanEnquiryFormStep3 = this._formBuilder.group({
            promoterName: [''],
            promoterAreaofBusinessNature: [''],
            promoterNetWorthAmount: [''],
            promoterPATAmount: [''],
            rating: [''],
            promoterKeyDirector: ['']
        });
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }
}
