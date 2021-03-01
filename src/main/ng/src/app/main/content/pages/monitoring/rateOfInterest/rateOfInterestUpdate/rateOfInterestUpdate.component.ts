import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../../loanMonitoring.service';
import { RateOfInterestModel } from 'app/main/content/model/rateOfInterest.model';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { MonitoringRegEx } from 'app/main/content/others/monitoring.regEx';

@Component({
    selector: 'fuse-rate-of-interest-update-dialog',
    templateUrl: './rateOfInterestUpdate.component.html',
    styleUrls: ['./rateOfInterestUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class RateOfInterestUpdateDialogComponent {

    dialogTitle = 'Rate Of Interest';

    selectedRateOfInterest: RateOfInterestModel ;

    rateOfInterestUpdateForm: FormGroup;
  
    particulars = LoanMonitoringConstants.particulars;

    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<RateOfInterestUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedRateOfInterest !== undefined) {
            this.selectedRateOfInterest = _dialogData.selectedRateOfInterest;
        }
        else {
            this.selectedRateOfInterest = new RateOfInterestModel({});
        }

        this.rateOfInterestUpdateForm = _formBuilder.group({
            particulars: [this.selectedRateOfInterest.particulars],
            sanctionPreCod: [this.selectedRateOfInterest.sanctionPreCod, [Validators.pattern(MonitoringRegEx.genericAmount)]],
            scheduledIfAny: [this.selectedRateOfInterest.scheduledIfAny],
            sanctionPostCod: [this.selectedRateOfInterest.sanctionPostCod, [Validators.pattern(MonitoringRegEx.genericAmount)]],
            presentRoi: [this.selectedRateOfInterest.presentRoi, [Validators.pattern(MonitoringRegEx.holdingPercentage)]],
            freeText: [this.selectedRateOfInterest.freeText],
        });
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.rateOfInterestUpdateForm.valid) {
            var rateOfInterest: RateOfInterestModel = new RateOfInterestModel(this.rateOfInterestUpdateForm.value);
            if (this._dialogData.operation === 'addRateOfInterest') {
                this._loanMonitoringService.saveRateOfInterest(rateOfInterest, this._dialogData.loanApplicationId).subscribe(() => {
                    this._matSnackBar.open('Rate Of Interest details added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedRateOfInterest.particulars = rateOfInterest.particulars;
                this.selectedRateOfInterest.sanctionPreCod = rateOfInterest.sanctionPreCod;
                this.selectedRateOfInterest.scheduledIfAny  = rateOfInterest.scheduledIfAny;
                this.selectedRateOfInterest.sanctionPostCod  = rateOfInterest.sanctionPostCod;
                this.selectedRateOfInterest.presentRoi  = rateOfInterest.presentRoi;
                this.selectedRateOfInterest.freeText  = rateOfInterest.freeText;
                this._loanMonitoringService.updateRateOfInterest(this.selectedRateOfInterest).subscribe(() => {
                    this._matSnackBar.open('Rate Of Interest details updated successfully.', 'OK', { duration: 7000 });
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
