import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StateModel } from '../../../model/state.model';
import { AppService } from '../../../../../app.service';

@Injectable()   
export class LoanEnquiryService implements Resolve<any> {

    /**
     * constructor()
     * @param _http 
     */
    constructor(private _http: HttpClient, private _appService: AppService) {
    }

    /**
     * resolve()
     * Router resolveer, fetches data before the ui is created.
     * @param route 
     * @param state 
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
        return forkJoin([
            this.getLoanClasses(), // get loan classes.
            this.getFinancingTypes(), // get financing types.
            this.getProjectTypes(), // get project types.
            this.getStates(), // get states.
            this.getAssistanceTypes() // get assistance types.
        ]);
    }

    /**
     * getLoanClasses()
     * returns a list of loan classes.
     */
    public getLoanClasses(): Observable<any> {
        return this._http.get('api/loanClasses');
    }

    /**
     * getFinancingTypes()
     * returns a list of financing types.
     */
    public getFinancingTypes(): Observable<any> {
        return this._http.get('api/financingTypes');
    }

    /**
     * getProjectTypes()
     * Returns a list of project types.
     */
    public getProjectTypes(): Observable<any> {
        return this._http.get('api/projectTypes');
    }

    /**
     * getAssistanceTypes()
     * Returns a list of assistance types.
     */
    public getAssistanceTypes(): Observable<any> {
        return this._http.get('api/assistanceTypes');
    }

    /**
     * getStates()
     * Returns a list of states in the country.
     */
    public getStates(): Observable<Array<string>> {
        return new Observable((observer) => {
            observer.next(StateModel.getStates());
            observer.complete();
        });
    }

    /**
     * saveLoanApplication()
     * Saves the loan application to the database.
     * @param loanApplication 
     * @param partner 
     */
    public saveLoanApplication(loanApplication: any, partner: any): Observable<any> {
        return this._http.post('/api/loanApplications', {loanApplication, partner});
    }

    public me(): void {
        this._http.get<any>('api/me').subscribe(response => {}
        );
    }
}
