import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-operating-parameter-plf-list',
    templateUrl: './operatingParameterPLFList.component.html',
    styleUrls: ['./operatingParameterPLFList.component.scss'],
    animations: fuseAnimations
})
export class OperatingParameterPLFListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set operatingParameterPLFList(operatingParameterPLFList: any) {
        console.log('operatingParameterPLFList', operatingParameterPLFList);
        this.dataSource = new MatTableDataSource(operatingParameterPLFList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'year', 'actualYearlyAveragePlfCuf', 'remarks'
    ];

    selectedOperatingParameterPLF: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedOperatingParameterPLF.next({});
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
     * @param selectedOperatingParameterPLF
     */
    onSelect(selectedOperatingParameterPLF: any): void {
        this.selectedOperatingParameterPLF = selectedOperatingParameterPLF;
        this._service.selectedOperatingParameterPLF.next(this.selectedOperatingParameterPLF);
    }
}
