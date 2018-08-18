export class AssistanceType {

    private static assistanceTypes = [
        { 'code': 'D', 'value': 'Debt' },
        { 'code': 'E', 'value': 'Equity' },
    ];

    public static getAssistanceTypes(): Array<any> {
        return this.assistanceTypes;
    }
}
