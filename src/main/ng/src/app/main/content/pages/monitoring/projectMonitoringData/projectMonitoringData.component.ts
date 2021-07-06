import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LoanEnquiryService } from '../../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from '../loanMonitoring.service';

@Component({
    selector: 'fuse-project-monitoring-data',
    templateUrl: './projectMonitoringData.component.html',
    styleUrls: ['./projectMonitoringData.component.scss'],
    animations: fuseAnimations
})
export class ProjectMonitoringDataComponent implements OnInit {

    loanApplicationId: string;

    projectMonitoringData: any;

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    selectedProjectMonitoringDataItem: any;

    displayedColumns = [
        'dateOfEntry', 'description', 'originalData','revisedData1', 'revisedData2', 'remarks'
    ];

    /**
     * constructor()
     */
    constructor(private _enquiryService: LoanEnquiryService, private _monitoringService: LoanMonitoringService) {
        this.loanApplicationId = _enquiryService.selectedLoanApplicationId.value;
        console.log(this.loanApplicationId);
        _monitoringService.getProjectMonitoringData(this.loanApplicationId).subscribe(data => {
            this.projectMonitoringData = data;
            this.dataSource = new MatTableDataSource(this.projectMonitoringData.projectMonitoringDataItems);
        });
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
     * @param onSelect
     */
    onSelect(projectMonitoringDataItem: any): void {
        this.selectedProjectMonitoringDataItem = projectMonitoringDataItem;
    }   
}
