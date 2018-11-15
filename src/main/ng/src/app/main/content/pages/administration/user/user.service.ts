import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, forkJoin, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../../../model/user.model';

@Injectable()   
export class UserService {

    users: BehaviorSubject<UserModel[]>;

    selectedUser: BehaviorSubject<UserModel>;

    constructor(private _http: HttpClient) {
    }

}
