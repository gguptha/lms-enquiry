import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { fuseAnimations } from '@fuse/animations';
import { LoanApplicationModel } from '../../../../model/loanApplication.model';
import { EnquiryAlertsService } from '../enquiryAlerts.service';
import { ProductModel } from '../../../../model/product.model';
import { PartnerModel } from '../../../../model/partner.model';
import { AppService } from '../../../../../../app.service';

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
            private _service: EnquiryAlertsService, private _appService: AppService) 
    {
        this.loanApplication = _data.loanApplication;
        this.partner = _data.partner;

        this._service.getProducts().subscribe((response) =>  {
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
     * 
     */
    approve(): void {
        console.log(this.loanApplication, this.partner);
        console.log(this.productCode);
        if (this.productCode !== undefined) {
            console.log('saving data');
            console.log('this._appService.currentUser', this._appService.currentUser);
            this.loanApplication.userBPNumber = this._appService.currentUser.sapBPNumber;
            this.loanApplication.productCode = this.productCode;
            this._service.approveLoanApplication(this.loanApplication, this.partner).subscribe(() => {
                this._dialogRef.close({ action: 'Approved' });
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
