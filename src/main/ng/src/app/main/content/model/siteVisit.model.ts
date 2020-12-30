export class SiteVisitModel {

    id: string;
    serialNumber: number;
    actualCOD: Date;
    dateOfSiteVisit: Date;
    dateOfLendersMeet: Date;

    /**
     * constructor()
     * @param _siteVisit 
     */
    constructor(_siteVisit: any)
    {
        this.id = _siteVisit.id || '';
        this.serialNumber = _siteVisit.serialNumber || 0;
        this.actualCOD = _siteVisit.actualCOD || undefined;
        this.dateOfSiteVisit = _siteVisit.dateOfSiteVisit || undefined;
        this.dateOfLendersMeet = _siteVisit.dateOfLendersMeet || undefined;
    }
}
