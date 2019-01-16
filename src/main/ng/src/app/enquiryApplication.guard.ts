import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FuseNavigationService } from '@fuse/components/navigation/navigation.service';
import { HttpClient } from '@angular/common/http';
import { UserModel } from './main/content/model/user.model';

@Injectable()
export class EnquiryApplicationRouteGuard implements CanActivate {

    currentUser: UserModel;

    constructor(private _appService: FuseNavigationService, private _httpClient: HttpClient, private _router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return new Observable<boolean>(observer => {
            this._httpClient.get<UserModel>('api/me').subscribe((response) => {
                console.log(response);
                this.currentUser = response;
                if (this.currentUser.role === 'TR0100') {
                    observer.next(true);
                }
                else {
                    this._router.navigate(['enquiryAlerts']);
                    observer.next(false);
                }
            });
        });
    }
}
