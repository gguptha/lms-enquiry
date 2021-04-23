import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';

@Injectable({
    providedIn: 'root'
})
export class ChangeDocumentService {

    constructor(private _httpClient: HttpClient) { }

    fetchChangeDocuments(processName: string, loanContractId: string, dateFrom: Date, dateTo: Date, page: number, size: number): Observable<any> {
        let api = 'enquiry/api/changedocuments?processName=' + processName + '&page=' + page + '&size=' + size;
        if (loanContractId !== null && loanContractId !== '') {
            api += '&loanContractId=' + loanContractId;
        }
        if (dateFrom !== null) {
            api += '&dateFrom=' + this.parseDateToString(dateFrom);
        }
        if (dateTo !== null) {
            api += '&dateTo=' + this.parseDateToString(dateTo);
        }
        return this._httpClient.get<any>(api);
    }

    parseDateToString(sdate: Date): string {
        return moment(sdate).format('YYYY-MM-DD');
    }
}
