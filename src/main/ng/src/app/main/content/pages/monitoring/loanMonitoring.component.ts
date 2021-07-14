import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { fuseAnimations } from '@fuse/animations';
import { ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs';
import { MatDialog, MatSnackBar } from '@angular/material';
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
import { SecurityComplianceUpdateDialogComponent } from './securityCompliance/securityComplianceUpdate/securityComplianceUpdate.component';
import { SecurityComplianceModel } from '../../model/securityCompliance.model';
import { RateOfInterestUpdateDialogComponent } from './rateOfInterest/rateOfInterestUpdate/rateOfInterestUpdate.component';
import { RateOfInterestModel } from '../../model/rateOfInterest.model';
import { BorrowerFinancialsModel } from '../../model/borrowerFinancials.model';
import { BorrowerFinancialsUpdateDialogComponent } from './borrowerFinancials/borrowerFinancialsUpdate/borrowerFinancialsUpdate.component';
import { PromoterFinancialsUpdateDialogComponent } from './promoterFinancials/promoterFinancialsUpdate/promoterFinancialsUpdate.component';
import { PromoterFinancialsModel } from '../../model/promoterFinancials.model';
import { FinancialCovenantsModel } from '../../model/financialCovenants.model';
import { FinancialCovenantsUpdateDialogComponent } from './financialCovenants/financialCovenantsUpdate/financialCovenantsUpdate.component';
import { PromoterDetailsModel } from '../../model/promoterDetails.model';
import { OperatingParameterModel } from '../../model/operatingParameter';
import { OperatingParameterUpdateDialogComponent } from './operatingParameter/operatingParameterUpdate/operatingParameterUpdate.component';
import { AppService } from 'app/app.service';
import { OperatingParameterPLFUpdateDialogComponent } from './operatingParameterPLF/operatingParameterPLFUpdate/operatingParameterPLFUpdate.component';
import { OperatingParameterPLFModel } from '../../model/operatingParameterPLF';

@Component({
    selector: 'fuse-loanmonitoring',
    templateUrl: './loanMonitoring.component.html',
    styleUrls: ['./loanMonitoring.component.scss'],
    animations: fuseAnimations
})
export class LoanMonitoringComponent implements OnInit, OnDestroy {

    disableSendForApproval: boolean = false;

    loanMonitor: any;
    loanContractExtension: any = {};

    loanApplicationId: string;
    selectedEnquiry: any;

    selectedLIE: LIEModel;
    selectedLIEReportAndFee: LIEReportAndFeeModel;
    selectedLFA: LFAModel;
    selectedLFAReportAndFee: LFAReportAndFeeModel;
    selectedTRA: TRAModel;
    selectedTRAStatement: TRAStatementModel = new TRAStatementModel({});
    selectedSecurityCompliance: SecurityComplianceModel;
    selectedRateOfInterest: RateOfInterestModel;
    selectedBorrowerFinancials: BorrowerFinancialsModel;
    selectedPromoterFinancials: PromoterFinancialsModel;
    selectedFinancialCovenants: FinancialCovenantsModel;
    selectedPromoterDetails: PromoterDetailsModel;
    selectedOperatingParameter: OperatingParameterModel;
    selectedOperatingParameterPLF: OperatingParameterPLFModel;
    selectedProjectMonitoringData: any;

    lieList: any;
    lieReportAndFeeList: any;
    lfaList: any;
    lfaReportAndFeeList: any;
    traList: any;
    traStatementList: any;
    securityComplianceList: any;
    rateOfInterestList: any;
    borrowerFinancialsList: any;
    promoterFinancialsList: any;
    financialCovenantsList: any;
    operatingParameterList: any;
    operatingParameterPLFList: any;


    selectedEnquiryForm: FormGroup;
    boardApprovalDetailsForm: FormGroup;

    subscriptions = new Subscription()

    expandPanel1 = true;
    expandPanel2 = false;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _service 
     * @param _router 
     * @param _dialogRef 
     */
    constructor(private _formBuilder: FormBuilder, public _loanEnquiryService: LoanEnquiryService, private _router: Router, private _dialogRef: MatDialog,
                private _loanMonitoringService: LoanMonitoringService, public _appService: AppService, private _matSnackBar: MatSnackBar,
                private _activatedRoute: ActivatedRoute) {
        
        _activatedRoute.data.subscribe((data) => {
            if (data.routeResolvedData !== null) {
                this.loanContractExtension = data.routeResolvedData;
            }
        });

        this.subscriptions.add(this._loanMonitoringService.loanMonitor.subscribe(data => {
            this.loanMonitor = data;
            console.log('updated loan monitor', this.loanMonitor);
        }));

        this.subscriptions.add(this._loanEnquiryService.selectedEnquiry.subscribe(data => {
            this.selectedEnquiry = data;
        }));          
        
        this.subscriptions.add(
            _loanEnquiryService.selectedLoanApplicationId.subscribe(data => {
                // set loanApplicationId
                this.loanApplicationId = data;
                // getLoanMonitor
                _loanMonitoringService.getLoanMonitor(this.loanApplicationId).subscribe(data => {
                    this.loanMonitor = data;
                })
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
                // getSecurityCompliances
                _loanMonitoringService.getSecurityCompliances(this.loanApplicationId).subscribe(data => {
                    this.securityComplianceList = data;
                })
                // getRateOfInterests
                _loanMonitoringService.getRateOfInterests(this.loanApplicationId).subscribe(data => {
                    this.rateOfInterestList = data;
                })         
                // getBorrowerFinancials
                _loanMonitoringService.getBorrowerFinancials(this.loanApplicationId).subscribe(data => {
                    this.borrowerFinancialsList = data;
                })
                // getPromoterFinancials
                _loanMonitoringService.getPromoterFinancials(this.loanApplicationId).subscribe(data => {
                    this.promoterFinancialsList = data;
                })
                // getFinancialCovenants
                _loanMonitoringService.getFinancialCovenants(this.loanApplicationId).subscribe(data => {
                    this.financialCovenantsList = data;
                })
                // getOperatingParameters
                _loanMonitoringService.getOperatingParameters(this.loanApplicationId).subscribe(data => {
                    this.operatingParameterList = data;
                })
                // getOperatingParameterPLFs
                _loanMonitoringService.getOperatingParameterPLFs(this.loanApplicationId).subscribe(data => {
                    this.operatingParameterPLFList = data;
                })
            })
        );
        
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

        // All about Security Compliance

        _loanMonitoringService.selectedSecurityCompliance.subscribe(data => {
            this.selectedSecurityCompliance = new SecurityComplianceModel(data);
        })

        // All about Operating Parameter

        _loanMonitoringService.selectedOperatingParameter.subscribe(data => {
            this.selectedOperatingParameter = new OperatingParameterModel(data);
        })

        // All about Operating Parameter PLF

        _loanMonitoringService.selectedOperatingParameterPLF.subscribe(data => {
            this.selectedOperatingParameterPLF = new OperatingParameterPLFModel(data);
        })

        // All about Rate of Interest

        _loanMonitoringService.selectedRateOfInterest.subscribe(data => {
            this.selectedRateOfInterest = new RateOfInterestModel(data);
        })

        // All about Borrower Financials

        _loanMonitoringService.selectedBorrowerFinancials.subscribe(data => {
            this.selectedBorrowerFinancials = new BorrowerFinancialsModel(data);
        })        

        // All about Promoter Financials

        _loanMonitoringService.selectedPromoterFinancials.subscribe(data => {
            this.selectedPromoterFinancials = new PromoterFinancialsModel(data);
        })

        // All about Financial Covenants

        _loanMonitoringService.selectedFinancialCovenants.subscribe(data => {
            this.selectedFinancialCovenants = new FinancialCovenantsModel(data);
        })
    }

    /**
     * ngOnDestroy()
     */
    ngOnDestroy(): void {
        this.subscriptions.unsubscribe();
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
        this.selectedEnquiryForm = this._formBuilder.group({
            busPartnerNumber: [this.selectedEnquiry.busPartnerNumber || ''],
            projectLocationState: [this.selectedEnquiry.projectLocationState || ''],
            projectType: [this.selectedEnquiry.projectType || ''],
            loanClassDescription: [this.selectedEnquiry.loanClassDescription || ''],
            projectCapacity: [this.selectedEnquiry.projectCapacity || ''],
            assistanceTypeDescription: [this.selectedEnquiry.assistanceTypeDescription || ''],
            projectCost: [this.selectedEnquiry.projectCost || ''],
            loanAmount: [this.selectedEnquiry.loanAmount || ''],
            financingTypeDescription: [this.selectedEnquiry.financingTypeDescription || ''],
            leadFI: [this.selectedEnquiry.leadFI || ''],
            stage: [this.selectedEnquiry.stage || '']
        });

        this.boardApprovalDetailsForm = this._formBuilder.group({
            boardMeetingNumber: [this.loanContractExtension.boardMeetingNumber || ''],
            boardApprovalDate: [this.loanContractExtension.boardApprovalDate || ''],
            loanNumber: [this.loanContractExtension.loanNumber || ''],
            sanctionLetterDate: [this.loanContractExtension.sanctionLetterDate || ''],
            loanDocumentationDate: [this.loanContractExtension.loanDocumentationDate || ''],
            firstDistributionDate: [this.loanContractExtension.firstDisbursementDate || ''],
            sanctionAmount: [this.loanContractExtension.sanctionAmount || ''],
            discributionStatus: [this.loanContractExtension.disbursementStatus || ''],
            scheduledCOD: [this.loanContractExtension.scheduledCOD || '']
        });
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
                this.getLoanMonitor();
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
            width: '1126px',
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
            width: '1126px',
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
                this.getLoanMonitor();
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
            width: '1126px',
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
            width: '1126px',
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
                this.getLoanMonitor();
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
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * updateOperatingParameter()
     * @param operation 
     */
    updateOperatingParameter(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedOperatingParameter': undefined
        };
        if (operation === 'updateOperatingParameter') {
            data.selectedOperatingParameter = this.selectedOperatingParameter;
        }
        const dialogRef = this._dialogRef.open(OperatingParameterUpdateDialogComponent, {
            panelClass: 'fuse-operating-parameter-update-dialog',
            width: '1000px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getOperatingParameters(this.loanApplicationId).subscribe(data => {
                    this.operatingParameterList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * updateOperatingParameterPLF()
     * @param operation 
     */
    updateOperatingParameterPLF(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedOperatingParameterPLF': undefined
        };
        if (operation === 'updateOperatingParameterPLF') {
            data.selectedOperatingParameterPLF = this.selectedOperatingParameterPLF;
        }
        const dialogRef = this._dialogRef.open(OperatingParameterPLFUpdateDialogComponent, {
            panelClass: 'fuse-operating-parameter-plf-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getOperatingParameterPLFs(this.loanApplicationId).subscribe(data => {
                    this.operatingParameterPLFList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * updateRateOfInterest()
     * @param operation 
     */
    updateRateOfInterest(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedRateOfInterest': undefined
        };
        if (operation === 'updateRateOfInterest') {
            data.selectedRateOfInterest = this.selectedRateOfInterest;
        }
        const dialogRef = this._dialogRef.open(RateOfInterestUpdateDialogComponent, {
            panelClass: 'fuse-rate-of-interest-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getRateOfInterests(this.loanApplicationId).subscribe(data => {
                    this.rateOfInterestList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * updateBorrowerFinancials()
     * @param operation 
     */
    updateBorrowerFinancials(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedFinancials': undefined
        };
        if (operation === 'updateFinancials') {
            data.selectedFinancials = this.selectedBorrowerFinancials;
        }
        const dialogRef = this._dialogRef.open(BorrowerFinancialsUpdateDialogComponent, {
            panelClass: 'fuse-borrower-financials-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getBorrowerFinancials(this.loanApplicationId).subscribe(data => {
                    this.borrowerFinancialsList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * updatePromoterFinancials()
     * @param operation 
     */
    updatePromoterFinancials(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedFinancials': undefined
        };
        if (operation === 'updateFinancials') {
            data.selectedFinancials = this.selectedPromoterFinancials;
        }
        const dialogRef = this._dialogRef.open(PromoterFinancialsUpdateDialogComponent, {
            panelClass: 'fuse-promoter-financials-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getPromoterFinancials(this.loanApplicationId).subscribe(data => {
                    this.promoterFinancialsList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }    

    /**
     * updateFinancialCovenants()
     * @param operation 
     */
    updateFinancialCovenants(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this.loanApplicationId,
            'selectedFinancialCovenants': undefined
        };
        if (operation === 'updateFinancialCovenants') {
            data.selectedFinancialCovenants = this.selectedFinancialCovenants;
        }
        const dialogRef = this._dialogRef.open(FinancialCovenantsUpdateDialogComponent, {
            panelClass: 'fuse-financial-covenants-update-dialog',
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._loanMonitoringService.getFinancialCovenants(this.loanApplicationId).subscribe(data => {
                    this.financialCovenantsList = data;
                });
                this.getLoanMonitor();
            }
        });    
    }

    /**
     * sendMonitoringForApproval()
     */
     sendMonitoringForApproval(): void {
        let name = this._appService.currentUser.firstName + ' ' + this._appService.currentUser.lastName;
        let email = this._appService.currentUser.email;
        this._matSnackBar.open('Please wait while attempting to send monitoring for approval.', 'OK', { duration: 25000 });
        this._loanMonitoringService.sendMonitoringForApproval(this.loanMonitor.id, name, email).subscribe(
            response => {
                this.loanMonitor = response;
                this._matSnackBar.dismiss();
                this._matSnackBar.open('Monitoring is sent for approval.', 'OK', { duration: 7000 });
            },
            error => {
                this.disableSendForApproval = false;
                this._matSnackBar.open('Errors occured. Pls try again after sometime or contact your system administrator', 
                    'OK', { duration: 7000 });
            });
        this.disableSendForApproval = true;
    }

    /**
     * getLoanMonitor()
     */
    getLoanMonitor(): void {
        this._loanMonitoringService.getLoanMonitor(this.loanApplicationId).subscribe(data => {
            this.loanMonitor = data;
        })
    }
}
