export class LIEModel
{
    bpCode: string;
    name: string;
    dateOfAppointment: Date;
    contactPerson: string;
    contractPeriodFrom: Date;
    contractPeriodTo: Date;
    email: string;
    
    /**
     * constructor();
     * @param _lie 
     */
    constructor(_lie: any)
    {
        this.bpCode = _lie.bpCode || '';
        this.name = _lie.name || '';
        this.dateOfAppointment = _lie.dateOfAppointment || undefined;
        this.contactPerson = _lie.contactPerson || '';
        this.contractPeriodFrom = _lie.contractPeriodFrom || undefined;
        this.contractPeriodTo = _lie.contractPeriodTo || undefined;
        this.email = _lie.email;
    }
}
