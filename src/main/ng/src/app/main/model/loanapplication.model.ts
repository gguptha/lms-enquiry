export class LoanApplication {

    assistanceType: string;
    createdOn: Date;
    enquiryNo: EnquiryNumber;
    financingType: string;
    id: string;
    loanApplicant: string;
    loanClass: number;
    loanPurpose: number;
    pfsDebtAmount: number;
    projectCapacity: number;
    projectCost: number;
    projectLocationState: string;
    projectType: number;
    tenorYear: number;
    tenorMonth: number;

    _links: Object;
    
    /**
     * constructor()
     * Initialize the object.
     * @param _loanApplication 
     */
    constructor(_loanApplication: any) {
        this.assistanceType = _loanApplication.assistanceType || '';
        this.createdOn = _loanApplication.createdOn || '';
        this.enquiryNo = _loanApplication.enquiryNo;
        this.financingType = _loanApplication.financingType || '';
        this.id = _loanApplication.id || '';
        this.loanApplicant = _loanApplication.loanApplicant;
        this.loanClass = _loanApplication.loanClass || 0;
        this.loanPurpose = _loanApplication.loanPurpose || '';
        this.pfsDebtAmount = _loanApplication.pfsDebtAmount || 0;
        this.projectCapacity = _loanApplication.projectCapacity;
        this.projectCost = _loanApplication.projectCost;
        this.projectLocationState = _loanApplication.projectLocationState || '';
        this.projectType = _loanApplication.projectType || 0;
        this.tenorYear = _loanApplication.tenorYear || 0;
        this.tenorMonth = _loanApplication.tenorMonth || 0;

        this._links = _loanApplication._links;
    }

    /**
     * loanClass()
     * Returns the string value of the loan class.
     */
    get loanClassDescription(): string {
        switch (this.loanClass) {
            case 0: return '';
            case 1: return 'Power';
            case 2: return 'Railways';
            case 3: return 'Urban Infrastructure';
            case 4: return 'Roads';
            case 5: return 'Ports';
            case 6: return 'Oil & Gas';
            case 7: return 'Corporates';
            case 8: return 'Infrastructure';
            case 9: return 'Others';
        }
    }

    /**
     * projectType()
     * Returns the string value of the project type.
     */
    get projectTypeDescription(): string {
        switch (this.projectType) {
            case  0: return '';
            case  1: return 'Thermal - Coal';
            case  2: return 'Thermal - Ignite';
            case  3: return 'Thermal - Gas';
            case  4: return 'Hydro';
            case  5: return 'Renewable - Solar';
            case  6: return 'Renewable - Wind';
            case  7: return 'Renewable - Biomass';
            case  8: return 'Renewable - Small Hydro';
            case  9: return 'EPC Contractors';
            case 10: return 'Coal Mining';
            case 11: return 'Power Transmission';
            case 12: return 'Railway Siding';
            case 13: return 'Ports';
            case 14: return 'Corporate';
            case 15: return 'Renovation & Modernisation';
            case 16: return 'Others';
        }
    }

    /**
     * assistanceTypeDescription()
     * Returns the string value of assistance type.
     */
    get assistanceTypeDescription(): string {
        switch (this.assistanceType) {
            case 'E': return 'Equity';
            case 'D': return 'Debt';
        }
    }
}

class EnquiryNumber {
    id: number;
}
