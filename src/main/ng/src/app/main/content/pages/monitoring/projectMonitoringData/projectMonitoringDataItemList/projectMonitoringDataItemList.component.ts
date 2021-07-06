import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatDialog } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanEnquiryService } from '../../../enquiry/enquiryApplication.service';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { ProjectMonitoringDataItemUpdateComponent } from '../projectMonitoringDataItemUpdate/projectMonitoringDataItemUpdate.component';

@Component({
    selector: 'fuse-project-monitoring-data-item-list',
    templateUrl: './projectMonitoringDataItemList.component.html',
    styleUrls: ['./projectMonitoringDataItemList.component.scss'],
    animations: fuseAnimations
})
export class ProjectMonitoringDataItemListComponent implements OnInit {

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
    constructor(private _enquiryService: LoanEnquiryService, private _monitoringService: LoanMonitoringService, private _dialog: MatDialog) {
        this.loanApplicationId = _enquiryService.selectedLoanApplicationId.value;
        console.log(this.loanApplicationId);
        _monitoringService.getProjectMonitoringData(this.loanApplicationId).subscribe(data => {
            if (data === null) {
                _monitoringService.saveProjectMonitoringData(this.loanApplicationId).subscribe(response => {
                    this.projectMonitoringData = response;
                    this.dataSource = new MatTableDataSource(this.projectMonitoringData.projectMonitoringDataItems);
                    console.log(this.projectMonitoringData, 'this.projectMonitoringData');
                });
            }
            else {
                this.projectMonitoringData = data;
                this.dataSource = new MatTableDataSource(this.projectMonitoringData.projectMonitoringDataItems);
            }
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
    
    /**
     * updateProjectMonitoringData()
     */
    updateProjectMonitoringData(): void {
        // Open the dialog.
        const dialogRef = this._dialog.open(ProjectMonitoringDataItemUpdateComponent, {
            panelClass: 'fuse-project-monitoring-data-item-update-dialog',
            width: '850px',
            data: {
                loanApplicationId: this.loanApplicationId,
                selectedProjectMonitoringDataItem: this.selectedProjectMonitoringDataItem
            }
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this._monitoringService.getProjectMonitoringData(this.loanApplicationId).subscribe(response => {
                    this.projectMonitoringData = response;
                    this.dataSource = new MatTableDataSource(this.projectMonitoringData.projectMonitoringDataItems);
                });
            }
        });  
    }

    /**
     * getHistory()
     */
    getHistory(): void {

    }
}
