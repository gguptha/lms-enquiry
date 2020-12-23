import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoanMonitoringService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLIE: BehaviorSubject<any> = new BehaviorSubject({});
    selectedLIEReportAndFee: BehaviorSubject<any> = new BehaviorSubject({});
    selectedLFA: BehaviorSubject<any> = new BehaviorSubject({});
    selectedLFAReportAndFee: BehaviorSubject<any> = new BehaviorSubject({});
    selectedTRA: BehaviorSubject<any> = new BehaviorSubject({});
    selectedTRAStatement: BehaviorSubject<any> = new BehaviorSubject({});

    /**
     *
     * @param _http
     */
    constructor(private _http: HttpClient) {
    }

    // All about LIE

    public getLendersIndependentEngineers(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/lendersIndependentEngineers');
    }

    public saveLIE(lie: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/lendersindependentengineers/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'lendersIndependentEngineer':lie });
    }

    public updateLIE(lie: any): Observable<any> {
        const url = "enquiry/api/loanApplications/lendersindependentengineers/" + lie.id;
        return this._http.put(url, { 'loanApplicationId':'', 'lendersIndependentEngineer':lie });
    }

    // All about LIE Reports And Fees
    
    public getLIEReportsAndFees(lieId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/lendersIndependentEngineer/' + lieId + '/lieReceiptsAndFees');
    }
    
    public saveLIEReportAndFee(lieReportAndFee: any, lieId: string): Observable<any> {
        const url = "enquiry/api/loanApplications/liereportandfeesubmission/create";
        return this._http.post(url, { 'lendersIndependentEngineerId': lieId, 'lieReportAndFee': lieReportAndFee });
    }

    public updateLIEReportAndFee(lieReportAndFee: any): Observable<any> {
        console.log('in service', lieReportAndFee);
        const url = "enquiry/api/loanApplications/liereportandfeesubmission/" + lieReportAndFee.id;
        return this._http.put(url, { 'lendersIndependentEngineerId': '', 'lieReportAndFee': lieReportAndFee });
    }

    // All about LFA

    public getLendersFinancialAdvisors(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/lendersFinancialAdvisors');
    }
    
    public saveLFA(lfa: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/lendersfinancialAdvisors/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'lendersFinancialAdvisor':lfa });
    }

    public updateLFA(lfa: any): Observable<any> {
        const url = "enquiry/api/loanApplications/lendersfinancialAdvisors/" + lfa.id;
        return this._http.put(url, { 'loanApplicationId':'', 'lendersFinancialAdvisor':lfa });
    }

    // All about LFA Reports And Fees

    public getLFAReportsAndFees(lfaId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/lendersFinancialAdvisor/' + lfaId + '/lfaReceiptsAndFees');
    }

    public saveLFAReportAndFee(lfaReportAndFee: any, lfaId: string): Observable<any> {
        const url = "enquiry/api/loanApplications/lfareportandfeesubmission/create";
        return this._http.post(url, { 'lendersFinancialAdvisorId': lfaId, 'lfaReportAndFee': lfaReportAndFee });
    }

    public updateLFAReportAndFee(lfaReportAndFee: any): Observable<any> {
        console.log('in service', lfaReportAndFee);
        const url = "enquiry/api/loanApplications/lfareportandfeesubmission/" + lfaReportAndFee.id;
        return this._http.put(url, { 'lendersFinancialAdvisorId': '', 'lfaReportAndFee': lfaReportAndFee });
    }

    // All about TRA

    public getTrustRetentionaccounts(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/trustretentionaccounts');
    }

    public saveTRA(tra: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/trustretentionaccount/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'trustRetentionAccount':tra });
    }

    public updateTRA(tra: any): Observable<any> {
        const url = "enquiry/api/loanApplications/trustretentionaccounts/" + tra.id;
        return this._http.put(url, { 'loanApplicationId':'', 'trustRetentionAccount':tra });
    }
    
    // All about TRA Statement

    public getTRAStatements(traId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/trustretentionaccount/' + traId + '/traStatements');
    }

    public saveTRAStatement(traStatement: any, traId: string): Observable<any> {
        const url = "enquiry/api/loanApplications/trastatement/create";
        return this._http.post(url, { 'trustRetentionAccountId': traId, 'trustRetentionAccountStatement': traStatement });
    }

    public updateTRAStatement(traStatement: any): Observable<any> {
        const url = "enquiry/api/loanApplications/trastatement/" + traStatement.id;
        return this._http.put(url, { 'trustRetentionAccountId': '', 'trustRetentionAccountStatement': traStatement });
    }

    // Others

    public searchLoanEnquiries(request: any): Observable<any> {
        return this._http.put<any>('enquiry/api/loanApplications/search', request);
    }
}
