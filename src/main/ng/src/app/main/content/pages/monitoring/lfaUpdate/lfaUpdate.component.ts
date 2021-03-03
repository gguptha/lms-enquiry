import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { fuseAnimations } from '@fuse/animations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { LoanMonitoringService } from '../loanMonitoring.service';
import { LFAModel } from 'app/main/content/model/lfa.model';
import { LoanMonitoringConstants } from 'app/main/content/model/loanMonitoringConstants';
import { EnquiryApplicationRegEx } from 'app/main/content/others/enquiryApplication.regEx';
import { PartnerModel } from 'app/main/content/model/partner.model';

@Component({
    selector: 'fuse-lfa-update-dialog',
    templateUrl: './lfaUpdate.component.html',
    styleUrls: ['./lfaUpdate.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class LFAUpdateDialogComponent {

    dialogTitle = 'Add LFA';

    selectedLFA: LFAModel;

    lfaUpdateForm: FormGroup;

    //businessPartnerRoles = LoanMonitoringConstants.businessPartnerRoles;
    partners: PartnerModel[] = new Array();
    selectedPartnerName: string;
    
    /**
     * constructor()
     * @param _formBuilder 
     * @param _loanMonitoringService 
     * @param _dialogRef 
     * @param _dialogData 
     * @param _matSnackBar 
     */
    constructor(_formBuilder: FormBuilder, private _loanMonitoringService: LoanMonitoringService,
        public _dialogRef: MatDialogRef<LFAUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) public _dialogData: any,
        private _matSnackBar: MatSnackBar) {

        // Fetch selected user details from the dialog's data attribute.
        if (_dialogData.selectedLFA !== undefined) {
            this.selectedLFA = _dialogData.selectedLFA;
        }
        else {
            this.selectedLFA = new LFAModel({});
        }

        this.lfaUpdateForm = _formBuilder.group({
            bpCode: [this.selectedLFA.bpCode],
            name: [this.selectedLFA.name || ''],
            dateOfAppointment: [this.selectedLFA.dateOfAppointment || ''],
            contactPerson: [this.selectedLFA.contactPerson],
            contractPeriodFrom: [this.selectedLFA.contractPeriodFrom || ''],
            contractPeriodTo: [this.selectedLFA.contractPeriodTo || ''],
            email: [this.selectedLFA.email, [Validators.pattern(EnquiryApplicationRegEx.email)]]
        });

        _loanMonitoringService.getLFAs().subscribe(response => {
            response.forEach(element => {
                this.partners.push(new PartnerModel(element));
            });
        })    
    }

    /**
     * submit()
     */
    submit(): void {
        if (this.lfaUpdateForm.valid) {
            // To solve the utc time zone issue
            var lfa: LFAModel = new LFAModel(this.lfaUpdateForm.value);
            var dt = new Date(lfa.dateOfAppointment);
            lfa.dateOfAppointment = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(lfa.contractPeriodFrom);
            lfa.contractPeriodFrom = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));
            dt = new Date(lfa.contractPeriodTo);
            lfa.contractPeriodTo = new Date(Date.UTC(dt.getFullYear(), dt.getMonth(), dt.getDate()));

            if (this._dialogData.operation === 'addLFA') {
                this._loanMonitoringService.saveLFA(lfa, this._dialogData.loanApplicationId).subscribe(() => {
                    this._matSnackBar.open('LFA added successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });
            }
            else {
                this.selectedLFA.bpCode  = lfa.bpCode;
                this.selectedLFA.name = lfa.name;
                this.selectedLFA.dateOfAppointment = lfa.dateOfAppointment;
                this.selectedLFA.contactPerson = lfa.contactPerson;
                this.selectedLFA.contractPeriodFrom = lfa.contractPeriodFrom;
                this.selectedLFA.contractPeriodTo = lfa.contractPeriodTo;
                this.selectedLFA.email = lfa.email;
                this._loanMonitoringService.updateLFA(this.selectedLFA).subscribe(() => {
                    this._matSnackBar.open('LFA updated successfully.', 'OK', { duration: 7000 });
                    this._dialogRef.close({ 'refresh': true });
                });            
            }
        }
    }


    /**
     * getBPDescription()
     * @param bpCode 
     */
    getBPDescription(bpCode: any): string {
        return bpCode.RoleCode + ' - ' + bpCode.RoleDescription;
    }

    /**
     * onPartnerSelect()
     * @param event 
     */
    onPartnerSelect(partner: PartnerModel): void {
        this.selectedPartnerName = partner.partyName1 + ' ' + partner.partyName2;
    }

    /**
     * closeClick()
     */
    closeClick(): void {
        this._dialogRef.close({ 'refresh': false });
    }

    /**
     * getPartyNumberAndName()
     * @param party 
     */
    getPartyNumberAndName(party: any): string {
        return party.partyNumber + " - " + party.partyName1 + ' ' + party.partyName2;
    }
}
