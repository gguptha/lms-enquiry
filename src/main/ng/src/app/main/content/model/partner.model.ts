export class PartnerModel {

    addressLine1: string;
    addressLine2: string;
    city: string;
    contactNumber: string;
    contactPersonName: string;
    country: string;
    email: string;
    groupCompany: string;
    id: string;
    pan: string;
    partyName1: string;
    partyName2: string;
    partyRole: string;
    password: string;
    postalCode: string;
    state: string;
    street: string;
    userName: string;

    _links: Object;
    
    /**
     * constructor()
     * Initialize the object.
     * @param _partner 
     */
    constructor(_partner: any) {
        this.addressLine1 = _partner && _partner.addressLine1 || '';
        this.addressLine2 = _partner && _partner.addressLine2 || '';
        this.city = _partner && _partner.city || '';
        this.contactNumber = _partner && _partner.contactNumber || '';
        this.contactPersonName = _partner && _partner.contactPersonName || '';
        this.country = _partner && _partner.country || '';
        this.email = _partner && _partner.email || '';
        this.groupCompany = _partner && _partner.groupCompany || '';
        this.id = _partner && _partner.id || '';
        this.pan = _partner && _partner.pan || '';
        this.partyName1 = _partner && _partner.partyName1 || '';
        this.partyName2 = _partner && _partner.partyName2 || '';
        this.partyRole = _partner && _partner.partyRole || '';
        this.password = _partner && _partner.password || '';
        this.postalCode = _partner && _partner.postalCode || '';
        this.state = _partner && _partner.state || '';
        this.street = _partner && _partner.street || '';
        this.userName = _partner && _partner.userName || '';

        this._links = _partner && _partner._links || '';
    }
}
