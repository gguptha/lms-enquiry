import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { BehaviorSubject } from 'rxjs';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-lie-list',
    templateUrl: './lieList.component.html',
    styleUrls: ['./lieList.component.scss'],
    animations: fuseAnimations
})
export class LIEListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set lieList(lieList: any) {
        this.dataSource = new MatTableDataSource(lieList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'advisor', 'bpCode','name', 'dateOfAppointment', 'contractPeriodFrom', 'contractPeriodTo', 'contactNumber', 'email'
    ];

    selectedLIE: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedLIE.next({});
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
     * @param enquiry
     */
    onSelect(lie: any): void {
        console.log(lie);
        this.selectedLIE = lie;
        this._service.selectedLIE.next(this.selectedLIE);
    }

    /**
     * getAdvisor()
     * @param lie 
     */
    getAdvisor(lie: any): string {
        // if lie.advisor == xxxx
        return 'LIE';
    }
}
