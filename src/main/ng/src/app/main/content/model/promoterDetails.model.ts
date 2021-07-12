import { PromoterDetailsItemModel } from "./promoterDetailsItem.model";

export class PromoterDetailsModel {

    id: string;
    dateOfChange: Date;
    groupExposure: number;

    promoterDetailItemSet: PromoterDetailsItemModel[]; 

    /**
     * constructor()
     * @param _promoterDetails
     */
    constructor(_promoterDetails: any) {
        this.id = _promoterDetails.id;
        this.dateOfChange = _promoterDetails.dateOfChange || new Date();
        this.groupExposure = _promoterDetails.groupExposure || 0;
        this.promoterDetailItemSet = _promoterDetails.promoterDetailItemList || new Array();
    }
}
