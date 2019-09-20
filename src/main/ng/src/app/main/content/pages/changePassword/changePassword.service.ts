import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AppService } from 'app/app.service';

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
        return this._http.put<any>('enquiry/api/password/modify', request);
    }
}
