import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-tra-statement-list',
    templateUrl: './traStatementList.component.html',
    styleUrls: ['./traStatementList.component.scss'],
    animations: fuseAnimations
})
export class TRAStatementListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set traStatementList(list: any) {
        this.dataSource = new MatTableDataSource(list);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'documentType', 'periodQuarter','periodYear', 'remarks', 'download'
    ];

    selectedTRAStatement: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedTRAStatement.next({});
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
     * @param enquiry
     */
    onSelect(traStatement: any): void {
        this.selectedTRAStatement = traStatement;
        this._service.selectedTRAStatement.next(this.selectedTRAStatement);
    }

    /**
     * getFileURL()
     * @param fileReference 
     */
    getFileURL(fileReference: string): string {
        return 'enquiry/api/download/' + fileReference;
    }

    /**
     * getDocumentType()
     * @param documentType 
     */
    getDocumentType(documentType: string): string {
        const filtered = LoanMonitoringConstants.documentTypes.filter(obj => obj.code === documentType);
        return filtered[0].value;    
    }
}
