export class LIEModel
{
    id: string;
    bpCode: string;
    name: string;
    dateOfAppointment: Date;
    contactPerson: string;
    contactNumber: string;
    contractPeriodFrom: Date;
    contractPeriodTo: Date;
    email: string;

    /**
     * constructor();
     * @param _lie 
     */
    constructor(_lie: any)
    {
        this.id = _lie.id || '';
        this.bpCode = _lie.bpCode || '';
        this.name = _lie.name || '';
        this.dateOfAppointment = _lie.dateOfAppointment || undefined;
        this.contactPerson = _lie.contactPerson || '';
        this.contactNumber = _lie.contactNumber || '';
        this.contractPeriodFrom = _lie.contractPeriodFrom || undefined;
        this.contractPeriodTo = _lie.contractPeriodTo || undefined;
        this.email = _lie.email;
    }
}
