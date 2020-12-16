package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LFAReportAndFee;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersFinancialAdvisor;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LFAReportAndFeeResource;
import pfs.lms.enquiry.resource.LFAResource;
import pfs.lms.enquiry.resource.LIEReportAndFeeResource;
import pfs.lms.enquiry.resource.LIEResource;

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



    // create, update , list LIE Report Submission and Fee

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

    @GetMapping("/loanApplications/lendersIndependentEngineer/{lendersindependentengineerid}")
    public ResponseEntity getLIEReportAndFee(@PathVariable("lendersindependentengineerid")
                                                     String lendersIndependentEngineerId,
                                             HttpServletRequest request)
    {
        List<LIEReportAndFeeResource>  lieReportAndFeeResources = loanMonitoringService.getLIEReportAndFee(lendersIndependentEngineerId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lieReportAndFeeResources);
    }

    //LFA

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



    // create, update , list LIE Report Submission and Fee

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

    @GetMapping("/loanApplications/lendersFinancialAdvisor/{lendersfinancialadvisorid}")
    public ResponseEntity getLFAReportAndFee(@PathVariable("lendersfinancialadvisorid")
                                                     String lendersFinancialAdvisorId,
                                             HttpServletRequest request)
    {
        List<LFAReportAndFeeResource>  lfaReportAndFeeResources = loanMonitoringService.getLFAReportAndFee(lendersFinancialAdvisorId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lfaReportAndFeeResources);
    }


}