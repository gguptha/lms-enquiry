///<reference path="../../../../../../@fuse/animations/index.ts"/>
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {MailObject} from "../../../model/mailObject.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MailRepoService} from "./mail-repo.service";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs/Rx";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {fuseAnimations} from "../../../../../../@fuse/animations/index";
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-mail-repo',
  templateUrl: './mail-repo.component.html',
  styleUrls: ['./mail-repo.component.scss'],
  animations: [
    fuseAnimations,
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class MailRepoComponent implements OnInit {

  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @Input()
  set mailObjectList(mailObjectList: MailObject[]) {
    this.dataSource = new MatTableDataSource(mailObjectList);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }


  displayedColumns = ['id', 'toAddress', 'dateTime', 'subject','sendingApp'];

  expandedElement: MailObject | null;



  selectedMailObject: MailObject;

  emailSearchForm: FormGroup;
  expandPanel = true;

  pageSizeOptions: number[] = [10, 25, 50, 100];

  constructor(private _service: MailRepoService,_formBuilder: FormBuilder, private _router: Router,
              private _snackBar: MatSnackBar) {
    this.emailSearchForm = _formBuilder.group({
      toAddress: [],
      dateFrom: [],
      dateTo: []
    });
    _service.selectedMailObject = undefined;

  }


  ngOnInit() {
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 15000,
    });
  }

  /**
   * searchMailObjects()
   */
  searchMailObjects(): void {

    const searchForm = this.emailSearchForm.value;

    let searchParameters: Array<string> = [searchForm.toAddress,
      searchForm.dateFrom,
      searchForm.dateTo];

    let SearchParameterObject = { "toAddress" : searchForm.toAddress,
                                 "dateFrom" :  searchForm.dateFrom,
                                 "dateTo" : searchForm.dateTo}


    this._service.searchMailObjects(SearchParameterObject).subscribe((result) => {
      const mailObjects = new Array<MailObject>();

      result.map(mailObjectModel => {

        mailObjects.push(new MailObject(mailObjectModel));
      });
      console.log("Mail Objects" + mailObjects.values());
      this.mailObjectList = mailObjects;
      this.expandPanel = true;
    });
  }

/*
* @param enquiry
*/
  onSelect(mailObject: MailObject): void {
    this.selectedMailObject = mailObject;
    this._service.selectedMailObjectId = new BehaviorSubject(mailObject.id);

  }

  /**
   * Redirect to Email Details
   */
  redirectToMailDetails(): void {
    if (this._service.selectedMailObjectId!== undefined) {
      this._service.selectedMailObjectId.next(this._service.selectedMailObjectId.value);
    }
    else {
      this._service.selectedMailObjectId= new BehaviorSubject(this._service.selectedMailObjectId.value);

    }

  }

}
