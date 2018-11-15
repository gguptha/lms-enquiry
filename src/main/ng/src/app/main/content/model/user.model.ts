export class UserModel 
{
    firstName: string; // partyName1
    lastName: string; // partyName2
    email: string;
    mobile: string;
    password: string;
    sapBPNumber: string;
    role: string;
    status: boolean;

    constructor(user: any)
    {
        if (user.firstName === undefined) {
            this.firstName = user.firstname;
        }
        else {
            this.firstName = user.firstName;
        }
        if (user.lastName === undefined) {
            this.lastName = user.lastname;
        }
        else {
            this.lastName = user.lastName;
        }
        this.email = user.email;
        this.mobile = user.mobile;
        this.password = user.password;
        this.sapBPNumber = user.sapBPNumber;
        this.role = user.role;
        this.status = user.status;
    }
}
