export class FinancingType {

    private static financingTypes = [
        { 'code': '01', 'value': 'Sole Lending' },
        { 'code': '02', 'value': 'Consor. Lending' },
        { 'code': '03', 'value': 'Lead FI' },
        { 'code': '04', 'value': 'Underwriting' },
        { 'code': '05', 'value': 'Lead FI & Synd.' },
        { 'code': '06', 'value': 'Syndication' }
    ];

    public static getFinancingTypes(): Array<any> {
        return this.financingTypes;
    }
}
