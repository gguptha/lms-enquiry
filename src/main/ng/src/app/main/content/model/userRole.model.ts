export class UserRole
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
    constructor(_role: any) {
        this.code = _role.code || '';
        this.name = _role.name || '';
    }

  /**
   * getUserRoles()
   * @param unitOfMeasure
   */
  public static getUserRoles(): UserRole[] {

    return ([
      {code:"TR0100",name:"Loan Applicant"},
      {code:"ZLM013",name:"Loan Officer"},
      {code:"ZLM023",name:"Administrator"}
    ]);

  }
}
