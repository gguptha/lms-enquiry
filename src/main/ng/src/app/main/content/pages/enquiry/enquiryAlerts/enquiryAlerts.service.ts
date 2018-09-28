import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanApplicationModel } from '../../../model/loanApplication.model';
import { LoanEnquiryService } from '../enquiryApplication/enquiryApplication.service';
import { PartnerModel } from '../../../model/partner.model';

@Injectable()   
export class EnquiryAlertsService implements Resolve<any> {

    loanApplications: BehaviorSubject<LoanApplicationModel[]>;

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
    public getLoanApplications(status: number): Observable<LoanApplicationModel[]> {
        return new Observable((observer) => {
            this._http.get<any>('api/loanApplications?status=' + status).subscribe(result => {
                const loanApplications = new Array<LoanApplicationModel>();
                result.content.map(loanApplication => {
                    loanApplications.push(new LoanApplicationModel(loanApplication));
                });
                observer.next(loanApplications);
                observer.complete();
            });
        });
    }

    public getLoanApplication(enquiryId: string): Observable<LoanApplicationModel> {
        return this._http.get<LoanApplicationModel>('api/loanApplications/' + enquiryId);
    }

    public getPartner(partnerId: string): Observable<PartnerModel> {
        return this._http.get<PartnerModel>('api/partners/' + partnerId);
    }

    /**
     * updateLoanApplication()
     * Saves/updates the loan application to the database.
     * @param loanApplication 
     * @param partner 
     */
    public updateLoanApplication(loanApplication: any, partner: any): Observable<any> {
        return this._http.put('/api/loanApplications/' + loanApplication.id, {loanApplication, partner});
    }
}
