package pfs.lms.enquiry.service;

import pfs.lms.enquiry.domain.LFAReportAndFee;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersFinancialAdvisor;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LFAReportAndFeeResource;
import pfs.lms.enquiry.resource.LFAResource;
import pfs.lms.enquiry.resource.LIEReportAndFeeResource;
import pfs.lms.enquiry.resource.LIEResource;

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


}
