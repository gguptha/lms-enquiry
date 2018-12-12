import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatStepper, DateAdapter, NativeDateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material';
import { EnquiryApplicationRegEx } from '../../../others/enquiryApplication.regEx';
import { MessageDialogComponent } from '../../../components/messageDialog/messageDialog.component';
import { FuseNavigationService } from '@fuse/components/navigation/navigation.service';
import { LoanEnquiryService } from '../enquiryApplication.service';
import { PartnerModel } from 'app/main/content/model/partner.model';

@Component({
    selector: 'fuse-enquiry-application-component',
    templateUrl: './enquiryApplication.component.html',
    styleUrls: ['./enquiryApplication.component.scss'],
    encapsulation: ViewEncapsulation.None
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

    minDate = new Date();

    /**
     * constructor()
     * @param _route 
     * @param _formBuilder 
     */
    constructor(_route: ActivatedRoute, private _formBuilder: FormBuilder, private _dialogRef: MatDialog,
        private _loanEnquiryService: LoanEnquiryService, private _router: Router, private _navigationService: FuseNavigationService) {

        const  partner = new PartnerModel(_route.snapshot.data.routeResolvedData[5]);

        // Set min value of scheduled cod to tomorrow's date.
        this.minDate.setDate(this.minDate.getDate() + 1);

        // Initialize the forms.
        this.loanEnquiryFormStep1 = this._formBuilder.group({
            projectName: [''],
            loanClass: [''],
            financingType: [''],
            projectType: [''],
            projectCapacity: ['', [Validators.pattern(EnquiryApplicationRegEx.projectCapacity), Validators.min(1), Validators.max(9999.99)]],
            assistanceType: [''],
            tenorYear: ['', [Validators.pattern(EnquiryApplicationRegEx.tenorYear)]],
            tenorMonth: ['', [Validators.max(11), Validators.pattern(EnquiryApplicationRegEx.tenorMonth)]],
            projectLocationState: [''],
            projectDistrict: [''],
            projectCost: ['', [Validators.pattern(EnquiryApplicationRegEx.projectCost)]],
            equity: ['', [Validators.pattern(EnquiryApplicationRegEx.equity), Validators.max(99)]],
            projectDebtAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.projectDebtAmount)]],
            pfsDebtAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.pfsDebtAmount)]],
            expectedSubDebt: ['', [Validators.pattern(EnquiryApplicationRegEx.expectedSubDebt)]],
            pfsSubDebtAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.pfsSubDebtAmount)]],
            loanPurpose: [''],
            leadFIName: [''],
            leadFILoanAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.leadFILoanAmount)]],
            expectedInterestRate: ['', [Validators.pattern(EnquiryApplicationRegEx.expectedInterestRate)]],
            scheduledCOD: ['']
        });

        this.loanEnquiryFormStep2 = this._formBuilder.group({
            partyName1: [partner.partyName1],
            partyName2: [partner.partyName2],
            contactPersonName: [partner.contactPersonName],
            addressLine1: [partner.addressLine1 || ''],
            addressLine2: [partner.addressLine2],
            street: [partner.street],
            city: [partner.city],
            state: [partner.state],
            postalCode: [partner.postalCode],
            email: [_navigationService.currentUser.email],
            contactNumber: [partner.contactNumber],
            pan: [partner.pan],
            groupCompany: [partner.groupCompany]
        });

        this.loanEnquiryFormStep3 = this._formBuilder.group({
            promoterName: [''],
            promoterAreaOfBusinessNature: [''],
            promoterNetWorthAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.borrowerNetWorth)]],
            promoterPATAmount: ['', [Validators.pattern(EnquiryApplicationRegEx.borrowerPAT)]],
            rating: ['', [Validators.max(100)]],
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

        // To solve the utc time zone issue
        const scheduledCOD = new Date(loanApplication.scheduledCOD);
        loanApplication.scheduledCOD = new Date(Date.UTC(scheduledCOD.getFullYear(), scheduledCOD.getMonth(), scheduledCOD.getDate()));

        // Save the loan application to the database.
        this._loanEnquiryService.saveLoanApplication(loanApplication, partner).subscribe((response) => {
            // Display alert message and redirect to enquiry alerts page.
            const dialogRef = this._dialogRef.open(MessageDialogComponent, {
                width: '400px',
                data: {
                    message: 'Your Loan Enquiry ' + response.enquiryNo.id + ' is submitted to PFS Loan Officer.'
                }
            });
            dialogRef.afterClosed().subscribe((dresponse) => {
                this._router.navigate(['/enquiryAlerts']);
            });
        });
    }
}
