export class ProjectType {

    private static projectTypes = [
        { 'code': '01', 'value': 'Thermal - Coal' },
        { 'code': '02', 'value': 'Thermal - Ignite' },
        { 'code': '03', 'value': 'Thermal - Gas' },
        { 'code': '04', 'value': 'Hydro' },
        { 'code': '05', 'value': 'Renewable - Solar' },
        { 'code': '06', 'value': 'Renewable - Wind' },
        { 'code': '07', 'value': 'Renewable - Biomass' },
        { 'code': '08', 'value': 'Renewable - Small Hydro' },
        { 'code': '09', 'value': 'EPC Contractors' },
        { 'code': '10', 'value': 'Coal Mining' },
        { 'code': '11', 'value': 'Power Transmission' },
        { 'code': '12', 'value': 'Railway Siding' },
        { 'code': '13', 'value': 'Ports' },
        { 'code': '14', 'value': 'Corporate' },
        { 'code': '15', 'value': 'Renovation & Modernisation' },
        { 'code': '16', 'value': 'Others' }
    ];

    public static getProjectTypes(): Array<any> {
        return this.projectTypes;
    }
}
