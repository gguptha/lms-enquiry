import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { BehaviorSubject } from 'rxjs';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-lfa-list',
    templateUrl: './lfaList.component.html',
    styleUrls: ['./lfaList.component.scss'],
    animations: fuseAnimations
})
export class LFAListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set lfaList(lfaList: any) {
        this.dataSource = new MatTableDataSource(lfaList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'advisor', 'bpCode','name', 'dateOfAppointment', 'contractPeriodFrom', 'contractPeriodTo', 'contactNumber', 'email'
    ];

    selectedLFA: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedLFA.next({});
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
    onSelect(lfa: any): void {
        console.log(lfa);
        this.selectedLFA = lfa;
        this._service.selectedLFA.next(this.selectedLFA);
    }

    /**
     * getAdvisor()
     * @param lfa
     */
    getAdvisor(lfa: any): string {
        // if lfa.advisor == xxxx
        return 'LFA';
    }    
}
