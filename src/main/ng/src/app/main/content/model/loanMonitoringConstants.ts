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
        {code: "Z00007" , value:"PFS:Personal Guarantee"},
        {code: "Z00008" , value:"PFS:Corporate Guarantee"},
        {code: "Z00009" , value:"PFS:Bank Guarantee"},
        {code: "Z30001" , value:"Securities"},
        {code: "ZCL001" , value:"DSRA Account"},
        {code: "ZCL002" , value:"TRA Account"},
        {code: "ZCL003" , value:"Other Current Assets"},
        {code: "ZDE001" , value:"Plant and Machinery"},
        {code: "ZDE002" , value:"Furniture and Fixtures"},
        {code: "ZDE003" , value:"Office Equipment"},
        {code: "ZDE004" , value:"Other Movables"},
        {code: "ZIN002" , value:"Inventory"},
        {code: "ZOT001" , value:"Project Clearances"},
        {code: "ZOT002" , value:"Project Documents"},
        {code: "ZOT003" , value:"Bank Guarantees under project documents"},
        {code: "ZOT004" , value:"Approvals"},
        {code: "ZOT005" , value:"Claims from Insurances"},
        {code: "ZOT006" , value:"Claims from letter of credit"},
        {code: "ZPA001" , value:"Patents"},
        {code: "ZRE001" , value:"Land"},
        {code: "ZRE002" , value:"Building"}
    ];

    // collateralAgreementTypes
    static collateralAgreementTypes = [
        {objectType: "ZRE001", code: "Z00001", value:"Mortgage"},
        {objectType: "ZRE002", code: "Z00002", value:"Mortgage Registerd"},
        {objectType: "Z30001", code: "Z00003", value:"Pledge Of Securities Account"},
        {objectType: "ZPA001", code: "Z00005", value:"Pledge of Patents/Rights"},
        {objectType: "ZCL001", code: "Z00004", value:"Pledge of Accounts"},
        {objectType: "ZCL002", code: "Z00004", value:"Pledge of Accounts"},
        {objectType: "ZCL003", code: "Z00004", value:"Pledge of Accounts"},
        {objectType: "Z00007", code: "Z00007", value:"Personal Guarantee"},
        {objectType: "Z00008", code: "Z00008", value:"Corporate Guarantee"},
        {objectType: "Z00009", code: "Z00009", value:"Bank Guarantee"},
        {objectType: "ZIN002", code: "Z00011", value:"Transfer Of Rights on Inventory"},
        {objectType: "ZOT001", code: "Z00006", value:"Pledge of Other Valuables"},
        {objectType: "ZOT002", code: "Z00006", value:"Pledge of Other Valuables"},
        {objectType: "ZOT003", code: "Z00006", value:"Pledge of Other Valuables"},
        {objectType: "ZOT004", code: "Z00006", value:"Pledge of Other Valuables"},
        {objectType: "ZOT005", code: "Z00006", value:"Pledge of Other Valuables"},
        {objectType: "ZOT006", code: "Z00011", value:"Transfer Of Rights on Inventory"},
        {objectType: "ZDE001", code: "Z00010", value:"Hypothecation"},
        {objectType: "ZDE002", code: "Z00010", value:"Hypothecation"},
        {objectType: "ZDE003", code: "Z00010", value:"Hypothecation"},
        {objectType: "ZDE004", code: "Z00010", value:"Hypothecation"}
    ];    

    // applicability
    static applicability = [
        {code: "0" , value:"Yes"},
        {code: "1" , value:"No"}
    ];

    // actionPeriods
    static actionPeriods = [
        {code: "1" , value:"Days"},
        {code: "2" , value:"Weeks"},
        {code: "3" , value:"Months"},
        {code: "4" , value:"Years"}
    ];

    // actionPeriodSuffixes
    static actionPeriodSuffixes = [
        {code: "1" , value:"Days"},
        {code: "2" , value:"Weeks"},
        {code: "3" , value:"Months"},
        {code: "4" , value:"Years"}
    ];

    // eventTypes
    static eventTypes = [
        {code: "1" , value:"Enquiry Completion Date"},
        {code: "2" , value:"ICC Clearance Date"},
        {code: "3" , value:"Appraisal Completion Date"},
        {code: "4" , value:"Sanction Letter Issue Date"},
        {code: "5" , value:"Contract Date"},
        {code: "6" , value:"1st Disbursement Date"},
        {code: "7" , value:"Scheduled COD"},
        {code: "8" , value:"Actual COD"},
        {code: "9" , value:"Date of Security"},
        {code: "0" , value:"Others"}
    ];

    // unitOfMeasures
    static unitOfMeasures = [
        {code: "ACR" , value:"Acre"},
        {code: "CM2" , value:"Square centimeter"},
        {code: "FT2" , value:"Square foot"},
        {code: "HA" , value:"Hectare"},
        {code: "KM2" , value:"Square kilometer"},
        {code: "LHK" , value:"Liter per 100 km"},
        {code: "M2" , value:"Square meter"},
        {code: "MI2" , value:"Square mile"},
        {code: "MM2" , value:"Square millimeter"},
        {code: "YD2" , value:"Square Yard"}
    ];

    // particulars
    static particulars = [
        {code: "1" , value:"Reference  Base Rate %"},
        {code: "2" , value:"Base Rate %"},
        {code: "3" , value:"Spread Reset"},
        {code: "4" , value:"Spread %"},
        {code: "5" , value:"Effective Rate of Interest %"},
        {code: "6" , value:"Reset period"},
        {code: "7" , value:"Next Reset Date (Month,Year)"}
    ]

    // financialCovenantsType
    static financialCovenantsType = [
        {code: "1" , value:"Permissible Limit"},
        {code: "2" , value:"Financial Year"}
    ];
}
