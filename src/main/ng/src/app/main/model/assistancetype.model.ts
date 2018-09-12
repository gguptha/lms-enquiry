export class AssistanceType {

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
     * @param _assistanceType
     */
    constructor(_assistanceType: any) {
        // Initialize the object.
        this.code = _assistanceType.code || '';
        this.value = _assistanceType.value || '';
    }
}
