export class EnquiryApplicationRegEx {
    
    // Does not accept decimal places
    public static tenorYear = /^\d{1,2}?$/;
    // Does not accept decimal places
    public static tenorMonth = /^\d{1,2}?$/;

    // Accepts upto 8 digits and 2 ** mandatory ** decimal places. 
    // *** Change {2,2} to {1,2} to accept upto 2 decimal places (99.1 & 99.99 are valid)
    // *** Change {2,2} to {1,3} to accept upto 3 decimal places (99.999)
    public static projectCost = /^\d{1,8}(\.\d{2,2})?$/;

    // ** Check comments on projectCost for other variations in the regEx.
    
    // Accepts upto 4 digits and 2 ** mandatory ** decimal places. 
    public static projectCapacity = /^\d{1,4}(\.\d{2,2})?$/;
    // Accepts upto 8 digits and 2 ** mandatory ** decimal places. 
    public static equity = /^\d{1,8}(\.\d{2,2})?$/;
}
