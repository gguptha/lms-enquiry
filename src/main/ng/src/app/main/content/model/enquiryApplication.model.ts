export class EnquiryApplicationModel {

    id: string;

    assistanceType: string;
    busPartnerNumber: string;
    createdOn: Date;
    createdByUserName:string;
    enquiryNumber: string;
    functionalStatus: number;
    loanAmount: number;
    loanClass: string;
    loanContractId: string;
    projectCapacity: number;
    projectCapacityUnit: string;
    projectCost: number;
    projectLocationState: string;
    projectName: string;
    projectType: string;
    technicalStatusDescription:string;
    rating: string;
    borrowerName: string;

    /**
     * constructor()
     * Initialize the object.
     * @param _enquiryApplication
     */
    constructor(_enquiryApplication: any) {
        this.id = _enquiryApplication.loanApplication.id || '';

        this.assistanceType = _enquiryApplication.loanApplication.assistanceType;
        this.busPartnerNumber = _enquiryApplication.loanApplication.busPartnerNumber;
        this.createdOn = _enquiryApplication.loanApplication.createdOn;
        this.createdByUserName = _enquiryApplication.loanApplication.createdByUserName;
        this.enquiryNumber = _enquiryApplication.loanApplication.enquiryNo.id;
        this.functionalStatus = _enquiryApplication.loanApplication.functionalStatus;
        this.loanAmount = _enquiryApplication.loanApplication.pfsDebtAmount;
        this.loanClass = _enquiryApplication.loanApplication.loanClass;
        this.loanContractId = _enquiryApplication.loanApplication.loanContractId;
        this.projectCapacity = _enquiryApplication.loanApplication.projectCapacity;
        this.projectCapacityUnit = _enquiryApplication.loanApplication.projectCapacityUnit;
        this.projectCost = _enquiryApplication.loanApplication.projectCost;
        this.projectLocationState = _enquiryApplication.loanApplication.projectLocationState;
        this.projectName = _enquiryApplication.loanApplication.projectName;
        this.projectType = _enquiryApplication.loanApplication.projectType;
        this.technicalStatusDescription = _enquiryApplication.loanApplication.technicalStatusDescription;
        this.rating = _enquiryApplication.loanApplication.rating;
        this.borrowerName = _enquiryApplication.partner.partyName1;
    }

    /**
     * loanClassDescription()
     * Returns the string value of the loan class.
     */
    get loanClassDescription(): string {
        switch (this.loanClass) {
            case '0' : return '';
            case '001': return 'Power';
            case '002': return 'Railways';
            case '003': return 'Urban Infrastructure';
            case '004': return 'Roads';
            case '005': return 'Ports';
            case '006': return 'Oil & Gas';
            case '007': return 'Corporates';
            case '008': return 'Infrastructure';
            case '009': return 'Others';
        }
    }

    /**
     * projectTypeDescription()
     * Returns the string value of the project type.
     */
    get projectTypeDescription(): string {
        switch (this.projectType) {
            case  '0': return '';
            case '01': return 'Thermal - Coal';
            case '02': return 'Thermal - Ignite';
            case '03': return 'Thermal - Gas';
            case '04': return 'Hydro';
            case '05': return 'Renewable - Solar';
            case '06': return 'Renewable - Wind';
            case '07': return 'Renewable - Biomass';
            case '08': return 'Renewable - Small Hydro';
            case '09': return 'EPC Contractors';
            case '10': return 'Coal Mining';
            case '11': return 'Power Transmission';
            case '12': return 'Railway Siding';
            case '13': return 'Ports';
            case '14': return 'Corporate';
            case '15': return 'Renovation & Modernisation';
            case '16': return 'Others';
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

    /**
     * functionalStatusDescription()
     * Returns the string value of the functional status.
     */
    get functionalStatusDescription(): string {
        switch (this.functionalStatus) {
            case 0: return '';
            case 1: return 'Enquiry Stage';
            case 2: return 'ICC ApprovalStage';
            case 3: return 'Apprisal Stage';
            case 4: return 'Board Approval Stage';
            case 5: return 'Loan Documenation Stage';
            case 6: return 'Loan Disbursement Stage';
            case 7: return 'Approved';
            case 8: return 'Rejected';
            case 9: return 'Cancelled';
        }
    }
}
