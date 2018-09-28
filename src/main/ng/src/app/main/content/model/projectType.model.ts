export class ProjectType {

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
     * @param _projectType
     */
    constructor(_projectType: any) {
        // Initialize the object.
        this.code = _projectType.code || '';
        this.value = _projectType.value || '';
    }
}
