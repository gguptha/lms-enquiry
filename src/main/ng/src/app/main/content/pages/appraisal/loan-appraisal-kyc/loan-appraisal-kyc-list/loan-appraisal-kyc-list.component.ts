import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanAppraisalService } from '../../loanAppraisal.service';
import { LoanAppraisalKYCUpdateComponent } from '../loan-appraisal-kyc-update/loan-appraisal-kyc-update.component';

@Component({
    selector: 'fuse-loan-appraisal-kyc-list',
    templateUrl: './loan-appraisal-kyc-list.component.html',
    styleUrls: ['./loan-appraisal-kyc-list.component.scss'],
    animations: fuseAnimations
})
export class LoanAppraisalKYCListComponent implements OnInit {

    dataSource: MatTableDataSource<any>;
    
    displayedColumns = [
        'serialNumber', 'kycType', 'dateOfCompletion', 'remarks', 'download'
    ];

    selectedKYC: any;

    _loanApplicationId: string;
    @Input() set loanApplicationId(value: string) {
        this._loanApplicationId = value;
        this.getLoanAppraisalKYCs();
    }

    /**
     * constructor()
     * @param _dialogRef 
     * @param _loanAppraisalService 
     */
    constructor(private _dialogRef: MatDialog, 
                private _loanAppraisalService: LoanAppraisalService) { 

        this.dataSource = new MatTableDataSource([]);
    }

    /**
     * ngOnInit()
     */
    ngOnInit() {
    }

    /**
     * openUpdateDialog()
     * @param operation 
     */
    openUpdateDialog(operation: string): void {
        // Open the dialog.
        var data = {
            'operation': operation,
            'loanApplicationId': this._loanApplicationId,
            'loanAppraisalKYC': {},
        };
        if (operation === 'modifyAppraisalKYC') {
            data.loanAppraisalKYC = this.selectedKYC;
        }
        const dialogRef = this._dialogRef.open(LoanAppraisalKYCUpdateComponent, {
            width: '750px',
            data: data
        });
        // Subscribe to the dialog close event to intercept the action taken.
        dialogRef.afterClosed().subscribe((result) => { 
            if (result.refresh) {
                this.getLoanAppraisalKYCs();
            }
        });
    }

    /**
     * getLoanAppraisalKYCs()
     */
     getLoanAppraisalKYCs(): void {
        this._loanAppraisalService.getLaonAppraisalKYCs(this._loanApplicationId).subscribe(data => {
            this.dataSource = new MatTableDataSource(data._embedded.loanAppraisalKYCs);
        });
    }

    /**
     * onRowSelect()
     * @param loanAppraisalKYC 
     */
    onRowSelect(loanAppraisalKYC: any): void {
        this.selectedKYC = loanAppraisalKYC;
    }

    /**
     * getFileURL()
     * @param fileReference 
     */
    getFileURL(fileReference: string): string {
        return 'enquiry/api/download/' + fileReference;
    }
}
