import { Resolve, CanActivate, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router/src/router_state';
import { Observable, forkJoin } from 'rxjs';
import { Injectable } from '@angular/core';
import { PartnerModel } from './main/content/model/partner.model';

@Injectable()
export class AppService implements CanActivate {

    /**
     * Currently logged in user. 
     */
    currentUser: PartnerModel;

    constructor(private _http: HttpClient, private _router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> |
        Promise<boolean> {

        return new Observable<boolean>((observer) => {
            this._http.get<PartnerModel>('api/me').subscribe(response => {
                this.currentUser = response;
                this._router.navigate(['/enquiryApplication']);
                observer.next(false);
            });
        });
    }
}
