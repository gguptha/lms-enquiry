export class FunctionalStatusModel {

    /**
     * getFunctionalStatus()
     * @param functionalStatus 
     */
    public static getFunctionalStatus(functionalStatus: number): string {
        switch (functionalStatus) {
            case 0: return '';
            case 1: return 'Enquiry Stage';
            case 2: return 'ICC ApprovalStage';
            case 3: return 'Apprisal Stage';
            case 4: return 'Board Approval Stage';
            case 5: return 'Loan Documenation Stage';
            case 6: return 'Loan Disbursement Stage';
            case 7: return 'Approved';
            case 8: return 'Rejected';
            case 9: return 'Cancelled';
        } 
    }
}
