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

    // viewRights
    static viewRights = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    // periodQuarters
    static periodQuarters = [
        {code: "I" , value:"First Quarter"},
        {code: "II" , value:"Second Quarter"},
        {code: "III" , value:"Third Quarter"},
        {code: "IV" , value:"Fourth Quarter"}
    ];

    // documentTypes
    static documentTypes = [
        {code: "ZPFSLM1" , value:"PFS Project Notes"},
        {code: "ZPFSLM10" , value:"Loan Agreement"},
        {code: "ZPFSLM11" , value:"Lenders Agent Agreement"},
        {code: "ZPFSLM12" , value:"Security Trustee Agreement"}
    ]

    // communications
    static communications = [
        {code: "0" , value:"Original"},
        {code: "1" , value:"Amended"},
        {code: "2" , value:"Others"}
    ]

    // collateralObjectTypes
    static collateralObjectTypes = [
        {code: "0" , value:"Collateral Object Type 1"},
        {code: "1" , value:"Collateral Object Type 2"}
    ];

    // collateralAgreementTypes
    static collateralAgreementTypes = [
        {code: "0" , value:"Collateral Agreement Type 1"},
        {code: "1" , value:"Collateral Agreement Type 2"}
    ];    

    // applicability
    static applicability = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    // actionPeriods
    static actionPeriods = [
        {code: "0" , value:"Action Period 1"},
        {code: "1" , value:"Action Period 2"}
    ];

    // actionPeriodSuffixes
    static actionPeriodSuffixes = [
        {code: "0" , value:"Action Period Suffix 1"},
        {code: "1" , value:"Action Period Suffix 2"}
    ];

    // eventTypes
    static eventTypes = [
        {code: "0" , value:"Event Type 1"},
        {code: "1" , value:"Event Type 2"}
    ];

    // unitOfMeasures
    static unitOfMeasures = [
        {code: "0" , value:"Unit of Measure 1"},
        {code: "1" , value:"Unit of Measure 2"}
    ];
}
