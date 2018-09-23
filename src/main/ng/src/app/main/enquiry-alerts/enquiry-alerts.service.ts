import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanApplication } from '../model/loanapplication.model';
import { LoanEnquiryService } from '../loan-enquiry/loan-enquiry.service';
import { Partner } from '../model/partner.model';

@Injectable()   
export class EnquiryAlertsService implements Resolve<any> {

    loanApplications: BehaviorSubject<LoanApplication[]>;

    selectedLoanApplicationId: BehaviorSubject<string>;

    constructor(private _http: HttpClient, private _loanEnquiryService: LoanEnquiryService) {
    }

    /**
     * resolve()
     * Router resolveer, fetches data before the ui is created.
     * @param route 
     * @param state 
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
        if (route.routeConfig.path === 'enquiryreview') {
            if (this.selectedLoanApplicationId !== undefined) {
                return forkJoin([
                    this._loanEnquiryService.getLoanClasses(), // get loan classes.
                    this._loanEnquiryService.getFinancingTypes(), // get financing types.
                    this._loanEnquiryService.getProjectTypes(), // get project types.
                    this._loanEnquiryService.getStates(), // get states.
                    this._loanEnquiryService.getAssistanceTypes(), // get assistance types.
                    this.getLoanApplication(this.selectedLoanApplicationId.getValue()) // get loan application.
                ]);
            }
        }
        else {
            return forkJoin([
                this.getLoanApplications(1)
            ]);
        }
    }

    /**
     * getLoanApplications()
     * Fetches a list of loan applications with a particular status.
     * @param status 
     */
    public getLoanApplications(status: number): Observable<LoanApplication[]> {
        return new Observable((observer) => {
            this._http.get<any>('api/loanApplications?status=' + status).subscribe(result => {
                const loanApplications = new Array<LoanApplication>();
                result.content.map(loanApplication => {
                    loanApplications.push(new LoanApplication(loanApplication));
                });
                observer.next(loanApplications);
                observer.complete();
            });
        });
    }

    public getLoanApplication(enquiryId: string): Observable<LoanApplication> {
        return this._http.get<LoanApplication>('api/loanApplications/' + enquiryId);
    }

    public getPartner(partnerId: string): Observable<Partner> {
        return this._http.get<Partner>('api/partners/' + partnerId);
    }
}
