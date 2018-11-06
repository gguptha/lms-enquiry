import { Resolve, CanActivate, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router/src/router_state';
import { Observable, forkJoin } from 'rxjs';
import { Injectable } from '@angular/core';
import { UserModel } from './main/content/model/user.model';

@Injectable()
export class AppService implements CanActivate {

    /**
     * Currently logged in user. 
     */
    currentUser: UserModel;

    /**
     * constructor()
     * @param _http 
     * @param _router 
     */
    constructor(private _http: HttpClient, private _router: Router) {
    }

    /**
     * canActivate()
     * @param route 
     * @param state 
     */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> |
        Promise<boolean> {

        return new Observable<boolean>((observer) => {
            this._http.get<UserModel>('api/me').subscribe(response => {
                this.currentUser = response;
                console.log('currentUser', this.currentUser);
                this._router.navigate(['/enquiryApplication']);
                observer.next(false);
            });
        });
    }

    /**
     * logout()
     */
    logout(): void
    {
    }
}
