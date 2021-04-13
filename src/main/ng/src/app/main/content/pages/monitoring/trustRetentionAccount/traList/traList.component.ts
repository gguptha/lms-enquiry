import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-tra-list',
    templateUrl: './traList.component.html',
    styleUrls: ['./traList.component.scss'],
    animations: fuseAnimations
})
export class TRAListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set traList(traList: any) {
        this.dataSource = new MatTableDataSource(traList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'bankKey', 'traBankName','branch', 'address', 'beneficiaryName', 'ifscCode', 'accountNumber', 'contactName', 'typeOfAccount',
            'contactNumber', 'email', 'pfsAuthorisedPerson'
    ];

    selectedTRA: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedTRA.next({});
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
    onSelect(tra: any): void {
        console.log(tra);
        this.selectedTRA = tra;
        this._service.selectedTRA.next(this.selectedTRA);
    }
}
