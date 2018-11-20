export class LoanClassModel {

    /**
     * code
     */
    code: string;

    /**
     * value
     */
    value: string;

    /**
     * constructor()
     * @param _loanClass
     */
    constructor(_loanClass: any) {
        // Initialize the object.
        this.code = _loanClass.code || '';
        this.value = _loanClass.value || '';
    }

    /**
     * getLoanClassDescription()
     * @param loanClass 
     */
    public static getLoanClassDescription(loanClass: string): string {
        switch (loanClass) {
            case '0' : return '';
            case '01': return 'Power';
            case '02': return 'Railways';
            case '03': return 'Urban Infrastructure';
            case '04': return 'Roads';
            case '05': return 'Ports';
            case '06': return 'Oil & Gas';
            case '07': return 'Corporates';
            case '08': return 'Infrastructure';
            case '09': return 'Others';
        } 
    }
}
