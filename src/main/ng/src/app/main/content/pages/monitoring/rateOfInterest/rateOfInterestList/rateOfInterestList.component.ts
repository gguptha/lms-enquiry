import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-rate-of-interest-list',
    templateUrl: './rateOfInterestList.component.html',
    styleUrls: ['./rateOfInterestList.component.scss'],
    animations: fuseAnimations
})
export class RateOfInterestListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set rateOfInterestList(rateOfInterestList: any) {
        this.dataSource = new MatTableDataSource(rateOfInterestList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'particulars', 'scheduledIfAny', 'sanctionPreCod','sanctionPostCod', 'presentRoi', 'freeText'
    ];

    selectedRateOfInterest: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedRateOfInterest.next({});
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
     * @param selectedRateOfInterest
     */
    onSelect(selectedRateOfInterest: any): void {
        this.selectedRateOfInterest = selectedRateOfInterest;
        this._service.selectedRateOfInterest.next(this.selectedRateOfInterest);
    }
}
