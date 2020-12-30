import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-site-visit-list',
    templateUrl: './siteVisitList.component.html',
    styleUrls: ['./siteVisitList.component.scss'],
    animations: fuseAnimations
})
export class SiteVisitListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set siteVisitList(siteVisitList: any) {
        this.dataSource = new MatTableDataSource(siteVisitList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'actualCOD', 'dateOfSiteVisit','dateOfLendersMeet'
    ];

    selectedSiteVisit: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedSiteVisit.next({});
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
     * @param securityCompliance
     */
    onSelect(selectedSiteVisit: any): void {
        this.selectedSiteVisit = selectedSiteVisit;
        this._service.selectedSiteVisit.next(this.selectedSiteVisit);
    }
}
