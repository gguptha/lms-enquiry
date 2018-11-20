export class FinancingTypeModel {

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
     * @param _financingType
     */
    constructor(_financingType: any) {
        // Initialize the object.
        this.code = _financingType.code || '';
        this.value = _financingType.value || '';
    }
}
