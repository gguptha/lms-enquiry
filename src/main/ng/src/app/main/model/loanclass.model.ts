export class LoanClass {

    private static loanClass = [
        { 'code': '001', 'value': 'Power' },
        { 'code': '002', 'value': 'Railways' },
        { 'code': '003', 'value': 'Urban Infra' },
        { 'code': '004', 'value': 'Roads' },
        { 'code': '005', 'value': 'Ports' },
        { 'code': '006', 'value': 'Oil & Gas' },
        { 'code': '007', 'value': 'Corporates' },
        { 'code': '008', 'value': 'Infrastructure' },
        { 'code': '009', 'value': 'Others' }
    ];

    public static getLoanClasses(): Array<any> {
        return this.loanClass;
    }
}
