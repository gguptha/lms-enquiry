export class LoanMonitoringConstants {

    // reportTypes
    public static reportTypes = [
        {code: "1" , value:"Due Diligence"},
        {code: "2" , value:"Monitoring"},
        {code: "3" , value:"O & M Review"},
        {code: "4" , value:"Other (if any)"}
    ];

    // feeReceiptStatuses
    public static feeReceiptStatuses = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    // feePaidStatuses
    public static feePaidStatuses = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    static viewRights = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    static periodQuarters = [
        {code: "I" , value:"First Quarter"},
        {code: "II" , value:"Second Quarter"},
        {code: "III" , value:"Third Quarter"},
        {code: "IV" , value:"Fourth Quarter"}
    ];

    static documentTypes = [
        {code: "ZPFSLM1" , value:"PFS Project Notes"},
        {code: "ZPFSLM10" , value:"Loan Agreement"},
        {code: "ZPFSLM11" , value:"Lenders Agent Agreement"},
        {code: "ZPFSLM12" , value:"Security Trustee Agreement"}
    ]
}
