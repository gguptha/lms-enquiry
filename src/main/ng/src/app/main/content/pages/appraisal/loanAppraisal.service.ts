import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';

@Injectable()
export class LoanAppraisalService {

    public enquirySearchList: BehaviorSubject<any>;

    /**
     * constructor()
     * @param _http
     */
    constructor(private _http: HttpClient, private _loanEnquiryService: LoanEnquiryService) {
    }

    /**
     * getPartnersByRoleType()
     * @param roleType 
     * @returns 
     */
    public getPartnersByRole(role: string): Observable<any> {
        return this._http.get("enquiry/api/partners/role/" + role);
    }
    
    /**
     * getAppraisalOfficers()
     * @param loanApplicationId 
     */
    public getLoanOfficers(loanApplicationId: string): Observable<any> {
        return this._http.get("enquiry/api/loanPartners/search/findByLoanApplicationIdOrderBySerialNumberDesc?loanApplicationId=" 
                + loanApplicationId);
    }

    /**
     * createAppraisalOfficer()
     * @param appraisalOfficer 
     */
    public createLoanOfficer(loanOfficer: any): Observable<any> {
        return this._http.post("enquiry/api/loanPartners/create", loanOfficer);
    }

    /**
     * updateLoanOfficer()
     * @param loanOfficer 
     * @returns 
     */
    public updateLoanOfficer(loanOfficer: any): Observable<any> {
        return this._http.put("enquiry/api/loanPartners/update", loanOfficer);
    }
}
