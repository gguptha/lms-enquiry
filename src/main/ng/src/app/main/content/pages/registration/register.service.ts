import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserModel } from '../../model/user.model';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class RegisterService 
{
    constructor(private _http: HttpClient)
    {
    }

    public register(user: UserModel): Observable<any>
    {
        return this._http.post('enquiry/api/signup', user).catch(this.errorHandler);
    }

    /**
     * errorHandler()
     * @param error 
     */
    errorHandler(error: HttpErrorResponse): Observable<any> {
        return Observable.throw(error.error.message || 'Service error occured.');
    }
}
