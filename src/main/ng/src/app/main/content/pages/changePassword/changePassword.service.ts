import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AppService } from 'app/app.service';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class ChangePasswordService 
{
    constructor(private _http: HttpClient, private _appService: AppService)
    {
    }

    public changePassword(password: string): Observable<any>
    {
        const request = {
            'password': password,
            'email': this._appService.currentUser.email
        };
        return this._http.put<any>('enquiry/api/password/modify', request).catch(this.errorHandler);
    }

    /**
     * errorHandler()
     * @param error 
     */
    errorHandler(error: HttpErrorResponse): Observable<any> {
        return Observable.throw(error.error.message || 'Service error occured.');
    }
}
