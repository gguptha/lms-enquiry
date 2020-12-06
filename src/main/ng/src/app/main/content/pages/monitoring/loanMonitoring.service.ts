import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoanMonitoringService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLIE: BehaviorSubject<any> = new BehaviorSubject({});
    selectedLIEReportAndFee: BehaviorSubject<any> = new BehaviorSubject({});

    /**
     *
     * @param _http
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * getLendersIndependentEngineers()
     * @param loanApplicationId 
     */
    public getLendersIndependentEngineers(loanApplicationId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/' + loanApplicationId + '/lendersIndependentEngineers');
    }

    /**
     * saveLIE();
     * @param lie 
     * @param loanApplicationId 
     */
    public saveLIE(lie: any, loanApplicationId: any): Observable<any> {
        const url = "enquiry/api/loanApplications/lendersindependentengineers/create";
        return this._http.post(url, { 'loanApplicationId':loanApplicationId, 'lendersIndependentEngineer':lie });
    }

    /**
     * getLIEReportsAndFees()
     * @param lieId 
     */
    public getLIEReportsAndFees(lieId: string): Observable<any> {
        return this._http.get('enquiry/api/loanApplications/lendersIndependentEngineer/' + lieId + '/lieReceiptsAndFees');
    }
    
    /**
     * saveLIEReportAndFee();
     * @param lie 
     * @param loanApplicationId 
     */
    public saveLIEReportAndFee(lieReportAndFee: any, lieId: string): Observable<any> {
        const url = "enquiry/api/loanApplications/liereportandfeesubmission/create";
        return this._http.post(url, { 'lendersIndependentEngineerId': lieId, 'lieReportAndFee': lieReportAndFee });
    }

    /**
     * searchLoanEnquiries()
     * Fetches a list of loan applications based on the request parameters.
     * @param request
     */
    public searchLoanEnquiries(request: any): Observable<any> {
        return this._http.put<any>('enquiry/api/loanApplications/search', request);
    }
}
