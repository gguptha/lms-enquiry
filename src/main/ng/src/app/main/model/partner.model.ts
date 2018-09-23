export class Partner {

    addressLine1: string;
    addressLine2: string;
    city: string;
    contactNumber: string;
    contactPersonName: string;
    country: string;
    email: string;
    groupCompany: string;
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
        this.addressLine1 = _partner.addressLine1;
        this.addressLine2 = _partner.addressLine2;
        this.city = _partner.city;
        this.contactNumber = _partner.contactNumber;
        this.contactPersonName = _partner.contactPersonName;
        this.country = _partner.country;
        this.email = _partner.email;
        this.groupCompany = _partner.groupCompany;
        this.pan = _partner.pan;
        this.partyName1 = _partner.partyName1;
        this.partyName2 = _partner.partyName2;
        this.partyRole = _partner.partyRole;
        this.password = _partner.password;
        this.postalCode = _partner.postalCode;
        this.state = _partner.state;
        this.street = _partner.street;
        this.userName = _partner.userName;

        this._links = _partner._links;
    }
}
