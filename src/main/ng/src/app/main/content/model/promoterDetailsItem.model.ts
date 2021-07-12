export class PromoterDetailsItemModel {

    id: string;
    serialNumber: number;
    shareHoldingCompany: string;
    paidupCapitalEquitySanction: number;
    equityLinkInstrumentSanction: number;
    paidupCapitalEquityCurrent: number;
    equityLinkInstrumentCurrent: number;

    /**
     * constructor()
     * @param _promoterDetailsItem
     */
    constructor(_promoterDetailsItem: any) {
        this.id = _promoterDetailsItem.id;
        this.serialNumber = _promoterDetailsItem.serialNumber || 0;
        this.shareHoldingCompany = _promoterDetailsItem.shareHoldingCompany || '';
        this.paidupCapitalEquitySanction = _promoterDetailsItem.paidupCapitalEquitySanction || 0;
        this.paidupCapitalEquityCurrent = _promoterDetailsItem.paidupCapitalEquityCurrent || 0;
        this.equityLinkInstrumentSanction = _promoterDetailsItem.equityLinkInstrumentSanction || 0;
        this.equityLinkInstrumentCurrent = _promoterDetailsItem.equityLinkInstrumentCurrent || 0;
    }
}
