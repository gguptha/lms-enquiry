import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-financial-covenants-list',
    templateUrl: './financialCovenantsList.component.html',
    styleUrls: ['./financialCovenantsList.component.scss'],
    animations: fuseAnimations
})
export class FinancialCovenantsListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set financialCovenantsList(financialCovenantsList: any) {
        this.dataSource = new MatTableDataSource(financialCovenantsList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'financialCovenantType', 'financialYear', 'debtEquityRatio','dscr', 'tolTnw', 'remarksForDeviation'
    ];

    selectedFinancialCovenants: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedFinancialCovenants.next({});
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
     * @param selectedFinancialCovenants
     */
    onSelect(selectedFinancialCovenants: any): void {
        this.selectedFinancialCovenants = selectedFinancialCovenants;
        this._service.selectedFinancialCovenants.next(this.selectedFinancialCovenants);
    }
}
