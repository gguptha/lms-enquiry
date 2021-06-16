package pfs.lms.enquiry.appraisal.loanpartner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.repository.LoanApplicationRepository;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class LoanPartnerController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanPartnerRepository loanPartnerRepository;

    @PostMapping("/loanPartners/create")
    public ResponseEntity<LoanPartner> createLoanPartner(@RequestBody LoanPartnerResource loanPartnerResource,
                                                         HttpServletRequest request) {

        LoanApplication loanApplication = loanApplicationRepository.findById(loanPartnerResource.getLoanApplicationId())
                .orElseThrow(() -> new EntityNotFoundException(loanPartnerResource.getLoanApplicationId().toString()));
        LoanPartner loanPartner = new LoanPartner();
        loanPartner.setSerialNumber(loanPartnerRepository.findByLoanApplicationId(loanPartnerResource
                .getLoanApplicationId()).size() + 1);
        loanPartner.setLoanApplication(loanApplication);
        loanPartner.setBusinessPartnerId(loanPartnerResource.getBusinessPartnerId());
        loanPartner.setRoleType(loanPartnerResource.getRoleType());
        loanPartner.setStartDate(loanPartnerResource.getStartDate());
        loanPartner = loanPartnerRepository.save(loanPartner);
        return ResponseEntity.ok(loanPartner);
    }

    @PutMapping("/loanPartners/update")
    public ResponseEntity<LoanPartner> updateLoanPartner(@RequestBody LoanPartnerResource loanPartnerResource,
                                                         HttpServletRequest request) {

        LoanPartner loanPartner = loanPartnerRepository.findById(loanPartnerResource.getId())
                .orElseThrow(() -> new EntityNotFoundException(loanPartnerResource.getId().toString()));
        loanPartner.setBusinessPartnerId(loanPartnerResource.getBusinessPartnerId());
        loanPartner.setRoleType(loanPartnerResource.getRoleType());
        loanPartner.setStartDate(loanPartnerResource.getStartDate());
        loanPartner = loanPartnerRepository.save(loanPartner);
        return ResponseEntity.ok(loanPartner);
    }
}
