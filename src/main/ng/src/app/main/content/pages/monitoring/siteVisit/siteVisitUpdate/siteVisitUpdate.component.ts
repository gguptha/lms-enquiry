import { Component, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { SiteVisitModel } from 'app/main/content/model/siteVisit.model';

@Component({
    selector: 'fuse-site-visit-update-dialog',
    templateUrl: './siteVisitUpdate.component.html',
    styleUrls: ['./siteVisitUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class SiteVisitUpdateDialogComponent {

    dialogTitle = 'Add New Site Visit Details';

    selectedSiteVisit: SiteVisitModel ;
    siteVisitUpdateForm: FormGroup;
  
    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<SiteVisitUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedSiteVisit !== undefined) {
            this.selectedSiteVisit = Object.assign({}, _dialogData.selectedSiteVisit);
            this.dialogTitle = 'Modify Site Visit Details';
        }
        else {
            this.selectedSiteVisit = new SiteVisitModel({});
        }

        this.siteVisitUpdateForm = _formBuilder.group({
            serialNumber: [this.selectedSiteVisit.serialNumber],
            actualCOD: [this.selectedSiteVisit.actualCOD || ''],
            dateOfSiteVisit: [this.selectedSiteVisit.dateOfSiteVisit || ''],
            dateOfLendersMeet: [this.selectedSiteVisit.dateOfLendersMeet || '']
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.siteVisitUpdateForm.valid) {
            var siteVisit: SiteVisitModel = new SiteVisitModel(this.siteVisitUpdateForm.value);
            var dt = new Date(siteVisit.actualCOD);
            siteVisit.actualCOD = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(siteVisit.dateOfSiteVisit);
            siteVisit.dateOfSiteVisit = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(siteVisit.dateOfLendersMeet);
            siteVisit.dateOfLendersMeet = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            if (this._dialogData.operation === 'addSiteVisit') {
                this._loanMonitoringService.saveSiteVisit(siteVisit, this._dialogData.loanApplicationId).subscribe(() => {
                    this._matSnackBar.open('Site Visit details added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedSiteVisit.serialNumber = siteVisit.serialNumber;
                this.selectedSiteVisit.actualCOD = siteVisit.actualCOD;
                this.selectedSiteVisit.dateOfLendersMeet  = siteVisit.dateOfLendersMeet;
                this.selectedSiteVisit.dateOfSiteVisit  = siteVisit.dateOfSiteVisit;
                this._loanMonitoringService.updateSiteVisit(this.selectedSiteVisit).subscribe(() => {
                    this._matSnackBar.open('Site Visit details updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });            
            }
        }
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close({ 'refresh': false });
    }
}
