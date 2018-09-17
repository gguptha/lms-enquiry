export class LoanApplication {

    assistanceType: string;
    createdOn: Date;
    financingType: string;
    loanClass: string;
    projectCapacity: number;
    projectCost: number;
    projectLocationState: string;
    projectType: number;
    

    constructor(_loanApplication: any) {
        // Initialize the object.
        this.assistanceType = _loanApplication.assistanceType || '';
        this.createdOn = _loanApplication.createdOn || '';
        this.financingType = _loanApplication.financingType || '';
        this.loanClass = _loanApplication.loanClass || '';
        this.projectCapacity = _loanApplication.projectCapacity;
        this.projectCost = _loanApplication.projectCost;
        this.projectLocationState = _loanApplication.projectLocationState || '';
        this.projectType = _loanApplication.projectType;
    }
}
