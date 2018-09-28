package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.resource.SearchResource;
import pfs.lms.enquiry.service.ILoanApplicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
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

    @PutMapping("/loanApplications/search")
    public ResponseEntity search(@RequestBody SearchResource resource,Pageable pageable){
        Set<LoanApplication> loanApplications = new HashSet<>(loanApplicationRepository.findAll(pageable).getContent());

        if (resource.getEnquiryDateFrom() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanEnquiryDate().isAfter(resource.getEnquiryDateFrom())).collect(Collectors.toSet());

        if (resource.getEnquiryDateTo() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanEnquiryDate().isBefore(resource.getEnquiryDateTo())).collect(Collectors.toSet());

        if (resource.getPromoterName() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getPromoterName().equals(resource.getPromoterName())).collect(Collectors.toSet());

        if (resource.getLoanClass() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanClass().equals(resource.getLoanClass())).collect(Collectors.toSet());

        if (resource.getFinancingType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getFinancingType().equals(resource.getFinancingType())).collect(Collectors.toSet());

        if (resource.getEnquiryNo() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getEnquiryNo().getId().equals(resource.getEnquiryNo())).collect(Collectors.toSet());

        if (resource.getProjectLocationState() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectLocationState().equals(resource.getProjectLocationState())).collect(Collectors.toSet());

        if (resource.getProjectType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectType().equals(resource.getProjectType())).collect(Collectors.toSet());

        if (resource.getAssistanceType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getAssistanceType().equals(resource.getAssistanceType())).collect(Collectors.toSet());


        Set<LoanApplicationResource> resources = new HashSet<>(0);
        loanApplications.forEach(loanApplication -> {
            Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
            resources.add(new LoanApplicationResource(loanApplication,partner));
        });

        return ResponseEntity.ok(resources);
    }
}