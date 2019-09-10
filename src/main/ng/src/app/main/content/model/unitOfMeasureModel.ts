export class UnitOfMeasureModel {

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
     * @param unitOfMeasure
     */
    constructor(_unitOfMeasure: any) {
        // Initialize the object.
        this.code = _unitOfMeasure.code || '';
        this.value = _unitOfMeasure.value || '';
    }

    /**
     * getUnitOfMeasureDescription()
     * @param unitOfMeasure
     */
    public static getUnitOfMeasureDesc(_unitOfMeasure: string): string {
        switch (_unitOfMeasure) {
            case 'MW': return 'Mega Watts';
            case 'KW': return 'Kilo Watts';
            case 'KM': return 'Kilometers';
            case 'KM2': return 'Square Kilometers';


        }
    }
}
