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
            this._httpClient.get<UserModel>('enquiry/api/me').subscribe((response) => {
                console.log(response);
                //alert('enquiryApplication.guard.ts');
                this.currentUser = response;
                if (this.currentUser.passwordReset) {
                    this._router.navigate(['forceChangePassword']);
                    observer.next(false);
                }
                else if (this.currentUser.role === 'TR0100' ||
                    this.currentUser.role === 'ZLM023' ||  //Added by Sajeev on Aug 10, 2019 to include ZLM023 and ZLM013
                    this.currentUser.role === 'ZLM013') {
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
