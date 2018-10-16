export class UserModel {

    id: string;
    partyRole: string;
    userName: string;

    /**
     * constructor()
     * @param _user 
     */
    constructor(private _user: any) {
        this.id = _user.id || '';
        this.partyRole = _user.partyRole || '';
        this.userName = _user.userName || '';
    }
}
