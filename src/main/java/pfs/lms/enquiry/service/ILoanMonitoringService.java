package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.*;
import pfs.lms.enquiry.resource.*;

import java.util.List;

public interface ILoanMonitoringService {

    //Lenders Independent Engineer
    LendersIndependentEngineer saveLIE(LIEResource resource, String username);
    LendersIndependentEngineer updateLIE(LIEResource resource, String username);
    List<LIEResource> getLendersIndependentEngineers(String loanApplicationId, String name);


    //Lenders Independent Engineer Report and Fee
    LIEReportAndFee saveLIEReportAndFee(LIEReportAndFeeResource resource, String username);
    LIEReportAndFee updateLIEReportAndFee(LIEReportAndFeeResource resource, String username);
    List<LIEReportAndFeeResource> getLIEReportAndFee(String loanApplicationId, String name);


    //Lenders financial Advisor
    LendersFinancialAdvisor saveLFA(LFAResource resource, String username);
    LendersFinancialAdvisor updateLFA(LFAResource resource, String username);
    List<LFAResource> getLendersFinancialAdvisors(String loanApplicationId, String name);


    //Lenders Financial Advisor Report and Fee
    LFAReportAndFee saveLFAReportAndFee(LFAReportAndFeeResource resource, String username);
    LFAReportAndFee updateLFAReportAndFee(LFAReportAndFeeResource resource, String username);
    List<LFAReportAndFeeResource> getLFAReportAndFee(String loanApplicationId, String name);

    // Trust Retention Account
    TrustRetentionAccount saveTRA(TRAResource resource, String username);
    TrustRetentionAccount updateTRA(TRAResource resource, String username);
    List<TRAResource> getTrustRetentionAccounts(String loanApplicationId, String name);

    //TRA STATEMENT
    TrustRetentionAccountStatement saveTRAStatement(TRAStatementResource resource, String username);
    TrustRetentionAccountStatement updateTRAStatement(TRAStatementResource resource, String username);
    List<TRAStatementResource> getTrustRetentionAccountStatements(String loanApplicationId, String name);


    //Terms and Conditions
    TermsAndConditionsModification saveTermsAndConditions(TermsAndConditionsResource resource, String username);
    TermsAndConditionsModification updateTermsAndConditions(TermsAndConditionsResource resource, String username);
    List<TermsAndConditionsResource> getTermsAndConditions(String loanApplicationId, String name);

    // Security Compliance
    SecurityCompliance saveSecurityCompliance(SecurityComplianceResource resource, String username);
    SecurityCompliance updateSecurityCompliance(SecurityComplianceResource resource, String username);
    List<SecurityComplianceResource> getSecurityCompliance(String loanApplicationId, String name);



}
