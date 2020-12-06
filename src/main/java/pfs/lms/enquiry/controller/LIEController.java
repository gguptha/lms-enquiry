package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.resource.LIEReportAndFeeResource;
import pfs.lms.enquiry.resource.LIEResource;

import pfs.lms.enquiry.service.ILIEReportAndFeeService;
import pfs.lms.enquiry.service.ILIEService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor

public class LIEController {

    private final ILIEService lieService;
    private final ILIEReportAndFeeService lieReportAndFeeService;


    @PostMapping("/loanApplications/lendersindependentengineers/create")
    public ResponseEntity create(@RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                lieService.save(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineer);
    }

    @PutMapping("/loanApplications/lendersindependentengineers/{id}")
    public ResponseEntity update(@PathVariable("id") String lieId, @RequestBody LIEResource resource, HttpServletRequest request) {
        LendersIndependentEngineer lendersIndependentEngineer =
                lieService.update(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lendersIndependentEngineer);

    }

    @GetMapping("/loanApplications/{loanapplicationid}/lendersIndependentEngineers")
    public ResponseEntity getLendersIndependentEngineers(@PathVariable("loanapplicationid") String loanApplicationId,
                                                         HttpServletRequest request)
    {
        List<LIEResource> lendersIndependentEngineers = lieService.getLendersIndependentEngineers(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lendersIndependentEngineers);
    }



    // create, update , list LIE Report Submission and Fee

    @PostMapping("/loanApplications/liereportandfeesubmission/create")
    public ResponseEntity createLIEReportSubmissionAndFee(@RequestBody LIEReportAndFeeResource resource, HttpServletRequest request) {
        LIEReportAndFee lieReportAndFee =
                lieReportAndFeeService.save(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(lieReportAndFee);
    }

    @PutMapping("/loanApplications/liereportandfeesubmission/{id}")
    public ResponseEntity updateLIEReortSubmissionAndFee(@PathVariable("id") String lieId, @RequestBody LIEReportAndFeeResource resource, HttpServletRequest request) {
        LIEReportAndFee lieReportAndFee =
                lieReportAndFeeService.update(resource, request.getUserPrincipal().getName());

        return ResponseEntity.ok(lieReportAndFee);

    }

    @GetMapping("/loanApplications/lendersIndependentEngineer/{lendersindependentengineerid}")
    public ResponseEntity getLIEReportAndFee(@PathVariable("lendersindependentengineerid")
                                                     String lendersIndependentEngineerId,
                                             HttpServletRequest request)
    {
        List<LIEReportAndFeeResource>  lieReportAndFeeResources = lieReportAndFeeService.getLIEReportAndFee(lendersIndependentEngineerId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(lieReportAndFeeResources);
    }




}
