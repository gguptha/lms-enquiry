import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../../../model/user.model';

@Injectable()
export class UserService 
{
    users: BehaviorSubject<UserModel[]>;

    selectedUser: BehaviorSubject<UserModel> = new BehaviorSubject(new UserModel({}));

    /**
     * constructor()
     * @param _http
     */
    constructor(private _http: HttpClient) { }

    /**
     * createUser()
     * Creates a new user.
     * @param user 
     */
    public createUser(user: UserModel): Observable<any> 
    {
        return this._http.post('/api/user', user);
    }

    /**
     * getUsers()
     * Fetches a list of users.
     */
    public getUsers(): void
    {
        this._http.get<any>('api/users').subscribe((response) => {
            const users = new Array<UserModel>();
            response._embedded.users.map((user) => {
                users.push(new UserModel(user));
            });
            this.users = new BehaviorSubject(users);
        });
    }
}