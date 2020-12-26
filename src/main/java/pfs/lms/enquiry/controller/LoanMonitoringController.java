package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.*;
import pfs.lms.enquiry.resource.*;

import pfs.lms.enquiry.service.ILIEReportAndFeeService;
import pfs.lms.enquiry.service.ILIEService;
import pfs.lms.enquiry.service.ILoanMonitoringService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor

public class LoanMonitoringController {

   // private final ILIEService lieService;
   // private final ILIEReportAndFeeService lieReportAndFeeService;

    private final ILoanMonitoringService loanMonitoringService;

    // Create update and list (LIE)

    @PostMapping("/loanApplications/lendersindependentengineers/create")
    public ResponseEntity createLIE(@RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                loanMonitoringService.saveLIE(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineer);
    }

    @PutMapping("/loanApplications/lendersindependentengineers/{id}")
    public ResponseEntity updateLIE(@PathVariable("id") String lieId, @RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                loanMonitoringService.updateLIE(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lendersIndependentEngineer);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/lendersIndependentEngineers")
    public ResponseEntity getLendersIndependentEngineers(@PathVariable("loanapplicationid") String loanApplicationId,
                                                         HttpServletRequest request)
    {
        List<LIEResource> lendersIndependentEngineers = loanMonitoringService.getLendersIndependentEngineers(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineers);
    }



    // create, update and list (LIE Report Submission and Fee)

    @PostMapping("/loanApplications/liereportandfeesubmission/create")
    public ResponseEntity createLIEReportSubmissionAndFee(@RequestBody LIEReportAndFeeResource resource, HttpServletRequest request) {
        LIEReportAndFee lieReportAndFee =
                loanMonitoringService.saveLIEReportAndFee(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lieReportAndFee);
    }

    @PutMapping("/loanApplications/liereportandfeesubmission/{id}")
    public ResponseEntity updateLIEReportSubmissionAndFee(@PathVariable("id") String lieId, @RequestBody LIEReportAndFeeResource resource, HttpServletRequest request) {
        LIEReportAndFee lieReportAndFee =
                loanMonitoringService.updateLIEReportAndFee(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lieReportAndFee);

    }

    @GetMapping("/loanApplications/lendersIndependentEngineer/{lendersindependentengineerid}/lieReceiptsAndFees")
    public ResponseEntity getLIEReportAndFee(@PathVariable("lendersindependentengineerid")
                                                     String lendersIndependentEngineerId,
                                             HttpServletRequest request)
    {
        List<LIEReportAndFeeResource>  lieReportAndFeeResources = loanMonitoringService.getLIEReportAndFee(lendersIndependentEngineerId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lieReportAndFeeResources);
    }

    // create, update and list (LFA)

    @PostMapping("/loanApplications/lendersfinancialAdvisors/create")
    public ResponseEntity createLFA(@RequestBody LFAResource resource, HttpServletRequest request) {
        LendersFinancialAdvisor lendersFinancialAdvisor =
                loanMonitoringService.saveLFA(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersFinancialAdvisor);
    }

    @PutMapping("/loanApplications/lendersfinancialAdvisors/{id}")
    public ResponseEntity updateLFA(@PathVariable("id") String lieId, @RequestBody LFAResource resource, HttpServletRequest request) {
        LendersFinancialAdvisor lendersFinancialAdvisor =
                loanMonitoringService.updateLFA(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lendersFinancialAdvisor);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/lendersFinancialAdvisors")
    public ResponseEntity getLendersFinancialAdvisors(@PathVariable("loanapplicationid") String loanApplicationId,
                                                         HttpServletRequest request)
    {
        List<LFAResource> lendersFinancialAdvisors = loanMonitoringService.getLendersFinancialAdvisors(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersFinancialAdvisors);
    }



    // create, update and list (LFA Report Submission and Fee)

    @PostMapping("/loanApplications/lfareportandfeesubmission/create")
    public ResponseEntity createLFAReportSubmissionAndFee(@RequestBody LFAReportAndFeeResource resource, HttpServletRequest request) {
        LFAReportAndFee lfaReportAndFee =
                loanMonitoringService.saveLFAReportAndFee(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lfaReportAndFee);
    }

    @PutMapping("/loanApplications/lfareportandfeesubmission/{id}")
    public ResponseEntity updateLFAReportSubmissionAndFee(@PathVariable("id") String lieId, @RequestBody LFAReportAndFeeResource resource, HttpServletRequest request) {
        LFAReportAndFee lfaReportAndFee =
                loanMonitoringService.updateLFAReportAndFee(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lfaReportAndFee);

    }

    @GetMapping("/loanApplications/lendersFinancialAdvisor/{lendersfinancialadvisorid}/lfaReceiptsAndFees")
    public ResponseEntity getLFAReportAndFee(@PathVariable("lendersfinancialadvisorid")
                                                     String lendersFinancialAdvisorId,
                                             HttpServletRequest request)
    {
        List<LFAReportAndFeeResource>  lfaReportAndFeeResources = loanMonitoringService.getLFAReportAndFee(lendersFinancialAdvisorId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lfaReportAndFeeResources);
    }



    // Create update and list (TRA)

    @PostMapping("/loanApplications/trustretentionaccount/create")
    public ResponseEntity createTRA(@RequestBody TRAResource resource, HttpServletRequest request) {
        TrustRetentionAccount trustRetentionAccount =
                loanMonitoringService.saveTRA(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(trustRetentionAccount);
    }

    @PutMapping("/loanApplications/trustretentionaccounts/{id}")
    public ResponseEntity updateTRA(@PathVariable("id") String traId, @RequestBody TRAResource resource, HttpServletRequest request) {
        TrustRetentionAccount trustRetentionAccount =
                loanMonitoringService.updateTRA(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(trustRetentionAccount);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/trustretentionaccounts")
    public ResponseEntity getTrustRetentionAccounts(@PathVariable("loanapplicationid") String loanApplicationId,
                                                         HttpServletRequest request)
    {
        List<TRAResource> trustRetentionAccounts = loanMonitoringService.getTrustRetentionAccounts(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(trustRetentionAccounts);
    }

    // create, update and list (TRA Statement)

    @PostMapping("/loanApplications/trastatement/create")
    public ResponseEntity createTRAStatement(@RequestBody TRAStatementResource resource, HttpServletRequest request) {
        TrustRetentionAccountStatement trustRetentionAccountStatement =
                loanMonitoringService.saveTRAStatement(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(trustRetentionAccountStatement);
    }

    @PutMapping("/loanApplications/trastatement/{id}")
    public ResponseEntity updateTRAStatement(@PathVariable("id") String traId, @RequestBody TRAStatementResource resource, HttpServletRequest request) {
        TrustRetentionAccountStatement trustRetentionAccountStatement =
                loanMonitoringService.updateTRAStatement(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(trustRetentionAccountStatement);

    }

    @GetMapping("/loanApplications/trustretentionaccount/{trustretentionaccountid}/traStatements")
    public ResponseEntity getTRAStatements(@PathVariable("trustretentionaccountid")
                                                     String trustRetentionAccountid,
                                             HttpServletRequest request)
    {
        List<TRAStatementResource>  traStatementResources = loanMonitoringService.getTrustRetentionAccountStatements(trustRetentionAccountid,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(traStatementResources);
    }


    // Create update and list (Terms and Conditions)

    @PostMapping("/loanApplications/termsandconditions/create")
    public ResponseEntity createTermsAndConditions(@RequestBody TermsAndConditionsResource resource, HttpServletRequest request) {
        TermsAndConditionsModification termsAndConditions =
                loanMonitoringService.saveTermsAndConditions(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(termsAndConditions);
    }

    @PutMapping("/loanApplications/termsandconditions/{id}")
    public ResponseEntity updateTermsAndConditions(@PathVariable("id") String traId, @RequestBody TermsAndConditionsResource resource, HttpServletRequest request) {
        TermsAndConditionsModification termsAndConditions =
                loanMonitoringService.updateTermsAndConditions(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(termsAndConditions);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/termsandconditions")
    public ResponseEntity getTermsAndConditions(@PathVariable("loanapplicationid") String loanApplicationId,
                                                    HttpServletRequest request)
    {
        List<TermsAndConditionsResource> termsAndConditions = loanMonitoringService.getTermsAndConditions(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(termsAndConditions);
    }


    // Create update and list (Security Compliance)

    @PostMapping("/loanApplications/securitycompliance/create")
    public ResponseEntity createSecurityCompliance(@RequestBody SecurityComplianceResource resource, HttpServletRequest request) {
        SecurityCompliance securityCompliance =
                loanMonitoringService.saveSecurityCompliance(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(securityCompliance);
    }

    @PutMapping("/loanApplications/securitycompliance/{id}")
    public ResponseEntity updateSecurityCompliance(@PathVariable("id") String securityComplianceId, @RequestBody SecurityComplianceResource resource, HttpServletRequest request) {
        SecurityCompliance securityCompliance =
                loanMonitoringService.updateSecurityCompliance(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(securityCompliance);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/securitycompliance")
    public ResponseEntity getSecurityCompliance(@PathVariable("loanapplicationid") String loanApplicationId,
                                                HttpServletRequest request)
    {
        List<SecurityComplianceResource> securityCompliances = loanMonitoringService.getSecurityCompliance(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(securityCompliances);
    }

}
