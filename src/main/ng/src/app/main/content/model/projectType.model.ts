export class ProjectTypeModel {

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
     * @param _projectType
     */
    constructor(_projectType: any) {
        // Initialize the object.
        this.code = _projectType.code || 0;
        this.value = _projectType.value || '';
    }

    /**
     * getProjectTypeDescription()
     * @param projectType 
     */
    public static getProjectTypeDescription(projectType: number): string {
        switch (projectType) {
            case  0: return '';
            case  1: return 'Thermal - Coal';
            case  2: return 'Thermal - Ignite';
            case  3: return 'Thermal - Gas';
            case  4: return 'Hydro';
            case  5: return 'Renewable - Solar';
            case  6: return 'Renewable - Wind';
            case  7: return 'Renewable - Biomass';
            case  8: return 'Renewable - Small Hydro';
            case  9: return 'EPC Contractors';
            case 10: return 'Coal Mining';
            case 11: return 'Power Transmission';
            case 12: return 'Railway Siding';
            case 13: return 'Ports';
            case 14: return 'Corporate';
            case 15: return 'Renovation & Modernisation';
            case 16: return 'Others';
        }    
    }
}
