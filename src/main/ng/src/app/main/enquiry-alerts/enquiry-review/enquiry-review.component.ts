import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatStepper } from '@angular/material';
import { EnquiryAlertsService } from '../enquiry-alerts.service';
import { LoanApplication } from '../../model/loanapplication.model';
import { Partner } from '../../model/partner.model';
import { Location } from '@angular/common';

@Component({
    selector: 'fuse-enquiry-review',
    templateUrl: './enquiry-review.component.html',
    styleUrls: ['./enquiry-review.component.scss']
})
export class EnquiryReviewComponent implements OnInit {

    loanEnquiryFormStep1: FormGroup;
    loanEnquiryFormStep2: FormGroup;
    loanEnquiryFormStep3: FormGroup;
    loanEnquiryFormStep4: FormGroup;

    loanClasses: Array<any>;
    financingTypes: Array<any>;
    projectTypes: Array<any>;
    assistanceTypes: Array<any>;
    states: Array<string>;

    loanApplication: LoanApplication;
    partner: Partner;

    /**
     * constructor()
     * @param _route 
     * @param _formBuilder 
     */
    constructor(private _route: ActivatedRoute, private _formBuilder: FormBuilder, private _dialogRef: MatDialog,
        private _loanEnquiryService: EnquiryAlertsService, private _location: Location) {

        // Initialize loanApplication.
        this.loanApplication = _route.snapshot.data.routeResolvedData[5];
        // Initialoze partner.
        this.partner = new Partner({});
        this._loanEnquiryService.getPartner(this.loanApplication.loanApplicant).subscribe((result) => {
            this.partner = result;
            this.initializePartnerForm(); // Refresh the partner form with data.
        });

        // Initialize the forms.
        this.initializeLoanApplicationForms();
        this.initializePartnerForm();

        // Initialize dropdowns.
        this.loanClasses = _route.snapshot.data.routeResolvedData[0]._embedded.loanClasses;
        this.financingTypes = _route.snapshot.data.routeResolvedData[1]._embedded.financingTypes;
        this.projectTypes = _route.snapshot.data.routeResolvedData[2]._embedded.projectTypes;
        this.states = _route.snapshot.data.routeResolvedData[3];
        this.assistanceTypes = _route.snapshot.data.routeResolvedData[4]._embedded.assistanceTypes;
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    // Initialize the forms loanEnquiryFormStep1, loanEnquiryFormStep3 & loanEnquiryFormStep4.
    initializeLoanApplicationForms(): void {

        this.loanEnquiryFormStep1 = this._formBuilder.group({
            loanClass: [this.loanApplication.loanClassDescription],
            financingType: [this.loanApplication.financingType],
            projectType: [this.loanApplication.projectType],
            projectCapacity: [this.loanApplication.projectCapacity],
            assistanceType: [this.loanApplication.assistanceType],
            tenorYear: [this.loanApplication.tenorYear],
            tenorMonth: [this.loanApplication.tenorMonth],
            projectLocationState: [this.loanApplication.projectLocationState],
            projectDistrict: [''],
            projectCost: [this.loanApplication.projectCost],
            equity: [''],
            projectDebtAmount: [''],
            pfsDebtAmount: [this.loanApplication.pfsDebtAmount],
            expectedSubDebt: [''],
            pfsSubDebtAmount: [''],
            loanPurpose: [this.loanApplication.loanPurpose],
            leadFIName: [''],
            leadFILoanAmount: [''],
            expectedInterestRate: [''],
            scheduledCOD: ['']
        });

        this.loanEnquiryFormStep3 = this._formBuilder.group({
            promoterName: [''],
            promoterAreaofBusinessNature: [''],
            promoterNetWorthAmount: [''],
            promoterPATAmount: [''],
            rating: [''],
            promoterKeyDirector: ['']
        });

        this.loanEnquiryFormStep4 = this._formBuilder.group({
            bpCode: [''],
            bpGroupCompany: [''],
            bpIndustry: [''],
            category: [''],
            rejectionReason: [''],
            rejectionDate: ['']
        });
    }

    // Initialize the form loanEnquiryFormStep1.
    initializePartnerForm(): void {

        this.loanEnquiryFormStep2 = this._formBuilder.group({
            partyName1: [this.partner.partyName1],
            partyName2: [this.partner.partyName2],
            contactPersonName: [this.partner.contactPersonName],
            addressLine1: [this.partner.addressLine1],
            addressLine2: [this.partner.addressLine2],
            street: [this.partner.street],
            city: [this.partner.city],
            state: [this.partner.state],
            postalCode: [this.partner.postalCode],
            email: [this.partner.email],
            contactNumber: [this.partner.contactNumber],
            pan: [this.partner.pan],
            groupCompany: [this.partner.groupCompany]
        });
    }

    backButtonClick(): void {
        this._location.back();
    }

    /**
     * saveLoanApplication()
     * This method is invoked when the user clicks on Finish on the last step of the loan application form.
     */
    saveLoanApplication(stepper: MatStepper): void {
        /* const dialogRef = this._dialogRef.open(FuseConfirmDialogComponent, {
            width: '400px',
            data: {
                confirmMessage: 'Provide your confirmation to save the loan application.',
            }
        });
        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                console.log('saving loan application');
            }
        }); */

        // Re-construct the partner object.
        const partner = this.loanEnquiryFormStep2.value;
        partner._links = this.partner._links;

        // Re-construct the loan application object.
        const loanApplication = this.loanEnquiryFormStep1.value;
        const promoter = this.loanEnquiryFormStep3.value;
        loanApplication.promoterName = promoter.promoterName;
        loanApplication.promoterAreaofBusinessNature = promoter.promoterAreaofBusinessNature;
        loanApplication.promoterNetWorthAmount = promoter.promoterNetWorthAmount;
        loanApplication.promoterPATAmount = promoter.promoterPATAmount;
        loanApplication.rating = promoter.rating;
        loanApplication.promoterKeyDirector = promoter.promoterKeyDirector;
        loanApplication._links = this.loanApplication._links;

        console.log(loanApplication, partner);

        this._location.back();
    }
}
