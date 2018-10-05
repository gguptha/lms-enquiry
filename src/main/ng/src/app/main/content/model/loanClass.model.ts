export class LoanClassModel {

    /**
     * code
     */
    code: number;

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
        this.code = _loanClass.code || 0;
        this.value = _loanClass.value || '';
    }

    /**
     * getLoanClassDescription()
     * @param loanClass 
     */
    public static getLoanClassDescription(loanClass: number): string {
        switch (loanClass) {
            case 0: return '';
            case 1: return 'Power';
            case 2: return 'Railways';
            case 3: return 'Urban Infrastructure';
            case 4: return 'Roads';
            case 5: return 'Ports';
            case 6: return 'Oil & Gas';
            case 7: return 'Corporates';
            case 8: return 'Infrastructure';
            case 9: return 'Others';
        } 
    }
}
