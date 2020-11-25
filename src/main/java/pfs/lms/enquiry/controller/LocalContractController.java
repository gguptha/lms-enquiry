package pfs.lms.enquiry.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.repository.StateRepository;
import pfs.lms.enquiry.repository.UserRepository;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.resource.LoanContractSearchResource;

import pfs.lms.enquiry.service.ILoanApplicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor

public class LocalContractController {

    private final ILoanApplicationService loanApplicationService;

    private final PartnerRepository partnerRepository;

    private final UserRepository userRepository;

    private final StateRepository stateRepository;


    @PutMapping("/loanContracts/search")
    public ResponseEntity search(@RequestBody LoanContractSearchResource resource, HttpServletRequest request,
                                 @PageableDefault(sort = "loanContractId", size = 9999, direction = Sort.Direction.DESC) Pageable pageable)
    {
        //List<LoanApplication> loanApplications = new ArrayList<>(loanApplicationRepository.findAll(pageable).getContent());
        //System.out.println("Search Loans............: search resource: " + resource);
        List<LoanApplication> loanApplications = new ArrayList<>(loanApplicationService.searchLoans(request,pageable));


/*
        if (resource.getEnquiryDateTo() == null){
            if (resource.getEnquiryDateFrom() != null) {
                resource.setEnquiryDateTo(resource.getEnquiryDateFrom().plusDays(1));

            }
        }

        if (resource.getEnquiryDateFrom() != null && resource.getEnquiryDateTo() != null) {
            loanApplications = loanApplications.stream()
                    .filter(loanApplication -> (
                            loanApplication.getLoanEnquiryDate().isAfter(resource.getEnquiryDateFrom()) || loanApplication.getLoanEnquiryDate().equals(resource.getEnquiryDateFrom())) &&
                            (loanApplication.getLoanEnquiryDate().isBefore(resource.getEnquiryDateTo()) || loanApplication.getLoanEnquiryDate().equals(resource.getEnquiryDateTo())))
                    .collect(Collectors
                            .toList());
        }
*/

/*
        if (resource.getEnquiryDateFrom() != null && resource.getEnquiryDateTo() == null) {
            loanApplications = loanApplications.stream()
                    .filter(loanApplication -> (
                            loanApplication.getLoanEnquiryDate().isEqual(resource.getEnquiryDateFrom())))
                    .collect(Collectors
                            .toList());
        }
*/


//to be modified to update status

        if (resource.getBorrowerCodeFrom() != null && resource.getBorrowerCodeTo() == null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getbusPartnerNumber() != null
                    && !loanApplication.getbusPartnerNumber().isEmpty())
                    .filter(loanApplication -> loanApplication.getbusPartnerNumber().contains(resource.getBorrowerCodeFrom() + "")).collect(Collectors.toList());
        }
        else if (resource.getBorrowerCodeFrom() == null && resource.getBorrowerCodeTo() != null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getbusPartnerNumber() != null
                    && !loanApplication.getbusPartnerNumber().isEmpty())
                    .filter(loanApplication -> loanApplication.getbusPartnerNumber().contains(resource.getBorrowerCodeTo() + "")).collect(Collectors.toList());
        }
        else if (resource.getBorrowerCodeFrom() != null && resource.getBorrowerCodeTo() != null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getbusPartnerNumber() != null && !loanApplication.getbusPartnerNumber().isEmpty()).filter(loanApplication -> new Integer(loanApplication.getbusPartnerNumber()) >=
                    resource.getBorrowerCodeFrom()).collect(Collectors.toList());
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getbusPartnerNumber() != null && !loanApplication.getbusPartnerNumber().isEmpty()).filter(loanApplication -> new Integer(loanApplication.getbusPartnerNumber()) <=
                    resource.getBorrowerCodeTo()).collect(Collectors.toList());
        }

        if (resource.getLoanNumberFrom() != null && resource.getLoanNumberTo() == null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanContractId() != null
                    && !loanApplication.getLoanContractId().isEmpty())
                    .filter(loanApplication -> loanApplication.getLoanContractId().contains(resource.getLoanNumberFrom() + "")).collect(Collectors.toList());
        }
        else if (resource.getLoanNumberFrom() == null && resource.getLoanNumberTo() != null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanContractId() != null
                    && !loanApplication.getLoanContractId().isEmpty())
                    .filter(loanApplication -> loanApplication.getLoanContractId().contains(resource.getLoanNumberTo() + "")).collect(Collectors.toList());
        }
        else if (resource.getLoanNumberFrom() != null && resource.getLoanNumberTo() != null) {
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanContractId() != null && !loanApplication.getLoanContractId().isEmpty()).filter(loanApplication -> new Integer(loanApplication.getLoanContractId()) >=
                    resource.getLoanNumberFrom()).collect(Collectors.toList());
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanContractId() != null && !loanApplication.getLoanContractId().isEmpty()).filter(loanApplication -> new Integer(loanApplication.getLoanContractId()) <=
                    resource.getLoanNumberTo()).collect(Collectors.toList());
        }

        if (resource.getPartyName() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectName().toLowerCase().contains(resource.getPartyName().toLowerCase())).collect(Collectors.toList());

        if (resource.getLoanClass() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getLoanClass().equals(resource.getLoanClass())).collect(Collectors.toList());

        if (resource.getFinancingType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getFinancingType().equals(resource.getFinancingType())).collect(Collectors.toList());

        if (resource.getProjectLocationState() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectLocationState().equals(resource.getProjectLocationState())).collect(Collectors.toList());

        if (resource.getProjectType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication -> loanApplication.getProjectType().equals(resource.getProjectType())).collect(Collectors.toList());

        if (resource.getAssistanceType() != null)
            loanApplications = loanApplications.stream().filter(loanApplication ->
                    loanApplication.getAssistanceType()
                            .equals(resource.getAssistanceType()))
                    .collect(Collectors.toList());


        if (resource.getTechnicalStatus() != null)
            loanApplications = loanApplications
                    .stream()
                    .filter(
                            loanApplication ->
                                    loanApplication.getTechnicalStatus()
                                            == Integer.parseInt(resource.getTechnicalStatus() ))
                    .collect(Collectors.toList());

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


        // System.out.println("-------------- Loans Applications Count :" + loanApplications.size());

        loanApplications.forEach(loanApplication -> {
            //System.out.println("Loan Contract Id     : " + loanApplication.getLoanContractId());
            //System.out.println("Applicant Details    : " + loanApplication.getLoanApplicant());

            if (loanApplication.getLoanApplicant() != null) {
                Partner partner = partnerRepository.findById(loanApplication.getLoanApplicant()).get();
                if (partner == null) {
                    //System.out.println("-------------- Partner is null for loan :" + loanApplication.getLoanContractId());
                }
                if (loanApplication.getTechnicalStatus() != null) {
                    switch (loanApplication.getTechnicalStatus()) {
                        case 1:
                            loanApplication.setTechnicalStatusDescription("Created");
                            break;
                        case 2:
                            loanApplication.setTechnicalStatusDescription("Modified");
                            break;
                        case 3:
                            loanApplication.setTechnicalStatusDescription("Submitted");
                            break;
                        case 4:
                            loanApplication.setTechnicalStatusDescription("Taken up for Processing");
                            break;
                        case 5:
                            loanApplication.setTechnicalStatusDescription("Cancelled");
                            break;
                        case 6:
                            loanApplication.setTechnicalStatusDescription("Rejected");
                            break;
                    }
                }

                resources.add(new LoanApplicationResource(loanApplication, partner));
            }
        });

        // Set the project location state name
        for ( LoanApplicationResource loanApplicationResource : resources) {
            if (loanApplicationResource.getLoanApplication().getProjectLocationState() != null)
                if (loanApplicationResource.getLoanApplication().getProjectLocationState().length() == 2) {
                    loanApplicationResource.getLoanApplication().setProjectLocationState(
                            stateRepository.findByCode(loanApplicationResource.getLoanApplication().getProjectLocationState()).getName());
                }

        }


        //System.out.println("-------------- All Loans Prepareed...... Ready for Return............. ");

        return ResponseEntity.ok(resources);
    }


}
