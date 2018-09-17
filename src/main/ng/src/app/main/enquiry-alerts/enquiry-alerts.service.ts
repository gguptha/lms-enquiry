import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanApplication } from '../model/loanapplication.model';

@Injectable()   
export class EnquiryAlertsService implements Resolve<any> {

    loanApplications: BehaviorSubject<LoanApplication[]>;

    constructor(private _http: HttpClient) {
    }

    /**
     * resolve()
     * Router resolveer, fetches data before the ui is created.
     * @param route 
     * @param state 
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
        return forkJoin([
            this.getLoanApplications(1)
        ]);
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
                console.log(loanApplications);
                observer.next(loanApplications);
                observer.complete();
            });
        });
    }
}
