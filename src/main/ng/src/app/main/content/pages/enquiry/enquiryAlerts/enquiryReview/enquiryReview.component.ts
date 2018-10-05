import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatStepper } from '@angular/material';
import { Location } from '@angular/common';
import { fuseAnimations } from '@fuse/animations';
import { EnquiryAlertsService } from '../enquiryAlerts.service';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { PartnerModel } from '../../../../model/partner.model';
import { EnquiryRejectDialogComponent } from '../enquiryReject/enquiryReject.component';

@Component({
    selector: 'fuse-enquiry-review',
    templateUrl: './enquiryReview.component.html',
    styleUrls: ['./enquiryReview.component.scss'],
    animations: fuseAnimations
})
export class EnquiryReviewComponent implements OnInit {

    loanEnquiryFormStep1: FormGroup;
    loanEnquiryFormStep2: FormGroup;
    loanEnquiryFormStep3: FormGroup;
    // loanEnquiryFormStep4: FormGroup;

    loanClasses: Array<any>;
    financingTypes: Array<any>;
    projectTypes: Array<any>;
    assistanceTypes: Array<any>;
    states: Array<string>;

    loanApplication: LoanApplicationModel;
    partner: PartnerModel;

    /**
     * constructor()
     * @param _route 
     * @param _formBuilder 
     */
    constructor(_route: ActivatedRoute, private _formBuilder: FormBuilder, private _dialogRef: MatDialog,
        private _enquiryAlertsService: EnquiryAlertsService, private _location: Location) {

        // Initialize loanApplication.
        this.loanApplication = _route.snapshot.data.routeResolvedData[5];
        // Initialize partner.
        this.partner = new PartnerModel({});
        this._enquiryAlertsService.getPartner(this.loanApplication.loanApplicant).subscribe((result) => {
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

    // Initialize the forms loanEnquiryFormStep1, loanEnquiryFormStep3.
    initializeLoanApplicationForms(): void {

        this.loanEnquiryFormStep1 = this._formBuilder.group({
            loanClass: [this.loanApplication.loanClass || ''],
            financingType: [this.loanApplication.financingType],
            projectType: [this.loanApplication.projectType],
            projectCapacity: [this.loanApplication.projectCapacity],
            assistanceType: [this.loanApplication.assistanceType],
            tenorYear: [this.loanApplication.tenorYear],
            tenorMonth: [this.loanApplication.tenorMonth],
            projectLocationState: [this.loanApplication.projectLocationState],
            projectDistrict: [this.loanApplication.projectDistrict],
            projectCost: [this.loanApplication.projectCost],
            equity: [this.loanApplication.equity],
            projectDebtAmount: [this.loanApplication.projectDebtAmount],
            pfsDebtAmount: [this.loanApplication.pfsDebtAmount],
            expectedSubDebt: [this.loanApplication.expectedSubDebt],
            pfsSubDebtAmount: [this.loanApplication.pfsSubDebtAmount],
            loanPurpose: [this.loanApplication.loanPurpose],
            leadFIName: [this.loanApplication.leadFIName],
            leadFILoanAmount: [this.loanApplication.leadFILoanAmount],
            expectedInterestRate: [this.loanApplication.expectedInterestRate],
            scheduledCOD: ['']
        });

        this.loanEnquiryFormStep3 = this._formBuilder.group({
            promoterName: [this.loanApplication.promoterName],
            promoterAreaOfBusinessNature: [this.loanApplication.promoterAreaOfBusinessNature],
            promoterNetWorthAmount: [this.loanApplication.promoterNetWorthAmount],
            promoterPATAmount: [this.loanApplication.promoterPATAmount],
            rating: [this.loanApplication.rating],
            promoterKeyDirector: [this.loanApplication.promoterKeyDirector]
        });

        // this.loanEnquiryFormStep4 = this._formBuilder.group({
        //     bpCode: [''],
        //     bpGroupCompany: [''],
        //     bpIndustry: [''],
        //     category: [''],
        //     rejectionReason: [''],
        //     rejectionDate: ['']
        // });
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
        this.reconstructPartner();

        // Re-construct the loan application object.
        this.reconstructLoanApplication();

        // Update the loan application and redirect back to the enquiry alerts list.
        this._enquiryAlertsService.updateLoanApplication(this.loanApplication, this.partner).subscribe(() => {
            this._location.back();
        });
    }

    reconstructLoanApplication(): void {
        // Reconstruct loanApplication with loanEnquiryFormStep1 values.
        const loanApplication = this.loanEnquiryFormStep1.value;
        this.loanApplication.loanClass = loanApplication.loanClass;
        this.loanApplication.financingType = loanApplication.financingType;
        this.loanApplication.projectType = loanApplication.projectType;
        this.loanApplication.projectCapacity = loanApplication.projectCapacity;
        this.loanApplication.assistanceType = loanApplication.assistanceType;
        this.loanApplication.tenorYear = loanApplication.tenorYear;
        this.loanApplication.tenorMonth = loanApplication.tenorMonth;        
        this.loanApplication.projectLocationState = loanApplication.projectLocationState;
        this.loanApplication.projectDistrict = loanApplication.projectDistrict;
        this.loanApplication.projectCost = loanApplication.projectCost;
        this.loanApplication.equity = loanApplication.equity;
        this.loanApplication.projectDebtAmount = loanApplication.projectDebtAmount;
        this.loanApplication.pfsDebtAmount = loanApplication.pfsDebtAmount;
        this.loanApplication.expectedSubDebt = loanApplication.expectedSubDebt;
        this.loanApplication.pfsSubDebtAmount = loanApplication.pfsDebtAmount;
        this.loanApplication.loanPurpose = loanApplication.loanPurpose;
        this.loanApplication.leadFIName = loanApplication.leadFIName;
        this.loanApplication.leadFILoanAmount = loanApplication.leadFILoanAmount;
        this.loanApplication.expectedInterestRate = loanApplication.expectedInterestRate;
        
        // Reconstruct loanApplication with loanEnquiryFormStep3 values.
        const promoter = this.loanEnquiryFormStep3.value;
        this.loanApplication.promoterName = promoter.promoterName;
        this.loanApplication.promoterAreaOfBusinessNature = promoter.promoterAreaofBusinessNature;
        this.loanApplication.promoterNetWorthAmount = promoter.promoterNetWorthAmount;
        this.loanApplication.promoterPATAmount = promoter.promoterPATAmount;
        this.loanApplication.rating = promoter.rating;
        this.loanApplication.promoterKeyDirector = promoter.promoterKeyDirector;
    }

    reconstructPartner(): void {
        const partner = this.loanEnquiryFormStep2.value;

        this.partner.addressLine1 = partner.addressLine1;
        this.partner.addressLine2 = partner.addressLine2;
        this.partner.city = partner.city;
        this.partner.contactNumber = partner.contactNumber;
        this.partner.contactPersonName = partner.contactPersonName;
        this.partner.email = partner.email;
        this.partner.groupCompany = partner.groupCompany;
        this.partner.pan = partner.pan;
        this.partner.partyName1 = partner.partyName1;
        this.partner.partyName2 = partner.partyName2;
        this.partner.postalCode = partner.postalCode;
        this.partner.state = partner.state;
        this.partner.street = partner.street;
    }

    rejectEnquiry(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(EnquiryRejectDialogComponent, {
            panelClass: 'fuse-enquiry-reject-dialog',
            width: '600px',
            data: {
                loanApplication: this.loanApplication
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => {
            if (result.action !== 'Cancel') {
                this._location.back();
            }
        });
    }
}