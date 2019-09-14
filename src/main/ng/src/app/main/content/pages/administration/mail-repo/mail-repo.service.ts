import {Observable, BehaviorSubject, forkJoin} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {MailObject} from "../../../model/mailObject.model";



@Injectable()
export class MailRepoService {


  mailObjects: BehaviorSubject<MailObject[]>;

  public selectedMailObject: BehaviorSubject<MailObject> = new BehaviorSubject(new MailObject({}));

  public  mailObject: BehaviorSubject<MailObject>;

  selectedMailObjectId: BehaviorSubject<string>;



  /**
   * constructor()
   * @param _http
   */
  constructor(private _http: HttpClient) { }

  /**
   * resolve()
   * Router resolveer, fetches data before the ui is created.
   * @param route
   * @param state
   */
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    if (route.routeConfig.path === 'mailRepo') {

    }
    else {
      return forkJoin([
       //this.getEnquiryApplications(1)
      ]);
    }
  }


  // public searchMailObjects(request: Array<string>): Observable<MailObject[]> {
  //
  //   let queryParams;
  //
  //   request.forEach(function(value) {
  //     if (value != undefined){
  //       queryParams = queryParams + value + "&";
  //     }
  //   });
  //
  //
  //   return new Observable(observer => {
  //     const mailObjects = new Array<MailObject>();
  //     this._http.get<MailObject[]>('enquiry/api/mailObjects/queryParams?query=' + request).subscribe(result => {
  //       result.map(mailObject => {
  //         mailObjects.push(new MailObject(mailObject));
  //       });
  //       observer.next(mailObjects);
  //       observer.complete();
  //     });
  //   });
  // }



  /**
   * searchMailObjects()
   * Fetches a list of Email Objects based on Search Params
   * @param request
   */
  public searchMailObjects(request: any): Observable<any> {
    return this._http.put<any>('enquiry/api/mailObjects/search', request);
  }

}
