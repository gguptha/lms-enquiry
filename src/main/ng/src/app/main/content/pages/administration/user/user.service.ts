import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../../../model/user.model';

@Injectable()
export class UserService
{
    users: BehaviorSubject<UserModel[]>;

    userId: string;

    selectedUser: BehaviorSubject<UserModel> = new BehaviorSubject(new UserModel({}));

    user : BehaviorSubject<UserModel>;

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
        return this._http.post('enquiry/api/user', user);
    }

    /**
     * updateUser()
     * Creates a new user.
     * @param user
     */
    public updateUser(user: UserModel): Observable<any>
    {
        return this._http.put('enquiry/api/user', user);
    }

    /**
     * getUsers()
     * Fetches a list of users.
     */
    public getUsers(): void
    {
        this._http.get<any>('enquiry/api/users?sort=role&sort=firstName').subscribe((response) => {
            const users = new Array<UserModel>();
            response._embedded.users.map((user) => {
                users.push(new UserModel(user));
            });
            if (this.users === undefined) {
                this.users = new BehaviorSubject(users);
            }
            else {
                this.users.next(users);
            }
        });
    }

    /*
      Get user by Email Id
     */

  /**
   * getUserByEmail()
   * @param email
   */
  public getUserByEmail(email: string): Observable<UserModel> {
    return this._http.get<UserModel>('enquiry/api/user?userId=' + email);
  }




}
