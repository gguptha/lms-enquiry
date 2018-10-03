import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MatDialog, MatStepper } from '@angular/material';
import { LoanEnquiryService } from './enquiryApplication.service';

@Component({
    selector: 'fuse-enquiry-application-component',
    templateUrl: './enquiryApplication.component.html',
    styleUrls: ['./enquiryApplication.component.scss']
})
export class EnquiryApplicationComponent implements OnInit {

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
    constructor(_route: ActivatedRoute, private _formBuilder: FormBuilder, private _dialogRef: MatDialog,
        private _loanEnquiryService: LoanEnquiryService) {

        // Initialize the forms.
        this.loanEnquiryFormStep1 = this._formBuilder.group({
            loanClass: ['', [Validators.required]],
            financingType: ['', [Validators.required]],
            projectType: ['', [Validators.required]],
            projectCapacity: ['', [Validators.pattern(/^\d{1,4}(\.\d{2,2})?$/)]],
            assistanceType: [''],
            tenorYear: [5, [Validators.required, Validators.min(1), Validators.max(99), Validators.pattern(/^\d{1,2}?$/)]],
            tenorMonth: [6, [Validators.required, Validators.min(1), Validators.max(11), Validators.pattern(/^\d{1,2}?$/)]],
            projectLocationState: [''],
            projectDistrict: [''],
            projectCost: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            equity: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            projectDebtAmount: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            pfsDebtAmount: [5, [Validators.required, Validators.min(1), Validators.max(99999999.99), Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            expectedSubDebt: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            pfsSubDebtAmount: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            loanPurpose: ['Loan required for the purpose of Road Constructions'],
            leadFIName: [''],
            leadFILoanAmount: ['', [Validators.pattern(/^\d{1,8}(\.\d{2,2})?$/)]],
            expectedInterestRate: ['', [Validators.pattern(/^\d{1,2}(\.\d{1,2})?$/)]],
            scheduledCOD: ['']
        });

        this.loanEnquiryFormStep2 = this._formBuilder.group({
            partyName1: ['Dotline Solutions'],
            partyName2: [''],
            contactPersonName: ['B R Gopinath'],
            addressLine1: ['Address1'],
            addressLine2: [''],
            street: ['Koldingweg'],
            city: ['Bangalore'],
            state: [''],
            postalCode: ['560085'],
            email: ['gopinath.br@gmail.com'],
            contactNumber: ['9886496849'],
            pan: ['ABNPG9212E'],
            groupCompany: ['']
        });

        this.loanEnquiryFormStep3 = this._formBuilder.group({
            promoterName: [''],
            promoterAreaOfBusinessNature: [''],
            promoterNetWorthAmount: [''],
            promoterPATAmount: [''],
            rating: [''],
            promoterKeyDirector: ['']
        });

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

        // Re-construct the loan application object.
        const loanApplication = this.loanEnquiryFormStep1.value;
        const promoter = this.loanEnquiryFormStep3.value;
        loanApplication.promoterName = promoter.promoterName;
        loanApplication.promoterAreaOfBusinessNature = promoter.promoterAreaOfBusinessNature;
        loanApplication.promoterNetWorthAmount = promoter.promoterNetWorthAmount;
        loanApplication.promoterPATAmount = promoter.promoterPATAmount;
        loanApplication.rating = promoter.rating;
        loanApplication.promoterKeyDirector = promoter.promoterKeyDirector;

        // Save the loan application to the database.
        this._loanEnquiryService.saveLoanApplication(loanApplication, partner).subscribe(() => {
            // Reset the stepper and the form data.
            stepper.reset();
        });
    }
}
