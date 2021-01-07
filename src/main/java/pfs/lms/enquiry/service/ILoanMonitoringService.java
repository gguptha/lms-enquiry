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

    // Site Visit
    SiteVisit saveSiteVisit(SiteVisitResource resource, String username);
    SiteVisit updateSiteVisit(SiteVisitResource resource, String username);
    List<SiteVisitResource> getSiteVisit(String loanApplicationId, String name);

    // Operating Parameter
    OperatingParameter saveOperatingParameter(OperatingParameterResource resource, String username);
    OperatingParameter updateOperatingParameter(OperatingParameterResource resource, String username);
    List<OperatingParameterResource> getOperatingParameter(String loanApplicationId, String name);

    // Rate Of Interest
    RateOfInterest saveRateOfInterest(RateOfInterestResource resource, String username);
    RateOfInterest updateRateOfInterest(RateOfInterestResource resource, String username);
    List<RateOfInterestResource> getRateOfInterest(String loanApplicationId, String name);

    // Borrower Financials
    BorrowerFinancials saveBorrowerFinancials(BorrowerFinancialsResource resource, String username);
    BorrowerFinancials updateBorrowerFinancials(BorrowerFinancialsResource resource, String username);
    List<BorrowerFinancialsResource> getBorrowerFinancials(String loanApplicationId, String name);

    // Promoter Financials
    PromoterFinancials savePromoterFinancials(PromoterFinancialsResource resource, String username);
    PromoterFinancials updatePromoterFinancials(PromoterFinancialsResource resource, String username);
    List<PromoterFinancialsResource> getPromoterFinancials(String loanApplicationId, String name);

    //  Financial Covenants
    FinancialCovenants saveFinancialCovenants(FinancialCovenantsResource resource, String username);
    FinancialCovenants updateFinancialCovenants(FinancialCovenantsResource resource, String username);
    List<FinancialCovenantsResource> getFinancialCovenants(String loanApplicationId, String name);


    //  Promoter Details
    PromoterDetails savePromoterDetails(PromoterDetailsResource resource, String username);
    PromoterDetails updatePromoterDetails(PromoterDetailsResource resource, String username);
    List<PromoterDetailsResource> getPromoterDetails(String loanApplicationId, String name);

    //  Promoter Details Item
    PromoterDetailsItem savePromoterDetailsItem(PromoterDetailsItemResource resource, String username);
    PromoterDetailsItem updatePromoterDetailsItem(PromoterDetailsItemResource resource, String username);
    List<PromoterDetailsItemResource> getPromoterDetailsItem(String loanApplicationId, String name);


}
