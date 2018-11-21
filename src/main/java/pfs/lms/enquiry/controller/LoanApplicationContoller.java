package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.repository.UserRepository;
import pfs.lms.enquiry.resource.*;
import pfs.lms.enquiry.service.ILoanApplicationService;
import pfs.lms.enquiry.service.ISAPIntegrationService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RepositoryRestController
@RequiredArgsConstructor
public class LoanApplicationContoller {

    private final ILoanApplicationService loanApplicationService;

    private final ISAPIntegrationService integrationService;

    private final LoanApplicationRepository loanApplicationRepository;

    private final PartnerRepository partnerRepository;

    private final UserRepository userRepository;

    @GetMapping("/loanApplications")
    public ResponseEntity get(@RequestParam(value = "status",required = false) Integer status, HttpServletRequest request,
                                                     Pageable pageable)
    {
        List<LoanApplication> loanApplications = null;

        User user;
        if(request.getUserPrincipal().getName().equals("admin"))
            user = userRepository.findByEmail("admin@gmail.com");
        else
            user = userRepository.findByEmail(request.getUserPrincipal().getName());

        if (user.getRole().equals("ZLM023") || user.getRole().equals("ZLM013") ||
                user.getRole().equals("ZLM010")) {
            if (status == null)
                loanApplications = loanApplicationRepository.findAll(pageable).getContent();
            else
                loanApplications = loanApplicationRepository.findByFunctionalStatus(status, pageable).getContent();
        }
        else {
            Partner partner = partnerRepository.findByEmail(user.getEmail());
            if (partner != null)
                loanApplications = loanApplicationRepository.findByLoanApplicant(partner.getId(), pageable).getContent();
        }

        List<LoanApplicationResource> resources = new ArrayList<>(0);
        if (loanApplications != null) {
            loanApplications.forEach(loanApplication -> {
                Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
                resources.add(new LoanApplicationResource(loanApplication, partner));
            });
        }

        return ResponseEntity.ok(resources);
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

    @PutMapping("/loanApplications/{id}/approve")
    public ResponseEntity approve(@PathVariable("id") String loanApplicationId, @RequestBody LoanApplicationResource resource)
            throws Exception
    {
        try
        {
            LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplication().getId());
            Partner partner = partnerRepository.getOne(resource.getPartner().getId());

            SAPLoanApplicationDetailsResource detailsResource = new SAPLoanApplicationDetailsResource();

            detailsResource.setLoanApplicationId(Long.toString(resource.getLoanApplication().getEnquiryNo().getId()));

            detailsResource.setPartnerExternalNumber("1");
            detailsResource.setPartnerRole("TR0100");
            detailsResource.setFirstname(resource.getPartner().getPartyName1());
            detailsResource.setLastname(resource.getPartner().getPartyName2() == null? "":
                    resource.getPartner().getPartyName2());
            detailsResource.setEmail(resource.getPartner().getEmail());
            detailsResource.setCity(resource.getPartner().getCity());
            detailsResource.setPostalCode(resource.getPartner().getPostalCode());
            detailsResource.setHouseNo(resource.getPartner().getAddressLine1());
            detailsResource.setStreet(resource.getPartner().getStreet());
            detailsResource.setCountry("IN");
            detailsResource.setContactPerName(resource.getPartner().getContactPersonName());

            detailsResource.setApplicationDate("\\/Date(1228089600000)\\/");
            detailsResource.setLoanClass(resource.getLoanApplication().getLoanClass());
            detailsResource.setFinancingType(resource.getLoanApplication().getFinancingType());
            detailsResource.setDebtEquityIndicator("X");
            detailsResource.setProjectCapaacity(resource.getLoanApplication().getProjectCapacity() == null? "0.00":
                    String.format("%.2f", resource.getLoanApplication().getProjectCapacity()));
            detailsResource.setProjectCapacityUnit("MW");
            detailsResource.setScheduledCommDate("\\/Date(1228089600000)\\/");
            detailsResource.setProjectCostInCrores(resource.getLoanApplication().getProjectCost() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getProjectCost()));
            detailsResource.setDebtAmountInCrores(resource.getLoanApplication().getProjectDebtAmount() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getProjectDebtAmount()));
            detailsResource.setEquityAmountInCrores(resource.getLoanApplication().getEquity() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getEquity()));
            detailsResource.setCurrency("INR");
            detailsResource.setApplicationCapitalInCrores(resource.getLoanApplication().getPfsDebtAmount() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getPfsDebtAmount()));
            detailsResource.setLoanPurpose(resource.getLoanApplication().getLoanPurpose());
            detailsResource.setGroupCompanyName(resource.getLoanApplication().getGroupCompany());
            detailsResource.setPromoterName(resource.getLoanApplication().getPromoterName());
            detailsResource.setPromoterPATInCrores(resource.getLoanApplication().getPromoterPATAmount() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getPromoterPATAmount()));
            detailsResource.setPromoterAreaOfBusiness(resource.getLoanApplication().getPromoterAreaOfBusinessNature());
            detailsResource.setPromoterRating(resource.getLoanApplication().getRating());
            detailsResource.setPromoterNetWorthInCrores(resource.getLoanApplication().getPromoterNetWorthAmount() == null? "0.000":
                    String.format("%.3f", resource.getLoanApplication().getPromoterNetWorthAmount()));
            detailsResource.setPromoterKeyDirector(resource.getLoanApplication().getPromoterKeyDirector());
            detailsResource.setLoanStatus(Integer.toString(resource.getLoanApplication().getFunctionalStatus()));
            detailsResource.setProjectName(resource.getPartner().getPartyName1());
            detailsResource.setLoanOfficer(resource.getLoanApplication().getUserBPNumber());
            detailsResource.setLoanProduct(resource.getLoanApplication().getProductCode());
            SAPLoanApplicationResource d = new SAPLoanApplicationResource();
            d.setSapLoanApplicationDetailsResource(detailsResource);
            integrationService.postLoanApplication(d);

            BeanUtils.copyProperties(resource.getLoanApplication(), loanApplication,"id", "enquiryNo");
            BeanUtils.copyProperties(resource.getPartner(), partner,"id");
            loanApplication.approve();
            loanApplication = loanApplicationRepository.save(loanApplication);
            partner = partnerRepository.save(partner);
            resource.setLoanApplication(loanApplication);
            resource.setPartner(partner);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }

        return ResponseEntity.ok(resource);
    }

    @PutMapping("/loanApplications/{id}/reject")
    public ResponseEntity update(@PathVariable("id") LoanApplication loanApplication, @RequestBody EnquiryRejectReason enquiryRejectReason, HttpServletRequest request) {
        Partner partner = partnerRepository.findByUserName(request.getUserPrincipal().getName());
        loanApplication.reject(enquiryRejectReason.getRejectReason(), partner);
        loanApplication = loanApplicationRepository.save(loanApplication);
        return ResponseEntity.ok(loanApplication);
    }

    @PutMapping("/loanApplications/{id}/cancel")
    public ResponseEntity cancel(@PathVariable("id") LoanApplication loanApplication, HttpServletRequest request) {
        Partner partner = partnerRepository.findByUserName(request.getUserPrincipal().getName());
        // Set functional status to 9 (cancelled).
        loanApplication.setFunctionalStatus(9);
        loanApplication = loanApplicationRepository.save(loanApplication);
        return ResponseEntity.ok(loanApplication);
    }

    @PutMapping("/loanApplications/search")
    public ResponseEntity search(@RequestBody SearchResource resource, HttpServletRequest request,
                                 @PageableDefault(sort = {"enquiryNo ASC"}) Pageable pageable)
    {
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

        User user;
        if(request.getUserPrincipal().getName().equals("admin")) {
            user = userRepository.findByEmail("admin@gmail.com");
        }
        else {
            user = userRepository.findByEmail(request.getUserPrincipal().getName());
        }

        if (user.getRole().equals("TR0100")) {
            Partner partner = partnerRepository.findByEmail(user.getEmail());
            if (partner != null) {
                loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanApplicant()
                        .equals(partner.getId())).collect(Collectors.toList());
            }
        }

        List<LoanApplicationResource> resources = new ArrayList<>(0);
        loanApplications.forEach(loanApplication -> {
            Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
            resources.add(new LoanApplicationResource(loanApplication,partner));
        });

        return ResponseEntity.ok(resources);
    }
}
