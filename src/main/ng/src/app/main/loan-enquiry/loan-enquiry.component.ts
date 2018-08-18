import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-loan-enquiry',
    templateUrl: './loan-enquiry.component.html',
    styleUrls: ['./loan-enquiry.component.scss']
})
export class LoanEnquiryComponent implements OnInit {

    loanEnquiryFormStep1: FormGroup;
    loanEnquiryFormStep2: FormGroup;
    loanEnquiryFormStep3: FormGroup;

    loanClasses: Array<any>;
    financingTypes: Array<any>;
    projectTypes: Array<any>;
    assistanceTypes: Array<any>;
    states: Array<string>;

    /**
     * constructor()
     * @param _route 
     * @param _formBuilder 
     */
    constructor(private _route: ActivatedRoute, private _formBuilder: FormBuilder) { 

        // Initialize the forms.
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

        // Initialize dropdowns.
        this.loanClasses = _route.snapshot.data.routeResolvedData[0];
        this.financingTypes = _route.snapshot.data.routeResolvedData[1];
        this.projectTypes = _route.snapshot.data.routeResolvedData[2];
        this.states = _route.snapshot.data.routeResolvedData[3];
        this.assistanceTypes = _route.snapshot.data.routeResolvedData[4];
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }
}
