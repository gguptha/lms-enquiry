export class ProjectTypeModel {

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

    /**
     * getProjectTypeDescription()
     * @param projectType 
     */
    public static getProjectTypeDescription(projectType: string): string {
        switch (projectType) {
            case '0':  return '';
            case '01': return 'Thermal - Coal';
            case '02': return 'Thermal - Ignite';
            case '03': return 'Thermal - Gas';
            case '04': return 'Hydro';
            case '05': return 'Renewable - Solar';
            case '06': return 'Renewable - Wind';
            case '07': return 'Renewable - Biomass';
            case '08': return 'Renewable - Small Hydro';
            case '09': return 'EPC Contractors';
            case '10': return 'Coal Mining';
            case '11': return 'Power Transmission';
            case '12': return 'Railway Siding';
            case '13': return 'Ports';
            case '14': return 'Corporate';
            case '15': return 'Renovation & Modernisation';
            case '16': return 'Others';
        }    
    }
}
