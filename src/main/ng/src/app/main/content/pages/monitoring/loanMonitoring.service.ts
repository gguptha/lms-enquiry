import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoanMonitoringService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLoanApplicationId: BehaviorSubject<string>;
    selectedLoanApplicationPartyNumber: BehaviorSubject<string>;

    /**
     *
     * @param _http
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * getTechnicalStatus()
     * returns a list of Technical Status
     */
    public getTechnicalStatus(): Observable<any> {
        return this._http.get('enquiry/api/technicalStatus');
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
     * searchLoanEnquiries()
     * Fetches a list of loan applications based on the request parameters.
     * @param request
     */
    public searchLoanEnquiries(request: any): Observable<any> {
        return this._http.put<any>('enquiry/api/loanApplications/search', request);
    }
}
