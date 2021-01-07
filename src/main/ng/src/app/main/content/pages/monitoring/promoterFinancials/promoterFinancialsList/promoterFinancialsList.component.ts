import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-promoter-financials-list',
    templateUrl: './promoterFinancialsList.component.html',
    styleUrls: ['./promoterFinancialsList.component.scss'],
    animations: fuseAnimations
})
export class PromoterFinancialsListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set promoterFinancialsList(promoterFinancialsList: any) {
        this.dataSource = new MatTableDataSource(promoterFinancialsList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'fiscalYear', 'turnover', 'pat','netWorth', 'overAllRating'
    ];

    selectedFinancials: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedPromoterFinancials.next({});
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
     * onSelect()
     * @param selectedFinancials
     */
    onSelect(selectedFinancials: any): void {
        this.selectedFinancials = selectedFinancials;
        this._service.selectedPromoterFinancials.next(this.selectedFinancials);
    }
}
