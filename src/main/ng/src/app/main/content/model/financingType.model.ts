export class FinancingTypeModel {

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
     * @param _financingType
     */
    constructor(_financingType: any) {
        // Initialize the object.
        this.code = _financingType.code || 0;
        this.value = _financingType.value || '';
    }
}
