import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-promoter-details-list',
    templateUrl: './promoterDetailsList.component.html',
    styleUrls: ['./promoterDetailsList.component.scss'],
    animations: fuseAnimations
})
export class PromoterDetailsItemListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set promoterDetailItemSet(promoterDetailItemSet: any) {
        this.dataSource = new MatTableDataSource(promoterDetailItemSet);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'shareHoldingCompany', 'paidupCapitalEquitySanction', 'paidupCapitalEquityCurrent','equityLinkInstrumentSanction', 'equityLinkInstrumentCurrent'
    ];

    selectedPromoterDetailsItem: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedPromoterDetailsItem.next({});
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
     * @param selectedPromoterDetailsItem
     */
    onSelect(selectedPromoterDetailsItem: any): void {
        this.selectedPromoterDetailsItem = selectedPromoterDetailsItem;
        this._service.selectedPromoterDetailsItem.next(this.selectedPromoterDetailsItem);
    }
}
