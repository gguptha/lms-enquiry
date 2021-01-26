import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-borrower-financials-list',
    templateUrl: './borrowerFinancialsList.component.html',
    styleUrls: ['./borrowerFinancialsList.component.scss'],
    animations: fuseAnimations
})
export class BorrowerFinancialsListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set borrowerFinancialsList(borrowerFinancialsList: any) {
        this.dataSource = new MatTableDataSource(borrowerFinancialsList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'fiscalYear', 'turnover', 'pat','netWorth', 'overAllRating', 'pdfAnnualReport', 'pdfRating'
    ];

    selectedFinancials: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedBorrowerFinancials.next({});
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
     * getFileURL()
     * @param fileReference 
     */
    getFileURL(fileReference: string): string {
        return 'enquiry/api/download/' + fileReference;
    }

    /**
     * onSelect()
     * @param selectedFinancials
     */
    onSelect(selectedFinancials: any): void {
        this.selectedFinancials = selectedFinancials;
        this._service.selectedBorrowerFinancials.next(this.selectedFinancials);
    }
}
