import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatDialog } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { EnquiryAlertsService } from '../enquiryAlerts.service';
import { ProductModel } from '../../../../model/product.model';
import { PartnerModel } from '../../../../model/partner.model';
import { AppService } from '../../../../../../app.service';
import { MessageDialogComponent } from '../../../../components/messageDialog/messageDialog.component';

@Component({
    selector: 'fuse-enquiry-approval-dialog',
    templateUrl: './enquiryApproval.component.html',
    styleUrls: ['./enquiryApproval.component.scss'],
    animations: fuseAnimations,
    encapsulation: ViewEncapsulation.None
})
export class EnquiryApprovalDialogComponent implements OnInit {

    dialogTitle = 'Take Enquiry Further';

    loanApplication: LoanApplicationModel;
    partner: PartnerModel;

    productCode: string;

    products: ProductModel[];

    /**
     * constructor()
     * @param _dialogRef 
     * @param _datePipe 
     */
    constructor(private _dialogRef: MatDialogRef<EnquiryApprovalDialogComponent>, @Inject(MAT_DIALOG_DATA) _data: any,
        private _service: EnquiryAlertsService, private _appService: AppService, private matSnackBar: MatSnackBar,
        private _messageDialog: MatDialog) {

        this.loanApplication = _data.loanApplication;
        this.partner = _data.partner;

        this._service.getProducts().subscribe((response) => {
            this.products = new Array<ProductModel>();
            response._embedded.products.map((product) => {
                this.products.push(new ProductModel(product));
            });
        });
    }

    /**
     * ngOnInit()
     */
    ngOnInit(): void {
    }

    /**
     * approve()
     */
    approve(): void {
        if (this.productCode !== undefined) {

            // Display alert message and then approve the loan application.
            const messageDialog = this._messageDialog.open(MessageDialogComponent, {
                width: '400px',
                data: {
                    message: 'Laon application will be submitted to the SAP loans management system and no further ' +
                        'updates are possible via the enquiry portal. However you can see the latest status of the loan.'
                }
            });
            messageDialog.afterClosed().subscribe((dresponse) => {
                this.loanApplication.userBPNumber = this._appService.currentUser.sapBPNumber;
                this.loanApplication.productCode = this.productCode;
                this._service.approveLoanApplication(this.loanApplication, this.partner).subscribe(
                    (response) => {
                        this._dialogRef.close({ action: 'Approved' });
                    },
                    (errorResponse) => {
                        this.matSnackBar.open('There is a problem while submitting to SAP. We are notified & working on it. ' +
                            'Please try again later.', 'OK', { duration: 7000 });
                    }
                );
            });
        }
    }

    /**
     * cancel()
     */
    cancel(): void {
        this._dialogRef.close({ action: 'Cancel' });
    }
}
