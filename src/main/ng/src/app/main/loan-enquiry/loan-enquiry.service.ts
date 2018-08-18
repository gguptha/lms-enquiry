import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { LoanClass } from '../model/loanclass.model';
import { ProjectType } from '../model/projecttype.model';
import { FinancingType } from '../model/financingtype.model';
import { State } from '../model/state.model';
import { AssistanceType } from '../model/assistancetype.model';

export class LoanEnquiryService implements Resolve<any> {

    loanClasses: Array<string>;

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
    public getLoanClasses(): Observable<Array<any>> {
        return new Observable((observer) => {
            observer.next(LoanClass.getLoanClasses());
            observer.complete();
        });
    }

    /**
     * getFinancingTypes()
     * returns a list of financing types.
     */
    public getFinancingTypes(): Observable<Array<any>> {
        return new Observable((observer) => {
            observer.next(FinancingType.getFinancingTypes());
            observer.complete();
        });
    }

    /**
     * getProjectTypes()
     * Returns a list of project types.
     */
    public getProjectTypes(): Observable<Array<any>> {
        return new Observable((observer) => {
            observer.next(ProjectType.getProjectTypes());
            observer.complete();
        });
    }

    /**
     * getStates()
     * Returns a list of states in the country.
     */
    public getStates(): Observable<Array<string>> {
        return new Observable((observer) => {
            observer.next(State.getStates());
            observer.complete();
        });
    }

    /**
     * getAssistanceTypes()
     * Returns a list of assistance types.
     */
    public getAssistanceTypes(): Observable<Array<string>> {
        return new Observable((observer) => {
            observer.next(AssistanceType.getAssistanceTypes());
            observer.complete();
        });
    }
}
