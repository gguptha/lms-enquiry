import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class InboxService implements Resolve<any> {

    selectedItem: BehaviorSubject<any>;

    /**
     * constructor()
     * @param _httpClient: HttpClient
     */
    constructor(private _httpClient: HttpClient) { }

    /**
     * resolve()
     */
    resolve(): Observable<any> {
        return this.fetchTasks();
    }

    /**
     * fetchTasks()
     */
    fetchTasks(): Observable<any> {
        return this._httpClient.get<any>('enquiry/api/tasklist');
    }


    /**
     * approveEvaluation()
     * @param template: any
     */
    approveEvaluation(id: any): Observable<any> {
        console.log("ID for Approval :" + id);
        return this._httpClient.put(<any>('enquiry/api/riskModel/process?action=3&id='+id), id);
    }


    /**
     * rejectEvaluation()
     * @param template: any
     */
    rejectEvaluation(id: any): Observable<any> {
        console.log("ID for Rejection :" + id);
        return this._httpClient.put(<any>('enquiry/api/riskModel/process?action=4&id='+id), id);
    }
}
