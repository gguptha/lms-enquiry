export class LoanClass {

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
}
