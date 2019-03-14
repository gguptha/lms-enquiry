package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.resource.ProcessorResource;

@ApiController
@RequiredArgsConstructor
public class Controller {

    private final LoanApplicationRepository loanApplicationRepository;

    @PutMapping("/loanEnquiry/assignProcessors")
    public ResponseEntity updateProcessors(@RequestBody ProcessorResource processorResource) {
        LoanApplication loanApplication = loanApplicationRepository.findByEnquiryNo(processorResource.getEnquiryNo());
        loanApplication.updateProcessors(processorResource.getProjectDepartmentInitiator(), processorResource.getMonitoringDepartmentInitiator());
        loanApplication = loanApplicationRepository.save(loanApplication);
        return ResponseEntity.ok(loanApplication);
    }
}
