import { PromoterDetailsItemModel } from "./promoterDetailsItem.model";

export class PromoterDetailsModel {

    id: string;
    dateOfChange: Date;
    groupExposure: number;

    promoterDetailsItemSet: PromoterDetailsItemModel[]; 

    /**
     * constructor()
     * @param _promoterDetails
     */
    constructor(_promoterDetails: any) {
        console.log('promoterDetails', _promoterDetails);
        this.id = _promoterDetails.id || '';
        this.dateOfChange = _promoterDetails.dateOfChange || new Date();
        this.groupExposure = _promoterDetails.groupExposure || 0;
        this.promoterDetailsItemSet = _promoterDetails.promoterDetailsItemList || new Array();
    }
}
