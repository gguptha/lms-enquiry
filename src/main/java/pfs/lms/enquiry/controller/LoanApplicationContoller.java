package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.resource.EnquiryRejectReason;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.resource.SearchResource;
import pfs.lms.enquiry.service.ILoanApplicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RepositoryRestController
@RequiredArgsConstructor
public class LoanApplicationContoller {

    private final ILoanApplicationService loanApplicationService;

    private final LoanApplicationRepository loanApplicationRepository;

    private final PartnerRepository partnerRepository;

    @GetMapping("/loanApplications")
    public ResponseEntity<Page<LoanApplication>> get(@RequestParam(value = "status",required = false) Integer status, HttpServletRequest request,
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

    @PutMapping("/loanApplications/{id}")
    public ResponseEntity update(@PathVariable("id") String loanApplicationId,@RequestBody LoanApplicationResource resource, HttpServletRequest request) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplication().getId());
        Partner partner = partnerRepository.getOne(resource.getPartner().getId());
        BeanUtils.copyProperties(resource.getLoanApplication(),loanApplication,"id","enquiryNo");
        BeanUtils.copyProperties(resource.getPartner(),partner,"id");
        loanApplication = loanApplicationRepository.save(loanApplication);
        partner = partnerRepository.save(partner);
        resource.setLoanApplication(loanApplication);
        resource.setPartner(partner);
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/loanApplications/{id}/reject")
    public ResponseEntity update(@PathVariable("id") LoanApplication loanApplication, @RequestBody EnquiryRejectReason enquiryRejectReason, HttpServletRequest request) {
        Partner partner = partnerRepository.findByUserName(request.getUserPrincipal().getName());
        loanApplication.reject(enquiryRejectReason.getRejectReason(), partner);
        loanApplication = loanApplicationRepository.save(loanApplication);
        return ResponseEntity.ok(loanApplication);
    }

    @PutMapping("/loanApplications/search")
    public ResponseEntity search(@RequestBody SearchResource resource, @PageableDefault(sort = {"enquiryNo ASC"}) Pageable pageable){
        List<LoanApplication> loanApplications = new ArrayList<>(loanApplicationRepository.findAll(pageable).getContent());

        if (resource.getEnquiryDateFrom() != null && resource.getEnquiryDateTo() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> (loanApplication.getLoanEnquiryDate().isAfter(resource.getEnquiryDateFrom()) || loanApplication.getLoanEnquiryDate().equals(resource.getEnquiryDateFrom())) && (loanApplication.getLoanEnquiryDate().isBefore(resource.getEnquiryDateTo()) || loanApplication.getLoanEnquiryDate().equals(resource.getEnquiryDateTo()))).collect(Collectors.toList());

        if (resource.getEnquiryNoFrom() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getEnquiryNo().getId() >=
                    resource.getEnquiryNoFrom()).collect(Collectors.toList());

        if (resource.getEnquiryNoTo() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getEnquiryNo().getId() <=
                    resource.getEnquiryNoTo()).collect(Collectors.toList());

        if (resource.getPartyName() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getPromoterName().contains(resource.getPartyName())).collect(Collectors.toList());

        if (resource.getLoanClass() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanClass().equals(resource.getLoanClass())).collect(Collectors.toList());

        if (resource.getFinancingType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getFinancingType().equals(resource.getFinancingType())).collect(Collectors.toList());

        if (resource.getProjectLocationState() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectLocationState().equals(resource.getProjectLocationState())).collect(Collectors.toList());

        if (resource.getProjectType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectType().equals(resource.getProjectType())).collect(Collectors.toList());

        if (resource.getAssistanceType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getAssistanceType().equals(resource.getAssistanceType())).collect(Collectors.toList());

        List<LoanApplicationResource> resources = new ArrayList<>(0);
        loanApplications.forEach(loanApplication -> {
            Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
            resources.add(new LoanApplicationResource(loanApplication,partner));
        });

        return ResponseEntity.ok(resources);
    }
}