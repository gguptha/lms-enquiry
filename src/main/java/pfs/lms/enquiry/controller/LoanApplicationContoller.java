package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.service.ILoanApplicationService;

import javax.servlet.http.HttpServletRequest;

@RepositoryRestController
@RequiredArgsConstructor
public class LoanApplicationContoller {

    private final ILoanApplicationService loanApplicationService;

    private final LoanApplicationRepository loanApplicationRepository;

    private final PartnerRepository partnerRepository;

    @GetMapping("/loanApplications")
    public ResponseEntity<Page<LoanApplication>> get(@RequestParam("status") Integer status, HttpServletRequest request,
                                                     Pageable pageable) {
        Partner partner = partnerRepository.findByUserName(request.getUserPrincipal().getName());
        if (partner.getPartyRole().equals("ZLM023")) {
            if (status == null)
                return ResponseEntity.ok(loanApplicationRepository.findAll(pageable));
            else
                return ResponseEntity.ok(loanApplicationRepository.findByFunctionalStatus(status, pageable));
        }
        else {
            if (status == null)
                return ResponseEntity.ok(loanApplicationRepository.findByLoanApplicant(partner.getId(), pageable));
            else
                return ResponseEntity.ok(loanApplicationRepository.findByLoanApplicantAndFunctionalStatus(partner.getId(),
                        status, pageable));
        }
    }

    @PostMapping("/loanApplications")
    public ResponseEntity add(@RequestBody LoanApplicationResource resource, HttpServletRequest request) {
        return ResponseEntity.ok(loanApplicationService.save(resource, request.getUserPrincipal().getName()));
    }
}