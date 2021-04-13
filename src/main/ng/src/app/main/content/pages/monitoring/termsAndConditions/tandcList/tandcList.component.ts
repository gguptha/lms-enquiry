import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-tandc-list',
    templateUrl: './tandcList.component.html',
    styleUrls: ['./tandcList.component.scss'],
    animations: fuseAnimations
})
export class TandCListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set tandcList(tandcList: any) {
        this.dataSource = new MatTableDataSource(tandcList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'serialNumber', 'communication', 'remarks','borrowerRequestLetterDate', 'dateofIssueofAmendedSanctionLetter', 'documentType', 'download'
    ];

    selectedTandC: any;

    /**
     * constructor()
     */
    constructor(private _service: LoanMonitoringService) {
        this._service.selectedTandC.next({});
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
    onSelect(tandc: any): void {
        this.selectedTandC = tandc;
        this._service.selectedTandC.next(this.selectedTandC);
    }

    /**
     * getCommunication()
     * @param communication 
     */
    getCommunication(communication: string) {
        if (communication === '0')
            return 'Original';
        else if (communication === '1')
            return 'Amended';
        else
            return 'Others';
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
