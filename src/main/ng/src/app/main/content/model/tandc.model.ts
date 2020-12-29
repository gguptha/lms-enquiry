export class TandCModel
{
    id: string;
    documentType: string;
    documentTitle: string;
    communication: string;
    borrowerRequestLetterDate: Date;
    dateofIssueofAmendedSanctionLetter: Date;
    remarks: string;

    /**
     * constructor();
     * @param _tandc 
     */
    constructor(_tandc: any)
    {
        this.id = _tandc.id || '';
        this.documentType = _tandc.documentType || '';
        this.documentTitle = _tandc.documentTitle || '';
        this.communication = _tandc.communication || '';
        this.borrowerRequestLetterDate = _tandc.borrowerRequestLetterDate || undefined;
        this.dateofIssueofAmendedSanctionLetter = _tandc.dateofIssueofAmendedSanctionLetter || undefined;
        this.remarks = _tandc.remarks || '';
    }
}
 