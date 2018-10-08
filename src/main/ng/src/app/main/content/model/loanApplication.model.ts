export class LoanApplicationModel {

    assistanceType: string;
    createdOn: Date;
    enquiryNo: EnquiryNumber;
    equity: number;
    expectedInterestRate: number;
    expectedSubDebt: number;
    financingType: string;
    id: string;
    loanApplicant: string;
    loanClass: number;
    loanPurpose: number;
    leadFIName: string;
    leadFILoanAmount: number;
    pfsDebtAmount: number;
    pfsSubDebtAmount: number;
    projectCapacity: number;
    projectCost: number;
    projectDebtAmount: number;
    projectDistrict: string;
    projectLocationState: string;
    projectType: number;
    promoterName: string;
    promoterAreaOfBusinessNature: string;
    promoterKeyDirector: string;
    promoterNetWorthAmount: number;
    promoterPATAmount: number;
    rating: string;
    tenorYear: number;
    tenorMonth: number;
    scheduledCOD: Date;

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
        this.equity = _loanApplication.equity;
        this.expectedInterestRate = _loanApplication.expectedInterestRate;
        this.expectedSubDebt = _loanApplication.expectedSubDebt;
        this.financingType = _loanApplication.financingType || '';
        this.id = _loanApplication.id || '';
        this.loanApplicant = _loanApplication.loanApplicant;
        this.loanClass = _loanApplication.loanClass || 0;
        this.loanPurpose = _loanApplication.loanPurpose || '';
        this.leadFIName = _loanApplication.leadFIName;
        this.leadFILoanAmount = _loanApplication.leadFILoanAmount;
        this.pfsDebtAmount = _loanApplication.pfsDebtAmount || 0;
        this.pfsSubDebtAmount = _loanApplication.pfsSubDebtAmount || 0;
        this.projectCapacity = _loanApplication.projectCapacity;
        this.projectCost = _loanApplication.projectCost;
        this.projectDebtAmount = _loanApplication.projectDebtAmount;
        this.projectDistrict = _loanApplication.projectDistrict;
        this.projectLocationState = _loanApplication.projectLocationState || '';
        this.projectType = _loanApplication.projectType || 0;
        this.promoterName = _loanApplication.promoterName || '';
        this.promoterAreaOfBusinessNature = _loanApplication.promoterAreaOfBusinessNature || '';
        this.promoterKeyDirector = _loanApplication.promoterKeyDirector;
        this.promoterNetWorthAmount = _loanApplication.promoterNetWorthAmount;
        this.promoterPATAmount = _loanApplication.promoterPATAmount;
        this.rating = _loanApplication.rating;
        this.tenorYear = _loanApplication.tenorYear || 0;
        this.tenorMonth = _loanApplication.tenorMonth || 0;
        this.scheduledCOD = _loanApplication.scheduledCOD || '';
        
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
