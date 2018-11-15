export class ProductModel 
{
    /**
     * code
     */
    code: string;

    /**
     * value
     */
    name: string;

    /**
     * constructor()
     * Initialize the object.
     * @param _product
     */
    constructor(_product: any) {
        this.code = _product.code || '';
        this.name = _product.name || '';
    }
}
