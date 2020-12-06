export class LIEReportAndFeeModel
{
    reportType: string;
    dateOfReceipt: Date;
    invoiceDate: Date;
    invoiceNo: string;
    feeAmount: number;
    statusOfFeeReceipt: string;
    statusOfFeePaid: string;
    documentTitle: string;
    nextReportDate: Date;
    
    /**
     * constructor();
     * @param _lieReportAndFee 
     */
    constructor(_lieReportAndFee: any)
    {
        this.reportType = _lieReportAndFee.reportType || '';
        this.dateOfReceipt = _lieReportAndFee.dateOfReceipt || undefined;
        this.invoiceDate = _lieReportAndFee.invoiceDate || undefined;
        this.invoiceNo = _lieReportAndFee.invoiceNo || '';
        this.feeAmount = _lieReportAndFee.feeAmount || 0;
        this.statusOfFeeReceipt = _lieReportAndFee.statusOfFeeReceipt || '';
        this.statusOfFeePaid = _lieReportAndFee.statusOfFeePaid || '';
        this.documentTitle = _lieReportAndFee.documentTitle || '';
        this.nextReportDate = _lieReportAndFee.nextReportDate || undefined;
    }
}
 