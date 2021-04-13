import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-lfa-report-fee-list',
    templateUrl: './lfaReportAndFeeList.component.html',
    styleUrls: ['./lfaReportAndFeeList.component.scss'],
    animations: fuseAnimations
})
export class LFAReportAndFeeListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set lfaReportAndFeeList(list: any) {
        this.dataSource = new MatTableDataSource(list);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'reportType', 'dateOfReceipt','invoiceDate', 'invoiceNo', 'feeAmount', 'statusOfFeeReceipt', 'statusOfFeePaid', 'documentTitle', 
            'nextReportDate', 'download'
    ];

    selectedLFAReportAndFee: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedLFAReportAndFee.next({});
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
    onSelect(lfaReportAndFee: any): void {
        this.selectedLFAReportAndFee = lfaReportAndFee;
        this._service.selectedLFAReportAndFee.next(this.selectedLFAReportAndFee);
    }

    /**
     * getFileURL()
     * @param fileReference 
     */
    getFileURL(fileReference: string): string {
        return 'enquiry/api/download/' + fileReference;
    }
    
    /**
     * getReportType()
     * @param reportType 
     */
    getReportType(reportType: string): string {
        const filtered = LoanMonitoringConstants.reportTypes.filter(obj => obj.code === reportType);
        return filtered[0].value;
    }
}
