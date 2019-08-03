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
        if (applicant == null) {
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

        LoanApplication loanApplicationExisting = new LoanApplication();

        // Check if loan application is existing
        if (loanApplication.getId() != null) {
            loanApplicationExisting = loanApplicationRepository.getOne(loanApplication.getId());
        }

        if (loanApplicationExisting != null) {

            loanApplicationExisting.setFunctionalStatus(loanApplication.getFunctionalStatus());

            if (loanApplication.getTechnicalStatus() != null)
                loanApplicationExisting.setTechnicalStatus(loanApplication.getTechnicalStatus());

            loanApplicationExisting.setAssistanceType(loanApplication.getAssistanceType());
            loanApplicationExisting.setBusPartnerNumber(loanApplication.getbusPartnerNumber());
            loanApplicationExisting.setDecisionDate(loanApplication.getDecisionDate());

            loanApplicationExisting.setId(loanApplication.getId());
            loanApplicationExisting.setEnquiryNo(loanApplication.getEnquiryNo());
            loanApplicationExisting.setEquity(loanApplication.getEquity());

            loanApplicationExisting.setExpectedInterestRate(loanApplication.getExpectedInterestRate());
            loanApplicationExisting.setExpectedSubDebt(loanApplication.getExpectedSubDebt());
            loanApplicationExisting.setFinalDecisionStatus(loanApplication.getFinalDecisionStatus());
            loanApplicationExisting.setFinancingType(loanApplication.getFinancingType());
            loanApplicationExisting.setAssistanceType(loanApplication.getAssistanceType());

            loanApplicationExisting.setGroupCompany(loanApplication.getGroupCompany());
            loanApplicationExisting.setKeyPromoter(loanApplication.getKeyPromoter());
            loanApplicationExisting.setLeadFILoanAmount(loanApplication.getLeadFILoanAmount());
            loanApplicationExisting.setLeadFIName(loanApplication.getLeadFIName());

            loanApplicationExisting.setLoanEnquiryDate(loanApplication.getLoanEnquiryDate());
            loanApplicationExisting.setLoanClass(loanApplication.getLoanClass());
            loanApplicationExisting.setLoanPurpose(loanApplication.getLoanPurpose());
            loanApplicationExisting.setPfsDebtAmount(loanApplication.getPfsDebtAmount());
            loanApplicationExisting.setPfsSubDebtAmount(loanApplication.getPfsSubDebtAmount());

            // Keep initiators as is
            // loanApplicationExisting.setMonitoringDepartmentInitiator(loanApplication.)

            loanApplicationExisting.setProductCode(loanApplication.getProductCode());
            loanApplicationExisting.setProjectAmountCurrency(loanApplication.getProjectAmountCurrency());
            loanApplicationExisting.setProjectCapacity(loanApplication.getProjectCapacity());
            loanApplicationExisting.setProjectCapacityUnit(loanApplication.getProjectCapacityUnit());
            loanApplicationExisting.setProjectCost(loanApplication.getProjectCost());
            loanApplicationExisting.setProjectDebtAmount(loanApplication.getProjectDebtAmount());
            loanApplicationExisting.setProjectDistrict(loanApplication.getProjectDistrict());
            loanApplicationExisting.setProjectName(loanApplication.getProjectName());
            loanApplicationExisting.setProjectLocationState(loanApplication.getProjectLocationState());
            loanApplicationExisting.setProjectType(loanApplication.getProjectType());

            loanApplicationExisting.setPromoterAreaOfBusinessNature(loanApplication.getPromoterAreaOfBusinessNature());
            loanApplicationExisting.setPromoterKeyDirector(loanApplication.getPromoterKeyDirector());
            loanApplicationExisting.setPromoterName(loanApplication.getPromoterName());
            loanApplicationExisting.setPromoterNetWorthAmount(loanApplication.getPromoterNetWorthAmount());
            loanApplicationExisting.setPromoterPATAmount(loanApplication.getPromoterPATAmount());

            loanApplicationExisting.setRating(loanApplication.getRating());
            if (loanApplication.getRejectionDate() != null)
                loanApplicationExisting.setRejectionDate(loanApplication.getRejectionDate());
            if (loanApplication.getRejectionReason() != null)
                loanApplicationExisting.setRejectionReason(loanApplication.getRejectionReason());
            if (loanApplication.getScheduledCOD() != null)
                loanApplicationExisting.setScheduledCOD(loanApplication.getScheduledCOD());

            if (loanApplication.getUserBPNumber() != null)
                loanApplicationExisting.setUserBPNumber(loanApplicationExisting.getUserBPNumber());

            if (loanApplication.getProjectDepartmentInitiator() != null)
                loanApplicationExisting.setProjectDepartmentInitiator(loanApplication.getProjectDepartmentInitiator());
            if (loanApplication.getMonitoringDepartmentInitiator() != null)
                loanApplicationExisting.setMonitoringDepartmentInitiator(loanApplication.getMonitoringDepartmentInitiator());
            if (loanApplicationExisting.getRiskDepartmentInitiator() != null)
                loanApplicationExisting.setRiskDepartmentInitiator(loanApplication.getRiskDepartmentInitiator());


            loanApplication.applicant(applicant);
            loanApplication.created(applicant);

            //Save and return the Loan Application
            loanApplication = loanApplicationRepository.save(loanApplicationExisting);

        } else {

            loanApplication.applicant(applicant);
            loanApplication.created(applicant);

            //Save and return the Loan Application
            loanApplication = loanApplicationRepository.save(loanApplication);
        }

        System.out.println("Loan Application :" + loanApplication);
        return loanApplication;
    }

    @Override
    public LoanApplication migrate(LoanApplicationResource resource, String username) {


        System.out.println("-------------- Migrating Loan number : " + resource.getLoanApplication().getLoanContractId() + "-----------------------------------------------------------");
        System.out.println("-------------- BusinessPartner ID    : " + resource.getPartner().getPartyNumber() + "-----------------------------------------------------------");

        Partner partner = new Partner();
        //resource.getPartner();

        // Temporary solution to get data migration done for BusinessPartners with empty email id
        if (resource.getPartner().getEmail() == null || resource.getPartner().getEmail().equals("")) {
            partner.setUserName(resource.getLoanApplication().getLoanContractId() + resource.getPartner().getPartyName1());
            partner.setEmail(resource.getLoanApplication().getLoanContractId() + resource.getPartner().getPartyName1());
        } else {
            partner.setUserName(resource.getPartner().getEmail());
            partner.setEmail(resource.getPartner().getEmail());
        }

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
        partner = partnerService.migrate(partner);

        //Set it to the Loan Application

        LoanApplication loanApplication = resource.getLoanApplication();


        loanApplication.applicant(partner);
        loanApplication.created(partner);

        //Save and return the Loan Application
        loanApplication = this.migrateUpdate(loanApplication,partner,username);

        System.out.println("-------------Finished Migrating Loan number : " + resource.getLoanApplication().getLoanContractId() + "-----------------------------------------------------------");

        return loanApplication;
    }


    @Override
    public LoanApplication migrateUpdate(LoanApplication loanApplication, Partner partner, String username) {


        LoanApplication loanApplicationExisting = new LoanApplication();

        // Check if loan application is existing
        if (loanApplication.getLoanContractId() != null) {
            loanApplicationExisting = loanApplicationRepository.findByLoanContractId(loanApplication.getLoanContractId());
        }

        if (loanApplicationExisting != null) {

            loanApplicationExisting.setFunctionalStatus(loanApplication.getFunctionalStatus());

            if (loanApplication.getTechnicalStatus() != null)
                loanApplicationExisting.setTechnicalStatus(loanApplication.getTechnicalStatus());

            loanApplicationExisting.setAssistanceType(loanApplication.getAssistanceType());
            loanApplicationExisting.setBusPartnerNumber(loanApplication.getbusPartnerNumber());
            loanApplicationExisting.setDecisionDate(loanApplication.getDecisionDate());

            loanApplicationExisting.setId(loanApplication.getId());
            loanApplicationExisting.setEnquiryNo(loanApplication.getEnquiryNo());
            loanApplicationExisting.setEquity(loanApplication.getEquity());

            loanApplicationExisting.setExpectedInterestRate(loanApplication.getExpectedInterestRate());
            loanApplicationExisting.setExpectedSubDebt(loanApplication.getExpectedSubDebt());
            loanApplicationExisting.setFinalDecisionStatus(loanApplication.getFinalDecisionStatus());
            loanApplicationExisting.setFinancingType(loanApplication.getFinancingType());
            loanApplicationExisting.setAssistanceType(loanApplication.getAssistanceType());

            loanApplicationExisting.setGroupCompany(loanApplication.getGroupCompany());
            loanApplicationExisting.setKeyPromoter(loanApplication.getKeyPromoter());
            loanApplicationExisting.setLeadFILoanAmount(loanApplication.getLeadFILoanAmount());
            loanApplicationExisting.setLeadFIName(loanApplication.getLeadFIName());

            loanApplicationExisting.setLoanEnquiryDate(loanApplication.getLoanEnquiryDate());
            loanApplicationExisting.setLoanClass(loanApplication.getLoanClass());
            loanApplicationExisting.setLoanPurpose(loanApplication.getLoanPurpose());
            loanApplicationExisting.setPfsDebtAmount(loanApplication.getPfsDebtAmount());
            loanApplicationExisting.setPfsSubDebtAmount(loanApplication.getPfsSubDebtAmount());

            // Keep initiators as is
            // loanApplicationExisting.setMonitoringDepartmentInitiator(loanApplication.)

            loanApplicationExisting.setProductCode(loanApplication.getProductCode());
            loanApplicationExisting.setProjectAmountCurrency(loanApplication.getProjectAmountCurrency());
            loanApplicationExisting.setProjectCapacity(loanApplication.getProjectCapacity());
            loanApplicationExisting.setProjectCapacityUnit(loanApplication.getProjectCapacityUnit());
            loanApplicationExisting.setProjectCost(loanApplication.getProjectCost());
            loanApplicationExisting.setProjectDebtAmount(loanApplication.getProjectDebtAmount());
            loanApplicationExisting.setProjectDistrict(loanApplication.getProjectDistrict());
            loanApplicationExisting.setProjectName(loanApplication.getProjectName());
            loanApplicationExisting.setProjectLocationState(loanApplication.getProjectLocationState());
            loanApplicationExisting.setProjectType(loanApplication.getProjectType());

            loanApplicationExisting.setPromoterAreaOfBusinessNature(loanApplication.getPromoterAreaOfBusinessNature());
            loanApplicationExisting.setPromoterKeyDirector(loanApplication.getPromoterKeyDirector());
            loanApplicationExisting.setPromoterName(loanApplication.getPromoterName());
            loanApplicationExisting.setPromoterNetWorthAmount(loanApplication.getPromoterNetWorthAmount());
            loanApplicationExisting.setPromoterPATAmount(loanApplication.getPromoterPATAmount());

            loanApplicationExisting.setRating(loanApplication.getRating());
            if (loanApplication.getRejectionDate() != null)
                loanApplicationExisting.setRejectionDate(loanApplication.getRejectionDate());
            if (loanApplication.getRejectionReason() != null)
                loanApplicationExisting.setRejectionReason(loanApplication.getRejectionReason());
            if (loanApplication.getScheduledCOD() != null)
                loanApplicationExisting.setScheduledCOD(loanApplication.getScheduledCOD());

            if (loanApplication.getUserBPNumber() != null)
                loanApplicationExisting.setUserBPNumber(loanApplicationExisting.getUserBPNumber());

            if (loanApplication.getProjectDepartmentInitiator() != null)
                loanApplicationExisting.setProjectDepartmentInitiator(loanApplication.getProjectDepartmentInitiator());
            if (loanApplication.getMonitoringDepartmentInitiator() != null)
                loanApplicationExisting.setMonitoringDepartmentInitiator(loanApplication.getMonitoringDepartmentInitiator());
            if (loanApplicationExisting.getRiskDepartmentInitiator() != null)
                loanApplicationExisting.setRiskDepartmentInitiator(loanApplication.getRiskDepartmentInitiator());

            loanApplicationExisting.created(partner);
            loanApplicationExisting.applicant(partner);

            //Save and return the Loan Application
            loanApplication = loanApplicationRepository.save(loanApplicationExisting);

        } else {

//            loanApplication.applicant(applicant);
//            loanApplication.created(applicant);

            //Save and return the Loan Application
            loanApplication = loanApplicationRepository.save(loanApplication);
        }

        System.out.println("Loan Application :" + loanApplication);
        return loanApplication;
    }

}
