import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanEnquiryService } from '../enquiry/enquiryApplication.service';

@Injectable()
export class LoanAppraisalService {

    /**
     * constructor()
     * @param _http
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * getLaonAppraisal()
     * @param loanApplicationId 
     */
    public getLaonAppraisal(loanApplicationId: string): Observable<any> {
        return this._http.get("enquiry/api/loanAppraisals/search/findByLoanApplicationId?loanApplicationId=" + loanApplicationId);
    }

    /**
     * getPartnersByRoleType()
     * @param roleType 
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
     */
    public updateLoanOfficer(loanOfficer: any): Observable<any> {
        return this._http.put("enquiry/api/loanPartners/update", loanOfficer);
    }

    /**
     * createLoanAppraisalKYC()
     * @param loanAppraisalKYC 
     */
    public createLoanAppraisalKYC(loanAppraisalKYC: any): Observable<any> {
        return this._http.post("enquiry/api/loanAppraisalKYCs/create", loanAppraisalKYC);
    }

    /**
     * updateLoanAppraisalKYC()
     * @param loanAppraisalKYC 
     */
    public updateLoanAppraisalKYC(loanAppraisalKYC: any): Observable<any> {
        return this._http.put("enquiry/api/loanAppraisalKYCs/update", loanAppraisalKYC);
    }

    /**
     * getLaonAppraisalKYCs()
     * @param loanApplicationId 
     */
    public getLaonAppraisalKYCs(loanApplicationId: string): Observable<any> {
        return this._http.get("enquiry/api/loanAppraisalKYCs/search/findByLoanAppraisalLoanApplicationId?loanApplicationId=" 
                + loanApplicationId);
    }

    /**
     * uploadVaultDocument()
     * @param file 
     */
    public uploadVaultDocument(file: FormData): Observable<any> {
        return this._http.post('enquiry/api/upload', file);
    }
}
