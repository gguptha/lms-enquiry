package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.service.ILoanApplicationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationService implements ILoanApplicationService {

    private final PartnerService partnerService;

    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public LoanApplication save(LoanApplicationResource resource, String username) {

        //Get the partner from partner service
        Partner applicant = partnerService.getOne(username);
        if(applicant == null) {
            applicant = new Partner();
            applicant.setUserName(username);
            applicant.setPartyRole("TR0100");
        }
        applicant.setAddressLine1(resource.getPartner().getAddressLine1());
        applicant.setAddressLine2(resource.getPartner().getAddressLine2());
        applicant.setCity(resource.getPartner().getCity());
        applicant.setContactNumber(resource.getPartner().getContactNumber());
        applicant.setContactPersonName(resource.getPartner().getContactPersonName());
        applicant.setCountry(resource.getPartner().getCountry());
        applicant.setGroupCompany(resource.getPartner().getGroupCompany());
        applicant.setPan(resource.getPartner().getPan());
        applicant.setPartyCategory(resource.getPartner().getPartyCategory());
        applicant.setPartyName1(resource.getPartner().getPartyName1());
        applicant.setPartyName2(resource.getPartner().getPartyName2());
        applicant.setPartyNumber(resource.getPartner().getPartyNumber());
        applicant.setPostalCode(resource.getPartner().getPostalCode());
        applicant.setState(resource.getPartner().getState());
        applicant.setStreet(resource.getPartner().getStreet());
        applicant.setEmail(resource.getPartner().getEmail());
        applicant = partnerService.save(applicant);

        // Partner applicant = partnerService.save(resource.getPartner()); // delete
        // Partner app = partnerService.getOne(username); // delete

        //Set it to the Loan Application
        LoanApplication loanApplication = resource.getLoanApplication();
        loanApplication.applicant(applicant);
        loanApplication.created(applicant);

        //Save and return the Loan Application
        loanApplication = loanApplicationRepository.save(loanApplication);

        System.out.println("Loan Application :" + loanApplication);
        return loanApplication;
    }

    @Override
    public LoanApplication migrate(LoanApplicationResource resource, String username) {


       System.out.println("-------------- Migrating Loan number: " + resource.getLoanApplication().getLoanContractId() + "-----------------------------------------------------------");

        Partner partner = new Partner();
        //resource.getPartner();

        partner.setUserName("MigrationUser");
        partner.setPartyRole("TR0100");
        partner.setAddressLine1(resource.getPartner().getAddressLine1());
        partner.setAddressLine2(resource.getPartner().getAddressLine2());
        partner.setCity(resource.getPartner().getCity());
        partner.setContactNumber(resource.getPartner().getContactNumber());
        partner.setContactPersonName(resource.getPartner().getContactPersonName());
        partner.setCountry(resource.getPartner().getCountry());
        partner.setGroupCompany(resource.getPartner().getGroupCompany());
        partner.setPan(resource.getPartner().getPan());
        partner.setPartyCategory(resource.getPartner().getPartyCategory());
        partner.setPartyName1(resource.getPartner().getPartyName1());
        partner.setPartyName2(resource.getPartner().getPartyName2());
        partner.setPartyNumber(resource.getPartner().getPartyNumber());
        partner.setPostalCode(resource.getPartner().getPostalCode());
        partner.setState(resource.getPartner().getState());
        partner.setStreet(resource.getPartner().getStreet());
        partner.setEmail(resource.getPartner().getEmail());
        partner = partnerService.save(partner);

        //Set it to the Loan Application
        LoanApplication loanApplication = resource.getLoanApplication();
        loanApplication.applicant(partner);
        loanApplication.created(partner);

        //Save and return the Loan Application
        loanApplication = loanApplicationRepository.save(loanApplication);

        return loanApplication;
    }
}
