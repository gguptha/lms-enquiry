import {CanActivate, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router/src/router_state';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {UserModel} from './main/content/model/user.model';

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
                console.log(response);
                this.currentUser = response;
                if (this.currentUser.role === 'TR0100') {
                    this._router.navigate(['enquiryApplication']);
                    observer.next(false);
                }
                else if (this.currentUser.role === 'ZLM023') {
                    this._router.navigate(['userManagement']);
                    observer.next(false);
                }
                else {
                    this._router.navigate(['enquiryAlerts']);
                    observer.next(false);
                }
            });
        });
    }

    me(): Observable<UserModel> {
        return this._http.get<UserModel>('api/me');
    }
}
