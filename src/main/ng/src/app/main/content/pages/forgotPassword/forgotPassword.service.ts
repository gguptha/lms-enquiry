import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../../model/user.model';

@Injectable()
export class ForgotPasswordService 
{
    constructor(private _http: HttpClient)
    {
    }

    public resetPassword(emailId: string): Observable<any>
    {
        return this._http.put<any>('api/password/reset', emailId);
    }
}
