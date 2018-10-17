import { PartnerModel } from './partner.model';
import { LoanApplicationModel } from './loanApplication.model';

export class LoanApplicationResourceModel {

    loanApplication: LoanApplicationModel;
    partner: PartnerModel;

    constructor(_loanApplicationResource: any) {
        this.loanApplication = new LoanApplicationModel(_loanApplicationResource.loanApplication);
        this.partner = new PartnerModel(_loanApplicationResource.partner);
    }
}
