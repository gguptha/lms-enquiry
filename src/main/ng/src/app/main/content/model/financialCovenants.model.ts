export class FinancialCovenantsModel {

    id: string;
    financialCovenantType: string;
    financialYear: number;
    debtEquityRatio: number;
    dscr: number;
    tolTnw: number;
    remarksForDeviation: string;

    /**
     * constructor()
     * @param _financialCovenants 
     */
    constructor(_financialCovenants: any)
    {
        this.id = _financialCovenants.id || '';
        this.financialCovenantType = _financialCovenants.financialCovenantType || '';
        this.financialYear = _financialCovenants.financialYear || 0;
        this.debtEquityRatio = _financialCovenants.debtEquityRatio || 0;
        this.dscr = _financialCovenants.dscr || 0;
        this.tolTnw = _financialCovenants.tolTnw || 0;
        this.remarksForDeviation = _financialCovenants.remarksForDeviation || '';
    }
}
 