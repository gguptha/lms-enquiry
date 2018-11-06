import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../../model/user.model';

@Injectable()
export class RegisterService 
{
    constructor(private _http: HttpClient)
    {
    }

    public register(user: UserModel): Observable<any>
    {
        return this._http.post('api/signup', user);
    }
}
