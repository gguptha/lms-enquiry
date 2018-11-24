package pfs.lms.enquiry.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanApplicationStatus;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.LoanApplicationStatusRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.resource.SAPLoanApplicationDetailsResource;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationEngine {
    
    private  final ISAPIntegrationService integrationService;

    private final LoanApplicationStatusRepository loanApplicationStatusRepository;
    
    private final PartnerRepository partnerRepository;

    private final LoanApplicationRepository loanApplicationRepository;

    @EventListener
    public void onLoanApplicationCreated(LoanApplication.LoanApplicationCreated loanApplicationCreated){
        loanApplicationStatusRepository.save(new LoanApplicationStatus(loanApplicationCreated.getLoanApplication()));
    }

    @EventListener
    public void onLoanApplicationRejected(LoanApplication.LoanApplicationRejected loanApplicationRejected){
        loanApplicationStatusRepository.save(LoanApplicationStatus.reject(loanApplicationRejected.getLoanApplication()));
    }

    @EventListener
    public LoanApplication onLoanApplicationApproved(LoanApplication.LoanApplicationApproved loanApplicationApproved){
        LoanApplication loanApplication = loanApplicationApproved.getLoanApplication();
        Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
        SAPLoanApplicationDetailsResource detailsResource = new SAPLoanApplicationDetailsResource();
        detailsResource.setLoanApplicationId(Long.toString(loanApplication.getEnquiryNo().getId()));
        detailsResource.setPartnerExternalNumber("1");
        detailsResource.setPartnerRole("TR0100");
        detailsResource.setFirstname(partner.getPartyName1());
        detailsResource.setLastname(partner.getPartyName2() == null? "":
                partner.getPartyName2());
        detailsResource.setEmail(partner.getEmail());
        detailsResource.setCity(partner.getCity());
        detailsResource.setPostalCode(partner.getPostalCode());
        detailsResource.setHouseNo(partner.getAddressLine1());
        detailsResource.setStreet(partner.getStreet());
        detailsResource.setCountry("IN");
        detailsResource.setContactPerName(partner.getContactPersonName());

        detailsResource.setApplicationDate("\\/Date(1228089600000)\\/");
        detailsResource.setLoanClass(loanApplication.getLoanClass());
        detailsResource.setFinancingType(loanApplication.getFinancingType());
        detailsResource.setDebtEquityIndicator("X");
        detailsResource.setProjectCapaacity(loanApplication.getProjectCapacity() == null? "0.00":
                String.format("%.2f", loanApplication.getProjectCapacity()));
        detailsResource.setProjectCapacityUnit("MW");
        detailsResource.setScheduledCommDate("\\/Date(1228089600000)\\/");
        detailsResource.setProjectCostInCrores(loanApplication.getProjectCost() == null? "0.000":
                String.format("%.3f", loanApplication.getProjectCost()));
        detailsResource.setDebtAmountInCrores(loanApplication.getProjectDebtAmount() == null? "0.000":
                String.format("%.3f", loanApplication.getProjectDebtAmount()));
        detailsResource.setEquityAmountInCrores(loanApplication.getEquity() == null? "0.000":
                String.format("%.3f", loanApplication.getEquity()));
        detailsResource.setCurrency("INR");
        detailsResource.setApplicationCapitalInCrores(loanApplication.getPfsDebtAmount() == null? "0.000":
                String.format("%.3f", loanApplication.getPfsDebtAmount()));
        // detailsResource.setLoanPurpose(loanApplication.getLoanPurpose());
        // Send empty string for loan purpose. Will be handled at SAP.
        detailsResource.setLoanPurpose("");
        detailsResource.setGroupCompanyName(loanApplication.getGroupCompany());
        detailsResource.setPromoterName(loanApplication.getPromoterName());
        detailsResource.setPromoterPATInCrores(loanApplication.getPromoterPATAmount() == null? "0.000":
                String.format("%.3f", loanApplication.getPromoterPATAmount()));
        detailsResource.setPromoterAreaOfBusiness(loanApplication.getPromoterAreaOfBusinessNature());
        detailsResource.setPromoterRating(loanApplication.getRating());
        detailsResource.setPromoterNetWorthInCrores(loanApplication.getPromoterNetWorthAmount() == null? "0.000":
                String.format("%.3f", loanApplication.getPromoterNetWorthAmount()));
        detailsResource.setPromoterKeyDirector(loanApplication.getPromoterKeyDirector());
        detailsResource.setLoanStatus(Integer.toString(loanApplication.getFunctionalStatus()));
        detailsResource.setProjectName(partner.getPartyName1());
        detailsResource.setLoanOfficer(loanApplication.getUserBPNumber());
        detailsResource.setLoanProduct(loanApplication.getProductCode());
        SAPLoanApplicationResource d = new SAPLoanApplicationResource();
        d.setSapLoanApplicationDetailsResource(detailsResource);
        SAPLoanApplicationResource sapLoanApplicationResourceResponse = integrationService.postLoanApplication(d);
        loanApplication.responseFromSAP(sapLoanApplicationResourceResponse);
        loanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplication;
    }
}
