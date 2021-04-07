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
     * approveTask()
     * @param workFlowProcessRequestResource: any
     */
     approveTask(workFlowProcessRequestResource: any): Observable<any> {
        return this._httpClient.put<any>('enquiry/api/approvetask', workFlowProcessRequestResource);
    }

    /**
     * rejectTask()
     * @param workFlowProcessRequestResource: any
     */
    rejectTask(workFlowProcessRequestResource: any): Observable<any> {
        return this._httpClient.put<any>('enquiry/api/rejecttask', workFlowProcessRequestResource);
    }
}
