import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-operating-parameter-list',
    templateUrl: './operatingParameterList.component.html',
    styleUrls: ['./operatingParameterList.component.scss'],
    animations: fuseAnimations
})
export class OperatingParameterListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set operatingParameterList(operatingParameterList: any) {
        console.log('operatingParameterList', operatingParameterList);
        this.dataSource = new MatTableDataSource(operatingParameterList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'month', 'year', 'exportNetGeneration', 'plfCufActual', 'applicableTariff', 'revenue', 'dateOfInvoice', 
            'dateOfPaymentReceipt', 'carbonDiOxideEmission', 'waterSaved'
    ];

    selectedOperatingParameter: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedOperatingParameter.next({});
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
     * @param selectedOperatingParameter
     */
    onSelect(selectedOperatingParameter: any): void {
        this.selectedOperatingParameter = selectedOperatingParameter;
        this._service.selectedOperatingParameter.next(this.selectedOperatingParameter);
    }
}
