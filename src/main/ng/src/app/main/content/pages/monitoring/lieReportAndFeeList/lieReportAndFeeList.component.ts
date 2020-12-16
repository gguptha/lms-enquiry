import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LIEReportAndFeeModel } from 'app/main/content/model/lieReportAndFee.model';
import { BehaviorSubject } from 'rxjs';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-lie-report-fee-list',
    templateUrl: './lieReportAndFeeList.component.html',
    styleUrls: ['./lieReportAndFeeList.component.scss'],
    animations: fuseAnimations
})
export class LIEReportAndFeeListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set lieReportAndFeeList(list: any) {
        this.dataSource = new MatTableDataSource(list);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'reportType', 'dateOfReceipt','invoiceDate', 'invoiceNo', 'feeAmount', 'statusOfFeeReceipt', 'statusOfFeePaid', 'documentTitle', 
            'nextReportDate'
    ];

    selectedLIEReportAndFee: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedLIEReportAndFee.next({});
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
        /**
         * this.sort will not be initialized in the constructor phase. It will be undefined and hence sorting
         * will not work. The below line has to be in ngOnInit() which is executed after all initializations.
         */
        this.dataSource.sort = this.sort;
    }

    /**
     *
     * @param enquiry
     */
    onSelect(lieReportAndFee: any): void {
        this.selectedLIEReportAndFee = lieReportAndFee;
        this._service.selectedLIEReportAndFee.next(this.selectedLIEReportAndFee);
    }
}
