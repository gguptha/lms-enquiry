import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { LoanMonitoringService } from '../../loanMonitoring.service';

@Component({
    selector: 'fuse-security-compliance-list',
    templateUrl: './securityComplianceList.component.html',
    styleUrls: ['./securityComplianceList.component.scss'],
    animations: fuseAnimations
})
export class SecurityComplianceListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    @ViewChild(MatSort) sort: MatSort;

    @Input()
    set securityComplianceList(securityComplianceList: any) {
        this.dataSource = new MatTableDataSource(securityComplianceList);
        this.dataSource.sort = this.sort
    }

    displayedColumns = [
        'particulars', 'qty', 'faceValue','percentage', 'applicability', 'timelines', 'dateOfCreation', 'validityDate', 'value',
            'securityPerfectionDate', 'remarks'
    ];

    selectedSecurityCompliance: any;

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
     * onSelect()
     * @param securityCompliance
     */
    onSelect(securityCompliance: any): void {
        this.selectedSecurityCompliance = securityCompliance;
        this._service.selectedSecurityCompliance.next(this.selectedSecurityCompliance);
    }

    /**
     * getParticulars()
     * @param collateralObjectTypeCode 
     */
    getParticulars(collateralObjectTypeCode: any): string {
        return LoanMonitoringConstants.collateralObjectTypes.filter(f => f.code === collateralObjectTypeCode)[0].value;
    }

    /**
     * getApplicability()
     * @param applicabilityCode 
     */
    getApplicability(applicabilityCode: any): string {
        return LoanMonitoringConstants.applicability.filter(f => f.code === applicabilityCode)[0].value;
    }  
}
