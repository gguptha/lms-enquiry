export class RateOfInterestModel {

    id: string;
    particulars: string;
    scheduledIfAny: string;
    sanctionPreCod: number;
    sanctionPostCod: number;
    presentRoi: number;
    freeText: string;

    /**
     * constructor()
     * @param _rateOfInterest 
     */
    constructor(_rateOfInterest: any)
    {
        this.id = _rateOfInterest.id || '';
        this.particulars = _rateOfInterest.particulars || '';
        this.scheduledIfAny = _rateOfInterest.scheduledIfAny || '';
        this.freeText = _rateOfInterest.freeText || '';
        this.sanctionPreCod = _rateOfInterest.sanctionPreCod || 0;
        this.sanctionPostCod = _rateOfInterest.sanctionPostCod || 0;
        this.presentRoi = _rateOfInterest.presentRoi || 0;
    }
}
