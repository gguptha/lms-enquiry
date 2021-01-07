export class PromoterFinancialsModel {

    id: string;
    fiscalYear: number;
    turnover: number;
    pat: number;
    netWorth: number;
    dateOfExternalRating: Date;
    nextDueDateOfExternalRating: Date;
    overAllRating: string;

    /**
     * constructor()
     * @param _promoterFinancialsDetails 
     */
    constructor(_promoterFinancialsDetails: any)
    {
        this.id = _promoterFinancialsDetails.id || '';
        this.fiscalYear = _promoterFinancialsDetails.fiscalYear || 0;
        this.turnover = _promoterFinancialsDetails.turnover || 0;
        this.pat = _promoterFinancialsDetails.pat || 0;
        this.netWorth = _promoterFinancialsDetails.netWorth || 0;
        this.dateOfExternalRating = _promoterFinancialsDetails.dateOfExternalRating || undefined;
        this.nextDueDateOfExternalRating = _promoterFinancialsDetails.nextDueDateOfExternalRating || undefined;
        this.overAllRating = _promoterFinancialsDetails.overAllRating || '';
    }
}
