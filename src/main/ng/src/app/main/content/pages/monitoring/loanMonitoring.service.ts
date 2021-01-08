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
    selectedTandC: BehaviorSubject<any> = new BehaviorSubject({});
    selectedSecurityCompliance: BehaviorSubject<any> = new BehaviorSubject({});
    selectedSiteVisit: BehaviorSubject<any> = new BehaviorSubject({});
    selectedRateOfInterest: BehaviorSubject<any> = new BehaviorSubject({});
    selectedBorrowerFinancials: BehaviorSubject<any> = new BehaviorSubject({});
    selectedPromoterFinancials: BehaviorSubject<any> = new BehaviorSubject({});
    selectedFinancialCovenants: BehaviorSubject<any> = new BehaviorSubject({});
    
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

    // All about Terms & Conditions

    public getTermsAndConditions(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/termsandconditions');
    }

    public saveTandC(tandc: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/termsandconditions/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'termsAndConditionsModification':tandc });
    }

    public updateTandC(tandc: any): Observable<any> {
        const url = "enquiry/api/loanApplications/termsandconditions/" + tandc.id;
        return this._http.put(url, { 'loanApplicationId':'', 'termsAndConditionsModification':tandc });
    }

    // All about Security Compliance

    public getSecurityCompliances(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/securitycompliances');
    }

    public saveSecurityCompliance(securityCompliance: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/securitycompliance/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'securityCompliance':securityCompliance });
    }

    public updateSecurityCompliance(securityCompliance: any): Observable<any> {
        const url = "enquiry/api/loanApplications/securitycompliance/" + securityCompliance.id;
        return this._http.put(url, { 'loanApplicationId':'', 'securityCompliance':securityCompliance });
    }    
    
    // All about Site Visit

    public getSiteVisits(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/sitevisits');
    }

    public saveSiteVisit(siteVisit: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/sitevisit/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'siteVisit':siteVisit });
    }

    public updateSiteVisit(siteVisit: any): Observable<any> {
        const url = "enquiry/api/loanApplications/sitevisit/" + siteVisit.id;
        return this._http.put(url, { 'loanApplicationId':'', 'siteVisit':siteVisit });
    }    

    // All about Rate of Interest
    
    public getRateOfInterests(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/rateofinterest');
    }

    public saveRateOfInterest(rateOfInterest: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/rateofinterest/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'rateOfInterest':rateOfInterest });
    }

    public updateRateOfInterest(rateOfInterest: any): Observable<any> {
        const url = "enquiry/api/loanApplications/rateofinterest/" + rateOfInterest.id;
        return this._http.put(url, { 'loanApplicationId':'', 'rateOfInterest':rateOfInterest });
    }  

    // All about Borrower Financials
    
    public getBorrowerFinancials(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/borrowerfinancials');
    }

    public saveBorrowerFinancials(borrowerfinancials: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/borrowerfinancials/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'borrowerFinancials':borrowerfinancials });
    }

    public updateBorrowerFinancials(borrowerfinancials: any): Observable<any> {
        const url = "enquiry/api/loanApplications/borrowerfinancials/" + borrowerfinancials.id;
        return this._http.put(url, { 'loanApplicationId':'', 'borrowerFinancials':borrowerfinancials });
    }  


    // All about Promoter Financials
    
    public getPromoterFinancials(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/promoterfinancials');
    }

    public savePromoterFinancials(promoterfinancials: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/promoterfinancials/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'promoterFinancials':promoterfinancials });
    }

    public updatePromoterFinancials(promoterfinancials: any): Observable<any> {
        const url = "enquiry/api/loanApplications/promoterfinancials/" + promoterfinancials.id;
        return this._http.put(url, { 'loanApplicationId':'', 'promoterFinancials':promoterfinancials });
    }      
    
    // All about Financial Covenants
    
    public getFinancialCovenants(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/financialcovenants');
    }

    public saveFinancialCovenants(financialCovenants: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/financialcovenants/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'financialCovenants':financialCovenants });
    }

    public updateFinancialCovenants(financialCovenants: any): Observable<any> {
        const url = "enquiry/api/loanApplications/financialcovenants/" + financialCovenants.id;
        return this._http.put(url, { 'loanApplicationId':'', 'financialCovenants':financialCovenants });
    }      

    // Others

    public searchLoanEnquiries(request: any): Observable<any> {
        return this._http.put<any>('enquiry/api/loanApplications/search', request);
    }
}
