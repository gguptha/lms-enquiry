import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { Router} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { MatDialog } from '@angular/material';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from './loanMonitoring.service';
import { LIEUpdateDialogComponent } from './lieUpdate/lieUpdate.component';
import { LIEReportAndFeeUpdateDialogComponent } from './lieReportAndFeeUpdate/lieReportAndFeeUpdate.component';
import { LIEModel } from '../../model/lie.model';
import { LIEReportAndFeeModel } from '../../model/lieReportAndFee.model';
import { LFAModel } from '../../model/lfa.model';
import { LFAUpdateDialogComponent } from './lfaUpdate/lfaUpdate.component';
import { LFAReportAndFeeModel } from '../../model/lfaReportAndFee.model';
import { LFAReportAndFeeUpdateDialogComponent } from './lfaReportAndFeeUpdate/lfaReportAndFeeUpdate.component';
import { TRAUpdateDialogComponent } from './trustRetentionAccount/traUpdate/traUpdate.component';
import { TRAModel } from '../../model/tra.model';
import { TRAStatementUpdateDialogComponent } from './trustRetentionAccount/traStatementUpdate/traStatementUpdate.component';
import { TRAStatementModel } from '../../model/traStatement.model';
import { TandCModel } from '../../model/tandc.model';
import { TandCUpdateDialogComponent } from './termsAndConditions/tandcUpdate/tandcUpdate.component';
import { SecurityComplianceUpdateDialogComponent } from './securityCompliance/securityComplianceUpdate/securityComplianceUpdate.component';
import { SecurityComplianceModel } from '../../model/securityCompliance.model';
import { SiteVisitModel } from '../../model/siteVisit.model';
import { SiteVisitUpdateDialogComponent } from './siteVisit/siteVisitUpdate/siteVisitUpdate.component';

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent {

    loanApplicationId: string;

    selectedLIE: LIEModel;
    selectedLIEReportAndFee: LIEReportAndFeeModel;
    selectedLFA: LFAModel;
    selectedLFAReportAndFee: LFAReportAndFeeModel;
    selectedTRA: TRAModel;
    selectedTRAStatement: TRAStatementModel = new TRAStatementModel({});
    selectedTandC: TandCModel = new TandCModel({});
    selectedSecurityCompliance: SecurityComplianceModel;
    selectedSiteVisit: SiteVisitModel;
    
    lieList: any;
    lieReportAndFeeList: any;
    lfaList: any;
    lfaReportAndFeeList: any;
    traList: any;
    traStatementList: any;
    tandcList: any;
    securityComplianceList: any;
    siteVisitList: any;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _dialogRef 
     */
    constructor(_formBuilder: FormBuilder, public _loanEnquiryService: LoanEnquiryService, private _router: Router, private _dialogRef: MatDialog,
            private _loanMonitoringService: LoanMonitoringService) {
        
        _loanEnquiryService.selectedLoanApplicationId.subscribe(data => {
            this.loanApplicationId = data;
            // getLendersIndependentEngineers
            _loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                this.lieList = data;
            });
            // getLendersFinancialAdvisors
            _loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                this.lfaList = data;
            });
            // getTrustRetentionAccounts
            _loanMonitoringService.getTrustRetentionaccounts(this.loanApplicationId).subscribe(data => {
                this.traList = data;
            });
            // getTermsAndConditions
            _loanMonitoringService.getTermsAndConditions(this.loanApplicationId).subscribe(data => {
                this.tandcList = data;
            })
            // getSecurityCompliances
            _loanMonitoringService.getSecurityCompliances(this.loanApplicationId).subscribe(data => {
                this.securityComplianceList = data;
            })
            // getSiteVisits
            _loanMonitoringService.getSiteVisits(this.loanApplicationId).subscribe(data => {
                this.siteVisitList = data;
            })            
        });
        
        // All about LIE

        this.lieReportAndFeeList = [];

        _loanMonitoringService.selectedLIE.subscribe(data => {
            this.selectedLIE = new LIEModel(data);
            if (this.selectedLIE.id !== '') {
                _loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        })

        _loanMonitoringService.selectedLIEReportAndFee.subscribe(data => {
            this.selectedLIEReportAndFee = new LIEReportAndFeeModel(data);
        });

        // All about LFA

        this.lfaReportAndFeeList = [];

        _loanMonitoringService.selectedLFA.subscribe(data => {
            this.selectedLFA = new LFAModel(data);
            if (this.selectedLFA.id !== '') {
                _loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        })
        
        _loanMonitoringService.selectedLFAReportAndFee.subscribe(data => {
            this.selectedLFAReportAndFee = new LFAReportAndFeeModel(data);
        });

        // All about TRA

        this.traStatementList = [];

        _loanMonitoringService.selectedTRA.subscribe(data => {
            this.selectedTRA = new TRAModel(data);
            if (this.selectedTRA.id !== '') {
                _loanMonitoringService.getTRAStatements(this.selectedTRA.id).subscribe(data => {
                    this.traStatementList = data;
                });
            }
        })

        _loanMonitoringService.selectedTRAStatement.subscribe(data => {
            this.selectedTRAStatement = new TRAStatementModel(data);
        })

        // All about Terms & Conditions

        _loanMonitoringService.selectedTandC.subscribe(data => {
            this.selectedTandC = new TandCModel(data);
        })

        // All about Security Compliance

        _loanMonitoringService.selectedSecurityCompliance.subscribe(data => {
            this.selectedSecurityCompliance = new SecurityComplianceModel(data);
        })

        // All about Site Visit

        _loanMonitoringService.selectedSiteVisit.subscribe(data => {
            this.selectedSiteVisit = new SiteVisitModel(data);
        })    
    }

    /**
     * redirectToMonitorLoan()
     */
    redirectToMonitorLoan(): void {
        this._router.navigate(['/enquiryReview']);
    }

    addLIE(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEUpdateDialogComponent, {
            panelClass: 'fuse-lie-update-dialog',
            width: '750px',
            data: {
                operation: 'addLIE',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                    this.lieList = data;
                });
            }
        });    
    }

    updateLIE(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEUpdateDialogComponent, {
            panelClass: 'fuse-lie-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLIE',
                loanApplicationId: this.loanApplicationId,
                selectedLIE: this.selectedLIE
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersIndependentEngineers(this.loanApplicationId).subscribe(data => {
                    this.lieList = data;
                });
            }
        });    
    }

    addLIEReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lie-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'addLIEReportAndFee',
                selectedLIE: this.selectedLIE
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        });    
    }

    updateLIEReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LIEReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lie-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLIEReportAndFee',
                selectedLIE: this.selectedLIE,
                selectedLIEReportAndFee: this.selectedLIEReportAndFee
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLIEReportsAndFees(this.selectedLIE.id).subscribe(data => {
                    this.lieReportAndFeeList = data;
                });
            }
        });    
    }

    addLFA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAUpdateDialogComponent, {
            panelClass: 'fuse-lfa-update-dialog',
            width: '750px',
            data: {
                operation: 'addLFA',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                    this.lfaList = data;
                });
            }
        });    
    }

    updateLFA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAUpdateDialogComponent, {
            panelClass: 'fuse-lfa-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLFA',
                loanApplicationId: this.loanApplicationId,
                selectedLFA: this.selectedLFA
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLendersFinancialAdvisors(this.loanApplicationId).subscribe(data => {
                    this.lfaList = data;
                });
            }
        });    
    }

    addLFAReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lfa-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'addLFAReportAndFee',
                selectedLFA: this.selectedLFA
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        });    
    }

    updateLFAReportAndFee(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(LFAReportAndFeeUpdateDialogComponent, {
            panelClass: 'fuse-lfa-report-fee-update-dialog',
            width: '750px',
            data: {
                operation: 'updateLFAReportAndFee',
                selectedLFA: this.selectedLFA,
                selectedLFAReportAndFee: this.selectedLFAReportAndFee
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getLFAReportsAndFees(this.selectedLFA.id).subscribe(data => {
                    this.lfaReportAndFeeList = data;
                });
            }
        });    
    }

    addTRA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(TRAUpdateDialogComponent, {
            panelClass: 'fuse-tra-update-dialog',
            width: '750px',
            data: {
                operation: 'addTRA',
                loanApplicationId: this.loanApplicationId
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getTrustRetentionaccounts(this.loanApplicationId).subscribe(data => {
                    this.traList = data;
                });
            }
        });    
    }

    updateTRA(): void {
        // Open the dialog.
        const dialogRef = this._dialogRef.open(TRAUpdateDialogComponent, {
            panelClass: 'fuse-tra-update-dialog',
            width: '750px',
            data: {
                operation: 'updateTRA',
                loanApplicationId: this.loanApplicationId,
                selectedTRA: this.selectedTRA
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getTrustRetentionaccounts(this.loanApplicationId).subscribe(data => {
                    this.traList = data;
                });
            }
        });    
    }

    /**
     * updateTRAStatement()
     * @param operation 
     */
    updateTRAStatement(operation: string): void {
        // Open the dialog.
        var data: any;
        if (operation === 'addTRAStatement') {
            data = {
                'operation': operation,
                'loanApplicationId': this.loanApplicationId,
                'selectedTRA': this.selectedTRA
            }
        }
        else {
            data = {
                'operation': operation,
                'selectedTRA': this.selectedTRA,
                'selectedTRAStatement': this.selectedTRAStatement
            }
        }
        const dialogRef = this._dialogRef.open(TRAStatementUpdateDialogComponent, {
            panelClass: 'fuse-tra-statement-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getTRAStatements(this.selectedTRA.id).subscribe(data => {
                    this.traStatementList = data;
                });
            }
        });    
    }

    /**
     * updateTermsAndConditions()
     * @param operation 
     */
    updateTermsAndConditions(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedTandC': undefined
        };
        if (operation === 'updateT&C') {
            data.selectedTandC = this.selectedTandC;
        }
        const dialogRef = this._dialogRef.open(TandCUpdateDialogComponent, {
            panelClass: 'fuse-tandc-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getTermsAndConditions(this.loanApplicationId).subscribe(data => {
                    this.tandcList = data;
                });
            }
        });    
    }

    /**
     * updateSecurityCompliance()
     * @param operation 
     */
    updateSecurityCompliance(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedSecurityCompliance': undefined
        };
        if (operation === 'updateSecurityCompliance') {
            data.selectedSecurityCompliance = this.selectedSecurityCompliance;
        }
        const dialogRef = this._dialogRef.open(SecurityComplianceUpdateDialogComponent, {
            panelClass: 'fuse-security-compliance-update-dialog',
            width: '1000px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getSecurityCompliances(this.loanApplicationId).subscribe(data => {
                    this.securityComplianceList = data;
                });
            }
        });    
    }

    /**
     * updatSiteVisit()
     * @param operation 
     */
    updateSiteVisit(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectediteVisit': undefined
        };
        if (operation === 'updateSiteVisit') {
            data.selectediteVisit = this.selectedSiteVisit;
        }
        const dialogRef = this._dialogRef.open(SiteVisitUpdateDialogComponent, {
            panelClass: 'fuse-site-visit-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getSiteVisits(this.loanApplicationId).subscribe(data => {
                    this.siteVisitList = data;
                });
            }
        });    
    }
}
