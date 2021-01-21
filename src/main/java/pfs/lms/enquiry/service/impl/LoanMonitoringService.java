package pfs.lms.enquiry.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.*;
import pfs.lms.enquiry.monitoring.lfa.*;
import pfs.lms.enquiry.monitoring.lie.*;
import pfs.lms.enquiry.monitoring.tra.*;
import pfs.lms.enquiry.repository.*;
import pfs.lms.enquiry.resource.*;
import pfs.lms.enquiry.service.ILoanMonitoringService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class LoanMonitoringService implements ILoanMonitoringService {

    private final LIERepository lieRepository;
    private  final LoanMonitorRepository loanMonitorRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    private final LIEReportAndFeeRepository lieReportAndFeeRepository;

    private final LFARepository lfaRepository;
    private final LFAReportAndFeeRepository lfaReportAndFeeRepository;

    private final TRARepository traRepository;
    private final TRAStatementRepository traStatementRepository;

    private final TermsAndConditionsRepository termsAndConditionsRepository;

    private final SecurityComplianceRepository securityComplianceRepository;

    private final SiteVisitRepository siteVisitRepository;

    private final OperatingParameterRepository operatingParameterRepository;

    private final RateOfInterestRepository rateOfInterestRepository;

    private final BorrowerFinancialsRepository borrowerFinancialsRepository;

    private final PromoterFinancialsRepository promoterFinancialsRepository;

    private final FinancialCovenantsRepository financialCovenantsRepository;

    private final PromoterDetailsRepository promoterDetailsRepository;

    @Override
    @Transactional
    public LendersIndependentEngineer saveLIE(LIEResource resource, String username) {

        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        LendersIndependentEngineer lendersIndependentEngineer = resource.getLendersIndependentEngineer();
        lendersIndependentEngineer.setLoanMonitor(loanMonitor);
        lendersIndependentEngineer.setAdvisor(resource.getLendersIndependentEngineer().getAdvisor());
        lendersIndependentEngineer.setBpCode(resource.getLendersIndependentEngineer().getBpCode());
        lendersIndependentEngineer.setName(resource.getLendersIndependentEngineer().getName());
        lendersIndependentEngineer.setDateOfAppointment(resource.getLendersIndependentEngineer().getDateOfAppointment());
        lendersIndependentEngineer.setContractPeriodFrom(resource.getLendersIndependentEngineer().getContractPeriodFrom());
        lendersIndependentEngineer.setContractPeriodTo(resource.getLendersIndependentEngineer().getContractPeriodTo());
        lendersIndependentEngineer.setContactPerson(resource.getLendersIndependentEngineer().getContactPerson());
        lendersIndependentEngineer.setContactNumber(resource.getLendersIndependentEngineer().getContactNumber());
        lendersIndependentEngineer.setEmail(resource.getLendersIndependentEngineer().getEmail());
        lendersIndependentEngineer = lieRepository.save(lendersIndependentEngineer);
        return lendersIndependentEngineer;
    }

    @Override
    public LendersIndependentEngineer updateLIE(LIEResource resource, String username) {

        LendersIndependentEngineer existingLendersIndependentEngineer
                = lieRepository.getOne(resource.getLendersIndependentEngineer().getId());

        existingLendersIndependentEngineer.setAdvisor(resource.getLendersIndependentEngineer().getAdvisor());
        existingLendersIndependentEngineer.setBpCode(resource.getLendersIndependentEngineer().getBpCode());
        existingLendersIndependentEngineer.setName(resource.getLendersIndependentEngineer().getName());
        existingLendersIndependentEngineer.setDateOfAppointment(resource.getLendersIndependentEngineer().getDateOfAppointment());
        existingLendersIndependentEngineer.setContractPeriodFrom(resource.getLendersIndependentEngineer().getContractPeriodFrom());
        existingLendersIndependentEngineer.setContractPeriodTo(resource.getLendersIndependentEngineer().getContractPeriodTo());
        existingLendersIndependentEngineer.setContactPerson(resource.getLendersIndependentEngineer().getContactPerson());
        existingLendersIndependentEngineer.setContactNumber(resource.getLendersIndependentEngineer().getContactNumber());
        existingLendersIndependentEngineer.setEmail(resource.getLendersIndependentEngineer().getEmail());
        existingLendersIndependentEngineer = lieRepository.save(existingLendersIndependentEngineer);

        return existingLendersIndependentEngineer;
    }

    @Override
    public List<LIEResource> getLendersIndependentEngineers(String loanApplicationId, String name) {

        List<LIEResource> lendersIndependentEngineerResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<LendersIndependentEngineer> lendersIndependentEngineers
                    = lieRepository.findByLoanMonitor(loanMonitor);
            lendersIndependentEngineers.forEach(
                    lendersIndependentEngineer -> {
                        LIEResource lieResource = new LIEResource();
                        lieResource.setLoanApplicationId(loanApplication.getId());
                        lieResource.setLendersIndependentEngineer(lendersIndependentEngineer);
                        lendersIndependentEngineerResources.add(lieResource);
                    }
            );
        }
        return lendersIndependentEngineerResources;
    }

    @Override
    @Transactional
    public LIEReportAndFee saveLIEReportAndFee(LIEReportAndFeeResource resource, String username) {


        LendersIndependentEngineer lendersIndependentEngineer = lieRepository.getOne(resource.getLendersIndependentEngineerId());
        LIEReportAndFee lieReportAndFee = resource.getLieReportAndFee();
        lieReportAndFee.setLendersIndependentEngineer(lendersIndependentEngineer);
        lieReportAndFee = lieReportAndFeeRepository.save(lieReportAndFee);
        return lieReportAndFee;
    }

    @Override
    public LIEReportAndFee updateLIEReportAndFee(LIEReportAndFeeResource resource, String username) {

        LIEReportAndFee existinglieReportAndFee
                = lieReportAndFeeRepository.getOne(resource.getLieReportAndFee().getId());

        existinglieReportAndFee.setReportType(resource.getLieReportAndFee().getReportType());
        existinglieReportAndFee.setDateOfReceipt(resource.getLieReportAndFee().getDateOfReceipt());
        existinglieReportAndFee.setInvoiceDate(resource.getLieReportAndFee().getInvoiceDate());
        existinglieReportAndFee.setInvoiceNo(resource.getLieReportAndFee().getInvoiceNo());
        existinglieReportAndFee.setFeeAmount(resource.getLieReportAndFee().getFeeAmount());
        existinglieReportAndFee.setStatusOfFeeReceipt(resource.getLieReportAndFee().getStatusOfFeeReceipt());
        existinglieReportAndFee.setStatusOfFeePaid(resource.getLieReportAndFee().getStatusOfFeePaid());
        existinglieReportAndFee.setDocumentTitle(resource.getLieReportAndFee().getDocumentTitle());
        existinglieReportAndFee.setNextReportDate(resource.getLieReportAndFee().getNextReportDate());
        existinglieReportAndFee.setFileReference(resource.getLieReportAndFee().getFileReference());
        existinglieReportAndFee = lieReportAndFeeRepository.save(existinglieReportAndFee);

        return existinglieReportAndFee;
    }

    @Override
    public List<LIEReportAndFeeResource> getLIEReportAndFee(String lendersIndependentEngineerId, String name) {

        List<LIEReportAndFeeResource>  lieReportAndFeeResources  = new ArrayList<>();
        LendersIndependentEngineer lendersIndependentEngineer = lieRepository.getOne(lendersIndependentEngineerId);
        //LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(lendersIndependentEngineer != null) {
            List<LIEReportAndFee> lieReportAndFees
                    = lieReportAndFeeRepository.findByLendersIndependentEngineer(lendersIndependentEngineer);
            lieReportAndFees.forEach(
                    lieReportAndFee -> {
                        LIEReportAndFeeResource lieReportAndFeeResource = new LIEReportAndFeeResource();
                        //lieResource.setLoanApplicationId(loanApplication.getId());
                        lieReportAndFeeResource.setLendersIndependentEngineerId(lendersIndependentEngineer.getId());
                        lieReportAndFeeResource.setLieReportAndFee(lieReportAndFee);
                        lieReportAndFeeResources.add(lieReportAndFeeResource);
                    }
            );
        }
        return lieReportAndFeeResources;
    }


    //LFA

    @Override
    @Transactional
    public LendersFinancialAdvisor saveLFA(LFAResource resource, String username) {

        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        LendersFinancialAdvisor lendersFinancialAdvisor = resource.getLendersFinancialAdvisor();
        lendersFinancialAdvisor.setLoanMonitor(loanMonitor);
        lendersFinancialAdvisor.setBpCode(resource.getLendersFinancialAdvisor().getBpCode());
        lendersFinancialAdvisor.setName(resource.getLendersFinancialAdvisor().getName());
        lendersFinancialAdvisor.setDateOfAppointment(resource.getLendersFinancialAdvisor().getDateOfAppointment());
        lendersFinancialAdvisor.setContractPeriodFrom(resource.getLendersFinancialAdvisor().getContractPeriodFrom());
        lendersFinancialAdvisor.setContractPeriodTo(resource.getLendersFinancialAdvisor().getContractPeriodTo());
        lendersFinancialAdvisor.setContactPerson(resource.getLendersFinancialAdvisor().getContactPerson());
        lendersFinancialAdvisor.setContactNumber(resource.getLendersFinancialAdvisor().getContactNumber());
        lendersFinancialAdvisor.setEmail(resource.getLendersFinancialAdvisor().getEmail());
        lendersFinancialAdvisor = lfaRepository.save(lendersFinancialAdvisor);
        return lendersFinancialAdvisor;
    }

    @Override
    public LendersFinancialAdvisor updateLFA(LFAResource resource, String username) {

        LendersFinancialAdvisor existingLendersFinancialAdvisor
                = lfaRepository.getOne(resource.getLendersFinancialAdvisor().getId());

        existingLendersFinancialAdvisor.setBpCode(resource.getLendersFinancialAdvisor().getBpCode());
        existingLendersFinancialAdvisor.setName(resource.getLendersFinancialAdvisor().getName());
        existingLendersFinancialAdvisor.setDateOfAppointment(resource.getLendersFinancialAdvisor().getDateOfAppointment());
        existingLendersFinancialAdvisor.setContractPeriodFrom(resource.getLendersFinancialAdvisor().getContractPeriodFrom());
        existingLendersFinancialAdvisor.setContractPeriodTo(resource.getLendersFinancialAdvisor().getContractPeriodTo());
        existingLendersFinancialAdvisor.setContactPerson(resource.getLendersFinancialAdvisor().getContactPerson());
        existingLendersFinancialAdvisor.setContactNumber(resource.getLendersFinancialAdvisor().getContactNumber());
        existingLendersFinancialAdvisor.setEmail(resource.getLendersFinancialAdvisor().getEmail());
        existingLendersFinancialAdvisor = lfaRepository.save(existingLendersFinancialAdvisor);

        return existingLendersFinancialAdvisor;
    }

    @Override
    public List<LFAResource> getLendersFinancialAdvisors(String loanApplicationId, String name) {

        List<LFAResource> lendersFinancialAdvisorResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<LendersFinancialAdvisor> lendersFinancialAdvisors
                    = lfaRepository.findByLoanMonitor(loanMonitor);
            lendersFinancialAdvisors.forEach(
                    lendersFinancialAdvisor -> {
                        LFAResource lfaResource = new LFAResource();
                        lfaResource.setLoanApplicationId(loanApplication.getId());
                        lfaResource.setLendersFinancialAdvisor(lendersFinancialAdvisor);
                        lendersFinancialAdvisorResources.add(lfaResource);
                    }
            );
        }
        return lendersFinancialAdvisorResources;
    }

    @Override
    public LFAReportAndFee saveLFAReportAndFee(LFAReportAndFeeResource resource, String username) {
        LendersFinancialAdvisor lendersFinancialAdvisor = lfaRepository.getOne(resource.getLendersFinancialAdvisorId());
        LFAReportAndFee lfaReportAndFee = resource.getLfaReportAndFee();
        lfaReportAndFee.setLendersFinancialAdvisor(lendersFinancialAdvisor);
        lfaReportAndFee = lfaReportAndFeeRepository.save(lfaReportAndFee);
        return lfaReportAndFee;

    }

    @Override
    public LFAReportAndFee updateLFAReportAndFee(LFAReportAndFeeResource resource, String username) {
        LFAReportAndFee existinglfaReportAndFee
                = lfaReportAndFeeRepository.getOne(resource.getLfaReportAndFee().getId());

        existinglfaReportAndFee.setReportType(resource.getLfaReportAndFee().getReportType());
        existinglfaReportAndFee.setDateOfReceipt(resource.getLfaReportAndFee().getDateOfReceipt());
        existinglfaReportAndFee.setInvoiceDate(resource.getLfaReportAndFee().getInvoiceDate());
        existinglfaReportAndFee.setInvoiceNo(resource.getLfaReportAndFee().getInvoiceNo());
        existinglfaReportAndFee.setFeeAmount(resource.getLfaReportAndFee().getFeeAmount());
        existinglfaReportAndFee.setStatusOfFeeReceipt(resource.getLfaReportAndFee().getStatusOfFeeReceipt());
        existinglfaReportAndFee.setStatusOfFeePaid(resource.getLfaReportAndFee().getStatusOfFeePaid());
        existinglfaReportAndFee.setDocumentTitle(resource.getLfaReportAndFee().getDocumentTitle());
        existinglfaReportAndFee.setDocumentType(resource.getLfaReportAndFee().getDocumentType());
        existinglfaReportAndFee.setFileReference(resource.getLfaReportAndFee().getFileReference());
        existinglfaReportAndFee.setNextReportDate(resource.getLfaReportAndFee().getNextReportDate());
        existinglfaReportAndFee = lfaReportAndFeeRepository.save(existinglfaReportAndFee);

        return existinglfaReportAndFee;

    }

    @Override
    public List<LFAReportAndFeeResource> getLFAReportAndFee(String  lendersFinancialAdvisorId, String name) {
        List<LFAReportAndFeeResource>  lfaReportAndFeeResources  = new ArrayList<>();
        LendersFinancialAdvisor lendersFinancialAdvisor = lfaRepository.getOne(lendersFinancialAdvisorId);
        //LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(lendersFinancialAdvisor != null) {
            List<LFAReportAndFee> lfaReportAndFees
                    = lfaReportAndFeeRepository.findByLendersFinancialAdvisor(lendersFinancialAdvisor);
            lfaReportAndFees.forEach(
                    lfaReportAndFee -> {
                        LFAReportAndFeeResource lfaReportAndFeeResource = new LFAReportAndFeeResource();
                        //lieResource.setLoanApplicationId(loanApplication.getId());
                        lfaReportAndFeeResource.setLendersFinancialAdvisorId(lendersFinancialAdvisor.getId());
                        lfaReportAndFeeResource.setLfaReportAndFee(lfaReportAndFee);
                        lfaReportAndFeeResources.add(lfaReportAndFeeResource);
                    }
            );
        }
        return lfaReportAndFeeResources;

    }

    @Override
    public TrustRetentionAccount saveTRA(TRAResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        TrustRetentionAccount trustRetentionAccount = resource.getTrustRetentionAccount();
        trustRetentionAccount.setLoanMonitor(loanMonitor);
        trustRetentionAccount.setBankKey(resource.getTrustRetentionAccount().getBankKey());
        trustRetentionAccount.setTraBankName(resource.getTrustRetentionAccount().getTraBankName());
        trustRetentionAccount.setBranch(resource.getTrustRetentionAccount().getBranch());
        trustRetentionAccount.setAddress(resource.getTrustRetentionAccount().getAddress());
        trustRetentionAccount.setBeneficiaryName(resource.getTrustRetentionAccount().getBeneficiaryName());
        trustRetentionAccount.setIfscCode(resource.getTrustRetentionAccount().getIfscCode());
        trustRetentionAccount.setAccountNumber(resource.getTrustRetentionAccount().getAccountNumber());
        trustRetentionAccount.setContactName(resource.getTrustRetentionAccount().getContactName());
        trustRetentionAccount.setTypeOfAccount(resource.getTrustRetentionAccount().getTypeOfAccount());
        trustRetentionAccount.setContactNumber(resource.getTrustRetentionAccount().getContactNumber());
        trustRetentionAccount.setEmail(resource.getTrustRetentionAccount().getEmail());
        trustRetentionAccount.setPfsAuthorisedPerson(resource.getTrustRetentionAccount().getPfsAuthorisedPerson());
        trustRetentionAccount.setPfsAuthorisedPersonBPCode(resource.getTrustRetentionAccount().getPfsAuthorisedPersonBPCode());
        trustRetentionAccount = traRepository.save(trustRetentionAccount);

        return trustRetentionAccount;

    }

    @Override
    public TrustRetentionAccount updateTRA(TRAResource resource, String username) {
        TrustRetentionAccount existingTrustRetentionAccount
                = traRepository.getOne(resource.getTrustRetentionAccount().getId());

        existingTrustRetentionAccount.setBankKey(resource.getTrustRetentionAccount().getBankKey());
        existingTrustRetentionAccount.setTraBankName(resource.getTrustRetentionAccount().getTraBankName());
        existingTrustRetentionAccount.setBranch(resource.getTrustRetentionAccount().getBranch());
        existingTrustRetentionAccount.setAddress(resource.getTrustRetentionAccount().getAddress());
        existingTrustRetentionAccount.setBeneficiaryName(resource.getTrustRetentionAccount().getBeneficiaryName());
        existingTrustRetentionAccount.setIfscCode(resource.getTrustRetentionAccount().getIfscCode());
        existingTrustRetentionAccount.setAccountNumber(resource.getTrustRetentionAccount().getAccountNumber());
        existingTrustRetentionAccount.setContactName(resource.getTrustRetentionAccount().getContactName());
        existingTrustRetentionAccount.setTypeOfAccount(resource.getTrustRetentionAccount().getTypeOfAccount());
        existingTrustRetentionAccount.setContactNumber(resource.getTrustRetentionAccount().getContactNumber());
        existingTrustRetentionAccount.setEmail(resource.getTrustRetentionAccount().getEmail());
        existingTrustRetentionAccount.setPfsAuthorisedPerson(resource.getTrustRetentionAccount().getPfsAuthorisedPerson());
        existingTrustRetentionAccount.setPfsAuthorisedPersonBPCode(resource.getTrustRetentionAccount().getPfsAuthorisedPersonBPCode());
        existingTrustRetentionAccount = traRepository.save(existingTrustRetentionAccount);

        return existingTrustRetentionAccount;
    }

    @Override
    public List<TRAResource> getTrustRetentionAccounts(String loanApplicationId, String name) {
        List<TRAResource> trustRetentionAccountrResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<TrustRetentionAccount> trustRetentionAccounts
                    = traRepository.findByLoanMonitor(loanMonitor);
            trustRetentionAccounts.forEach(
                    trustRetentionAccount -> {
                        TRAResource traResource = new TRAResource();
                        traResource.setLoanApplicationId(loanApplication.getId());
                        traResource.setTrustRetentionAccount(trustRetentionAccount);
                        trustRetentionAccountrResources.add(traResource);
                    }
            );
        }
        return trustRetentionAccountrResources;

    }

    @Override
    public TrustRetentionAccountStatement saveTRAStatement(TRAStatementResource resource, String username) {
        TrustRetentionAccount trustRetentionAccount = traRepository.getOne(resource.getTrustRetentionAccountId());
        TrustRetentionAccountStatement trustRetentionAccountStatement = resource.getTrustRetentionAccountStatement();
        trustRetentionAccountStatement.setTrustRetentionAccount(trustRetentionAccount);
        trustRetentionAccountStatement = traStatementRepository.save(trustRetentionAccountStatement);
        return trustRetentionAccountStatement;

    }

    @Override
    public TrustRetentionAccountStatement updateTRAStatement(TRAStatementResource resource, String username) {
        TrustRetentionAccountStatement existingTrustRetentionAccountStatement
                = traStatementRepository.getOne(resource.getTrustRetentionAccountStatement().getId());

        existingTrustRetentionAccountStatement.setViewRights(resource.getTrustRetentionAccountStatement().getViewRights());
        existingTrustRetentionAccountStatement.setRemarks(resource.getTrustRetentionAccountStatement().getRemarks());
        existingTrustRetentionAccountStatement.setPeriodQuarter(resource.getTrustRetentionAccountStatement().getPeriodQuarter());
        existingTrustRetentionAccountStatement.setPeriodYear(resource.getTrustRetentionAccountStatement().getPeriodYear());
        existingTrustRetentionAccountStatement.setDocumentType(resource.getTrustRetentionAccountStatement().getDocumentType());
        existingTrustRetentionAccountStatement.setFileReference(resource.getTrustRetentionAccountStatement().getFileReference());
        existingTrustRetentionAccountStatement = traStatementRepository.save(existingTrustRetentionAccountStatement);

        return existingTrustRetentionAccountStatement;

    }

    @Override
    public List<TRAStatementResource> getTrustRetentionAccountStatements(String trustRetentionAccountId, String name) {
        List<TRAStatementResource>  traStatementResources  = new ArrayList<>();
        TrustRetentionAccount trustRetentionAccount = traRepository.getOne(trustRetentionAccountId);
        //LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(trustRetentionAccount != null) {
            List<TrustRetentionAccountStatement> trustRetentionAccountStatements
                    = traStatementRepository.findByTrustRetentionAccount(trustRetentionAccount);
            trustRetentionAccountStatements.forEach(
                    trustRetentionAccountStatement -> {
                        TRAStatementResource traStatementResource = new TRAStatementResource();
                        //lieResource.setLoanApplicationId(loanApplication.getId());
                        traStatementResource.setTrustRetentionAccountId(trustRetentionAccount.getId());
                        traStatementResource.setTrustRetentionAccountStatement(trustRetentionAccountStatement);
                        traStatementResources.add(traStatementResource);
                    }
            );
        }
        return traStatementResources;

    }

    //Terms and Conditions
    @Override
    public TermsAndConditionsModification saveTermsAndConditions(TermsAndConditionsResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        TermsAndConditionsModification termsAndConditions = resource.getTermsAndConditionsModification();
        termsAndConditions.setLoanMonitor(loanMonitor);
        termsAndConditions.setDocumentType(resource.getTermsAndConditionsModification().getDocumentType());
        termsAndConditions.setDocumentTitle(resource.getTermsAndConditionsModification().getDocumentTitle());
        termsAndConditions.setCommunication(resource.getTermsAndConditionsModification().getCommunication());
        termsAndConditions.setBorrowerRequestLetterDate(resource.getTermsAndConditionsModification().getBorrowerRequestLetterDate());
        termsAndConditions.setDateofIssueofAmendedSanctionLetter(resource.getTermsAndConditionsModification().getDateofIssueofAmendedSanctionLetter());
        termsAndConditions.setRemarks(resource.getTermsAndConditionsModification().getRemarks());
        termsAndConditions.setFileReference(resource.getTermsAndConditionsModification().getFileReference());
        termsAndConditions = termsAndConditionsRepository.save(termsAndConditions);

        return termsAndConditions;

    }

    @Override
    public TermsAndConditionsModification updateTermsAndConditions(TermsAndConditionsResource resource, String username) {
        TermsAndConditionsModification existingTermsAndConditionsModification
                = termsAndConditionsRepository.getOne(resource.getTermsAndConditionsModification().getId());

        existingTermsAndConditionsModification.setDocumentType(resource.getTermsAndConditionsModification().getDocumentType());
        existingTermsAndConditionsModification.setDocumentTitle(resource.getTermsAndConditionsModification().getDocumentTitle());
        existingTermsAndConditionsModification.setCommunication(resource.getTermsAndConditionsModification().getCommunication());
        existingTermsAndConditionsModification.setBorrowerRequestLetterDate(resource.getTermsAndConditionsModification().getBorrowerRequestLetterDate());
        existingTermsAndConditionsModification.setDateofIssueofAmendedSanctionLetter(resource.getTermsAndConditionsModification().getDateofIssueofAmendedSanctionLetter());
        existingTermsAndConditionsModification.setRemarks(resource.getTermsAndConditionsModification().getRemarks());
        existingTermsAndConditionsModification.setFileReference(resource.getTermsAndConditionsModification().getFileReference());
        existingTermsAndConditionsModification = termsAndConditionsRepository.save(existingTermsAndConditionsModification);

        return existingTermsAndConditionsModification;

    }


    @Override
    public List<TermsAndConditionsResource> getTermsAndConditions(String loanApplicationId, String name) {
        List<TermsAndConditionsResource> termsAndConditionsResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<TermsAndConditionsModification> termsAndConditions
                    = termsAndConditionsRepository.findByLoanMonitor(loanMonitor);
            termsAndConditions.forEach(
                    termsAndCondition -> {
                        TermsAndConditionsResource termsAndConditionsResource = new TermsAndConditionsResource();
                        termsAndConditionsResource.setLoanApplicationId(loanApplication.getId());
                        termsAndConditionsResource.setTermsAndConditionsModification(termsAndCondition);
                        termsAndConditionsResources.add(termsAndConditionsResource);
                    }
            );
        }
        return termsAndConditionsResources;


    }


    // Security Compliance
    @Override
    public SecurityCompliance saveSecurityCompliance(SecurityComplianceResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        SecurityCompliance securityCompliance = resource.getSecurityCompliance();
        securityCompliance.setLoanMonitor(loanMonitor);
        securityCompliance.setCollateralObjectType(resource.getSecurityCompliance().getCollateralObjectType());
        securityCompliance.setQuantity(resource.getSecurityCompliance().getQuantity());
        securityCompliance.setApplicability(resource.getSecurityCompliance().getApplicability());
        securityCompliance.setCollateralAgreementType(resource.getSecurityCompliance().getCollateralAgreementType());
        securityCompliance.setTimelines(resource.getSecurityCompliance().getTimelines());
        securityCompliance.setDateOfCreation(resource.getSecurityCompliance().getDateOfCreation());
        securityCompliance.setValidityDate(resource.getSecurityCompliance().getValidityDate());
        securityCompliance.setValue(resource.getSecurityCompliance().getValue());
        securityCompliance.setSecurityPerfectionDate(resource.getSecurityCompliance().getSecurityPerfectionDate());
        securityCompliance.setRemarks(resource.getSecurityCompliance().getRemarks());
        securityCompliance.setActionPeriod(resource.getSecurityCompliance().getActionPeriod());
        securityCompliance.setEventType(resource.getSecurityCompliance().getEventType());
        securityCompliance.setLocation(resource.getSecurityCompliance().getLocation());
        securityCompliance.setAdditionalText(resource.getSecurityCompliance().getAdditionalText());
        securityCompliance.setRealEstateLandArea(resource.getSecurityCompliance().getRealEstateLandArea());
        securityCompliance.setAreaUnitOfMeasure(resource.getSecurityCompliance().getAreaUnitOfMeasure());
        securityCompliance.setSecurityNoOfUnits(resource.getSecurityCompliance().getSecurityNoOfUnits());
        securityCompliance.setSecurityFaceValueAmount(resource.getSecurityCompliance().getSecurityFaceValueAmount());
        securityCompliance.setHoldingPercentage(resource.getSecurityCompliance().getHoldingPercentage());

        securityCompliance = securityComplianceRepository.save(securityCompliance);

        return securityCompliance;

    }

    @Override
    public SecurityCompliance updateSecurityCompliance(SecurityComplianceResource resource, String username) {
        SecurityCompliance existingSecurityCompliance
                = securityComplianceRepository.getOne(resource.getSecurityCompliance().getId());

        existingSecurityCompliance.setCollateralObjectType(resource.getSecurityCompliance().getCollateralObjectType());
        existingSecurityCompliance.setQuantity(resource.getSecurityCompliance().getQuantity());
        existingSecurityCompliance.setApplicability(resource.getSecurityCompliance().getApplicability());
        existingSecurityCompliance.setCollateralAgreementType(resource.getSecurityCompliance().getCollateralAgreementType());
        existingSecurityCompliance.setTimelines(resource.getSecurityCompliance().getTimelines());
        existingSecurityCompliance.setDateOfCreation(resource.getSecurityCompliance().getDateOfCreation());
        existingSecurityCompliance.setValidityDate(resource.getSecurityCompliance().getValidityDate());
        existingSecurityCompliance.setValue(resource.getSecurityCompliance().getValue());
        existingSecurityCompliance.setSecurityPerfectionDate(resource.getSecurityCompliance().getSecurityPerfectionDate());
        existingSecurityCompliance.setRemarks(resource.getSecurityCompliance().getRemarks());
        existingSecurityCompliance.setActionPeriod(resource.getSecurityCompliance().getActionPeriod());
        existingSecurityCompliance.setEventType(resource.getSecurityCompliance().getEventType());
        existingSecurityCompliance.setLocation(resource.getSecurityCompliance().getLocation());
        existingSecurityCompliance.setAdditionalText(resource.getSecurityCompliance().getAdditionalText());
        existingSecurityCompliance.setRealEstateLandArea(resource.getSecurityCompliance().getRealEstateLandArea());
        existingSecurityCompliance.setAreaUnitOfMeasure(resource.getSecurityCompliance().getAreaUnitOfMeasure());
        existingSecurityCompliance.setSecurityNoOfUnits(resource.getSecurityCompliance().getSecurityNoOfUnits());
        existingSecurityCompliance.setSecurityFaceValueAmount(resource.getSecurityCompliance().getSecurityFaceValueAmount());
        existingSecurityCompliance.setHoldingPercentage(resource.getSecurityCompliance().getHoldingPercentage());

        existingSecurityCompliance = securityComplianceRepository.save(existingSecurityCompliance);

        return existingSecurityCompliance;

    }

    @Override
    public List<SecurityComplianceResource> getSecurityCompliance(String loanApplicationId, String name) {
        List<SecurityComplianceResource> securityComplianceResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<SecurityCompliance> securityCompliances
                    = securityComplianceRepository.findByLoanMonitor(loanMonitor);
            securityCompliances.forEach(
                    securityCompliance -> {
                        SecurityComplianceResource securityComplianceResource = new SecurityComplianceResource();
                        securityComplianceResource.setLoanApplicationId(loanApplication.getId());
                        securityComplianceResource.setSecurityCompliance(securityCompliance);
                        securityComplianceResources.add(securityComplianceResource);
                    }
            );
        }
        return securityComplianceResources;

    }

    @Override
    public SiteVisit saveSiteVisit(SiteVisitResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        SiteVisit siteVisit = resource.getSiteVisit();
        siteVisit.setLoanMonitor(loanMonitor);
        siteVisit.setSerialNumber(resource.getSiteVisit().getSerialNumber());
        siteVisit.setActualCOD(resource.getSiteVisit().getActualCOD());
        siteVisit.setDateOfLendersMeet(resource.getSiteVisit().getDateOfLendersMeet());
        siteVisit.setDateOfSiteVisit(resource.getSiteVisit().getDateOfSiteVisit());
        siteVisit = siteVisitRepository.save(siteVisit);

        return siteVisit;

    }

    @Override
    public SiteVisit updateSiteVisit(SiteVisitResource resource, String username) {
        SiteVisit existingSiteVisit
                = siteVisitRepository.getOne(resource.getSiteVisit().getId());

        existingSiteVisit.setSerialNumber(resource.getSiteVisit().getSerialNumber());
        existingSiteVisit.setActualCOD(resource.getSiteVisit().getActualCOD());
        existingSiteVisit.setDateOfSiteVisit(resource.getSiteVisit().getDateOfSiteVisit());
        existingSiteVisit.setDateOfLendersMeet(resource.getSiteVisit().getDateOfLendersMeet());
        existingSiteVisit = siteVisitRepository.save(existingSiteVisit);

        return existingSiteVisit;

    }

    @Override
    public List<SiteVisitResource> getSiteVisit(String loanApplicationId, String name) {
        List<SiteVisitResource> siteVisitResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<SiteVisit> siteVisits
                    = siteVisitRepository.findByLoanMonitor(loanMonitor);
            siteVisits.forEach(
                    siteVisit -> {
                        SiteVisitResource siteVisitResource = new SiteVisitResource();
                        siteVisitResource.setLoanApplicationId(loanApplication.getId());
                        siteVisitResource.setSiteVisit(siteVisit);
                        siteVisitResources.add(siteVisitResource);
                    }
            );
        }
        return siteVisitResources;

    }

    //Operating Parameter
    @Override
    public OperatingParameter saveOperatingParameter(OperatingParameterResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        OperatingParameter operatingParameter = resource.getOperatingParameter();
        operatingParameter.setLoanMonitor(loanMonitor);
        operatingParameter.setSerialNumber(resource.getOperatingParameter().getSerialNumber());
        operatingParameter.setMonth(resource.getOperatingParameter().getMonth());
        operatingParameter.setYear(resource.getOperatingParameter().getYear());
        operatingParameter.setExportNetGeneration(resource.getOperatingParameter().getExportNetGeneration());
        operatingParameter.setPlfCufActual(resource.getOperatingParameter().getPlfCufActual());
        operatingParameter.setApplicableTariff(resource.getOperatingParameter().getApplicableTariff());
        operatingParameter.setRevenue(resource.getOperatingParameter().getRevenue());
        operatingParameter.setDateOfInvoice(resource.getOperatingParameter().getDateOfInvoice());
        operatingParameter.setDateOfPaymentReceipt(resource.getOperatingParameter().getDateOfPaymentReceipt());
        operatingParameter.setCarbonDioxideEmission(resource.getOperatingParameter().getCarbonDioxideEmission());
        operatingParameter.setWaterSaved(resource.getOperatingParameter().getWaterSaved());
        operatingParameter.setRemarks(resource.getOperatingParameter().getRemarks());
        operatingParameter.setDesignPlfCuf(resource.getOperatingParameter().getDesignPlfCuf());
        operatingParameter.setActualYearlyAveragePlfCuf(resource.getOperatingParameter().getActualYearlyAveragePlfCuf());

        operatingParameter.setDocumentType(resource.getOperatingParameter().getDocumentType());
        operatingParameter.setDocumentTitle(resource.getOperatingParameter().getDocumentTitle());

        operatingParameter.setDocumentContent(resource.getOperatingParameter().getDocumentContent());
        operatingParameter = operatingParameterRepository.save(operatingParameter);

        return operatingParameter;

    }

    @Override
    public OperatingParameter updateOperatingParameter(OperatingParameterResource resource, String username) {
        OperatingParameter existingOperatingParameter
                = operatingParameterRepository.getOne(resource.getOperatingParameter().getId());

        existingOperatingParameter = operatingParameterRepository.save(existingOperatingParameter);
        existingOperatingParameter.setSerialNumber(resource.getOperatingParameter().getSerialNumber());
        existingOperatingParameter.setMonth(resource.getOperatingParameter().getMonth());
        existingOperatingParameter.setYear(resource.getOperatingParameter().getYear());
        existingOperatingParameter.setExportNetGeneration(resource.getOperatingParameter().getExportNetGeneration());
        existingOperatingParameter.setPlfCufActual(resource.getOperatingParameter().getPlfCufActual());
        existingOperatingParameter.setApplicableTariff(resource.getOperatingParameter().getApplicableTariff());
        existingOperatingParameter.setRevenue(resource.getOperatingParameter().getRevenue());
        existingOperatingParameter.setDateOfInvoice(resource.getOperatingParameter().getDateOfInvoice());
        existingOperatingParameter.setDateOfPaymentReceipt(resource.getOperatingParameter().getDateOfPaymentReceipt());
        existingOperatingParameter.setCarbonDioxideEmission(resource.getOperatingParameter().getCarbonDioxideEmission());
        existingOperatingParameter.setWaterSaved(resource.getOperatingParameter().getWaterSaved());
        existingOperatingParameter.setRemarks(resource.getOperatingParameter().getRemarks());
        existingOperatingParameter.setDesignPlfCuf(resource.getOperatingParameter().getDesignPlfCuf());
        existingOperatingParameter.setActualYearlyAveragePlfCuf(resource.getOperatingParameter().getActualYearlyAveragePlfCuf());

        existingOperatingParameter.setDocumentType(resource.getOperatingParameter().getDocumentType());
        existingOperatingParameter.setDocumentTitle(resource.getOperatingParameter().getDocumentTitle());

        existingOperatingParameter.setDocumentContent(resource.getOperatingParameter().getDocumentContent());

        return existingOperatingParameter;


    }

    @Override
    public List<OperatingParameterResource> getOperatingParameter(String loanApplicationId, String name) {
        List<OperatingParameterResource> operatingParameterResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<OperatingParameter> operatingParameters
                    = operatingParameterRepository.findByLoanMonitor(loanMonitor);
            operatingParameters.forEach(
                    operatingParameter -> {
                        OperatingParameterResource operatingParameterResource = new OperatingParameterResource();
                        operatingParameterResource.setLoanApplicationId(loanApplication.getId());
                        operatingParameterResource.setOperatingParameter(operatingParameter);
                        operatingParameterResources.add(operatingParameterResource);
                    }
            );
        }
        return operatingParameterResources;

    }

    //Rate Of Interest


    @Override
    public RateOfInterest saveRateOfInterest(RateOfInterestResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        RateOfInterest rateOfInterest = resource.getRateOfInterest();
        rateOfInterest.setLoanMonitor(loanMonitor);
        rateOfInterest.setParticulars(resource.getRateOfInterest().getParticulars());
        rateOfInterest.setScheduledIfAny(resource.getRateOfInterest().getScheduledIfAny());
        rateOfInterest.setSanctionPreCod(resource.getRateOfInterest().getSanctionPreCod());
        rateOfInterest.setSanctionPostCod(resource.getRateOfInterest().getSanctionPostCod());
        rateOfInterest.setPresentRoi(resource.getRateOfInterest().getPresentRoi());
        rateOfInterest.setFreeText(resource.getRateOfInterest().getFreeText());
        rateOfInterest = rateOfInterestRepository.save(rateOfInterest);

        return rateOfInterest;

    }

    @Override
    public RateOfInterest updateRateOfInterest(RateOfInterestResource resource, String username) {
        RateOfInterest existingRateOfInterest
                = rateOfInterestRepository.getOne(resource.getRateOfInterest().getId());

        existingRateOfInterest.setParticulars(resource.getRateOfInterest().getParticulars());
        existingRateOfInterest.setScheduledIfAny(resource.getRateOfInterest().getScheduledIfAny());
        existingRateOfInterest.setSanctionPreCod(resource.getRateOfInterest().getSanctionPreCod());
        existingRateOfInterest.setSanctionPostCod(resource.getRateOfInterest().getSanctionPostCod());
        existingRateOfInterest.setPresentRoi(resource.getRateOfInterest().getPresentRoi());
        existingRateOfInterest.setFreeText(resource.getRateOfInterest().getFreeText());
        existingRateOfInterest = rateOfInterestRepository.save(existingRateOfInterest);

        return existingRateOfInterest;
    }

    @Override
    public List<RateOfInterestResource> getRateOfInterest(String loanApplicationId, String name) {
        List<RateOfInterestResource> rateOfInterestResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<RateOfInterest> rateOfInterests
                    = rateOfInterestRepository.findByLoanMonitor(loanMonitor);
            rateOfInterests.forEach(
                    rateOfInterest -> {
                        RateOfInterestResource rateOfInterestResource = new RateOfInterestResource();
                        rateOfInterestResource.setLoanApplicationId(loanApplication.getId());
                        rateOfInterestResource.setRateOfInterest(rateOfInterest);
                        rateOfInterestResources.add(rateOfInterestResource);
                    }
            );
        }
        return rateOfInterestResources;

    }

    // Borrower Financials

    @Override
    public BorrowerFinancials saveBorrowerFinancials(BorrowerFinancialsResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        BorrowerFinancials borrowerFinancials = resource.getBorrowerFinancials();
        borrowerFinancials.setLoanMonitor(loanMonitor);
        // borrowerFinancials.setBorrowerFinancialsId(resource.getBorrowerFinancials().getBorrowerFinancialsId());
        borrowerFinancials.setFiscalYear(resource.getBorrowerFinancials().getFiscalYear());
        borrowerFinancials.setTurnover(resource.getBorrowerFinancials().getTurnover());
        borrowerFinancials.setPat(resource.getBorrowerFinancials().getPat());
        borrowerFinancials.setNetWorth(resource.getBorrowerFinancials().getNetWorth());
        borrowerFinancials.setDateOfExternalRating(resource.getBorrowerFinancials().getDateOfExternalRating());
        borrowerFinancials.setNextDueDateOfExternalRating(resource.getBorrowerFinancials().getNextDueDateOfExternalRating());
        borrowerFinancials.setOverAllRating(resource.getBorrowerFinancials().getOverAllRating());
        borrowerFinancials.setDocumentContentAnnualReturn(resource.getBorrowerFinancials().getDocumentContentAnnualReturn());
        borrowerFinancials.setDocumentContentRating(resource.getBorrowerFinancials().getDocumentContentRating());
        borrowerFinancials = borrowerFinancialsRepository.save(borrowerFinancials);
        return borrowerFinancials;

    }

    @Override
    public BorrowerFinancials updateBorrowerFinancials(BorrowerFinancialsResource resource, String username) {
        BorrowerFinancials existingBorrowerFinancials
                = borrowerFinancialsRepository.getOne(resource.getBorrowerFinancials().getId());

        existingBorrowerFinancials.setFiscalYear(resource.getBorrowerFinancials().getFiscalYear());
        existingBorrowerFinancials.setTurnover(resource.getBorrowerFinancials().getTurnover());
        existingBorrowerFinancials.setPat(resource.getBorrowerFinancials().getPat());
        existingBorrowerFinancials.setNetWorth(resource.getBorrowerFinancials().getNetWorth());
        existingBorrowerFinancials.setDateOfExternalRating(resource.getBorrowerFinancials().getDateOfExternalRating());
        existingBorrowerFinancials.setNextDueDateOfExternalRating(resource.getBorrowerFinancials().getNextDueDateOfExternalRating());
        existingBorrowerFinancials.setOverAllRating(resource.getBorrowerFinancials().getOverAllRating());
        existingBorrowerFinancials.setDocumentContentAnnualReturn(resource.getBorrowerFinancials().getDocumentContentAnnualReturn());
        existingBorrowerFinancials.setDocumentContentRating(resource.getBorrowerFinancials().getDocumentContentRating());
        existingBorrowerFinancials = borrowerFinancialsRepository.save(existingBorrowerFinancials);
        return existingBorrowerFinancials;


    }

    @Override
    public List<BorrowerFinancialsResource> getBorrowerFinancials(String loanApplicationId, String name) {
        List<BorrowerFinancialsResource> borrowerFinancialsResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<BorrowerFinancials> borrowerFinancials
                    = borrowerFinancialsRepository.findByLoanMonitor(loanMonitor);
            borrowerFinancials.forEach(
                    borrowerFinancial -> {
                        BorrowerFinancialsResource borrowerFinancialsResource = new BorrowerFinancialsResource();
                        borrowerFinancialsResource.setLoanApplicationId(loanApplication.getId());
                        borrowerFinancialsResource.setBorrowerFinancials(borrowerFinancial);
                        borrowerFinancialsResources.add(borrowerFinancialsResource);
                    }
            );
        }
        return borrowerFinancialsResources;
    }

    // Promoter Financials

    @Override
    public PromoterFinancials savePromoterFinancials(PromoterFinancialsResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        PromoterFinancials promoterFinancials = resource.getPromoterFinancials();
        promoterFinancials.setLoanMonitor(loanMonitor);
        promoterFinancials.setBorrowerFinancialsId(resource.getPromoterFinancials().getBorrowerFinancialsId());
        promoterFinancials.setFiscalYear(resource.getPromoterFinancials().getFiscalYear());
        promoterFinancials.setTurnover(resource.getPromoterFinancials().getTurnover());
        promoterFinancials.setPat(resource.getPromoterFinancials().getPat());
        promoterFinancials.setNetWorth(resource.getPromoterFinancials().getNetWorth());
        promoterFinancials.setDateOfExternalRating(resource.getPromoterFinancials().getDateOfExternalRating());
        promoterFinancials.setNextDueDateOfExternalRating(resource.getPromoterFinancials().getNextDueDateOfExternalRating());
        promoterFinancials.setOverAllRating(resource.getPromoterFinancials().getOverAllRating());
        promoterFinancials.setDocumentContentAnnualReturn(resource.getPromoterFinancials().getDocumentContentAnnualReturn());
        promoterFinancials.setDocumentContentRating(resource.getPromoterFinancials().getDocumentContentRating());
        promoterFinancials = promoterFinancialsRepository.save(promoterFinancials);
        return promoterFinancials;

    }

    @Override
    public PromoterFinancials updatePromoterFinancials(PromoterFinancialsResource resource, String username) {
        PromoterFinancials existingPromoterFinancials
                = promoterFinancialsRepository.getOne(resource.getPromoterFinancials().getId());

        existingPromoterFinancials.setFiscalYear(resource.getPromoterFinancials().getFiscalYear());
        existingPromoterFinancials.setTurnover(resource.getPromoterFinancials().getTurnover());
        existingPromoterFinancials.setPat(resource.getPromoterFinancials().getPat());
        existingPromoterFinancials.setNetWorth(resource.getPromoterFinancials().getNetWorth());
        existingPromoterFinancials.setDateOfExternalRating(resource.getPromoterFinancials().getDateOfExternalRating());
        existingPromoterFinancials.setNextDueDateOfExternalRating(resource.getPromoterFinancials().getNextDueDateOfExternalRating());
        existingPromoterFinancials.setOverAllRating(resource.getPromoterFinancials().getOverAllRating());
        existingPromoterFinancials.setDocumentContentAnnualReturn(resource.getPromoterFinancials().getDocumentContentAnnualReturn());
        existingPromoterFinancials.setDocumentContentRating(resource.getPromoterFinancials().getDocumentContentRating());
        existingPromoterFinancials = promoterFinancialsRepository.save(existingPromoterFinancials);
        return existingPromoterFinancials;


    }

    @Override
    public List<PromoterFinancialsResource> getPromoterFinancials(String loanApplicationId, String name) {
        List<PromoterFinancialsResource> promoterFinancialsResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<PromoterFinancials> promoterFinancials
                    = promoterFinancialsRepository.findByLoanMonitor(loanMonitor);
            promoterFinancials.forEach(
                    promoterFinancial -> {
                        PromoterFinancialsResource promoterFinancialsResource = new PromoterFinancialsResource();
                        promoterFinancialsResource.setLoanApplicationId(loanApplication.getId());
                        promoterFinancialsResource.setPromoterFinancials(promoterFinancial);
                        promoterFinancialsResources.add(promoterFinancialsResource);
                    }
            );
        }
        return promoterFinancialsResources;
    }

    // Financial Covenants
    @Override
    public FinancialCovenants saveFinancialCovenants(FinancialCovenantsResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        FinancialCovenants financialCovenants = resource.getFinancialCovenants();
        financialCovenants.setLoanMonitor(loanMonitor);
        financialCovenants.setFinancialCovenantType(resource.getFinancialCovenants().getFinancialCovenantType());
        financialCovenants.setFinancialYear(resource.getFinancialCovenants().getFinancialYear());
        financialCovenants.setDebtEquityRatio(resource.getFinancialCovenants().getDebtEquityRatio());
        financialCovenants.setDscr(resource.getFinancialCovenants().getDscr());
        financialCovenants.setTolTnw(resource.getFinancialCovenants().getTolTnw());
        financialCovenants.setRemarksForDeviation(resource.getFinancialCovenants().getRemarksForDeviation());
        financialCovenants = financialCovenantsRepository.save(financialCovenants);
        return financialCovenants;

    }

    @Override
    public FinancialCovenants updateFinancialCovenants(FinancialCovenantsResource resource, String username) {
        FinancialCovenants existingFinancialCovenants
                = financialCovenantsRepository.getOne(resource.getFinancialCovenants().getId());

        existingFinancialCovenants.setFinancialCovenantType(resource.getFinancialCovenants().getFinancialCovenantType());
        existingFinancialCovenants.setFinancialYear(resource.getFinancialCovenants().getFinancialYear());
        existingFinancialCovenants.setDebtEquityRatio(resource.getFinancialCovenants().getDebtEquityRatio());
        existingFinancialCovenants.setDscr(resource.getFinancialCovenants().getDscr());
        existingFinancialCovenants.setTolTnw(resource.getFinancialCovenants().getTolTnw());
        existingFinancialCovenants.setRemarksForDeviation(resource.getFinancialCovenants().getRemarksForDeviation());
        existingFinancialCovenants = financialCovenantsRepository.save(existingFinancialCovenants);
        return existingFinancialCovenants;

    }

    @Override
    public List<FinancialCovenantsResource> getFinancialCovenants(String loanApplicationId, String name) {
        List<FinancialCovenantsResource> financialCovenantsResources = new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<FinancialCovenants> financialCovenants
                    = financialCovenantsRepository.findByLoanMonitor(loanMonitor);
            financialCovenants.forEach(
                    financialCovenant -> {
                        FinancialCovenantsResource financialCovenantsResource = new FinancialCovenantsResource();
                        financialCovenantsResource.setLoanApplicationId(loanApplication.getId());
                        financialCovenantsResource.setFinancialCovenants(financialCovenant);
                        financialCovenantsResources.add(financialCovenantsResource);
                    }
            );
        }
        return financialCovenantsResources;

    }

    //Promoter Details
    @Override
    public PromoterDetails savePromoterDetails(PromoterDetailsResource resource, String username) {
        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }
        PromoterDetails promoterDetails = resource.getPromoterDetails();
        promoterDetails.setLoanMonitor(loanMonitor);
        promoterDetails.setDateOfChange(resource.getPromoterDetails().getDateOfChange());
        promoterDetails.setGroupExposure(resource.getPromoterDetails().getGroupExposure());
        promoterDetails.setPromoterDetailsItemSet(resource.getPromoterDetails().getPromoterDetailsItemSet());
        promoterDetails = promoterDetailsRepository.save(promoterDetails);
        return promoterDetails;

    }

    @Override
    public PromoterDetails updatePromoterDetails(PromoterDetailsResource resource, String username) {
        final PromoterDetails existingPromoterDetails
                = promoterDetailsRepository.getOne(resource.getPromoterDetails().getId());

        existingPromoterDetails.setDateOfChange(resource.getPromoterDetails().getDateOfChange());
        existingPromoterDetails.setGroupExposure(resource.getPromoterDetails().getGroupExposure());

        resource.getPromoterDetails().getPromoterDetailsItemSet().forEach(resourceItem -> {
            existingPromoterDetails.getPromoterDetailsItemSet().forEach(promoterDetailsItem -> {
                if (resourceItem.getId().equals(promoterDetailsItem.getId())) {
                    promoterDetailsItem.setShareHoldingCompany(resourceItem.getShareHoldingCompany());
                    promoterDetailsItem.setPaidupCapitalEquitySanction(resourceItem.getPaidupCapitalEquitySanction());
                    promoterDetailsItem.setEquityLinkInstrumentSanction(resourceItem.getEquityLinkInstrumentSanction());
                    promoterDetailsItem.setPaidupCapitalEquityCurrent(resourceItem.getPaidupCapitalEquityCurrent());
                    promoterDetailsItem.setEquityLinkInstrumentCurrent(resourceItem.getEquityLinkInstrumentCurrent());
                }
            });
        });
        resource.getPromoterDetails().getPromoterDetailsItemSet().forEach(resourceItem -> {
            if (resourceItem.getId().equals("")) {
                existingPromoterDetails.getPromoterDetailsItemSet().add(resourceItem);
            }
        });

        PromoterDetails promoterDetails = promoterDetailsRepository.save(existingPromoterDetails);
        return promoterDetails;
    }

    @Override
    public List<PromoterDetailsResource> getPromoterDetails(String loanApplicationId, String name) {
        List<PromoterDetailsResource> promoterDetailsResources= new ArrayList<>();
        LoanApplication loanApplication = loanApplicationRepository.getOne(UUID.fromString(loanApplicationId));
        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor != null) {
            List<PromoterDetails> promoterDetails
                    = promoterDetailsRepository.findByLoanMonitor(loanMonitor);
            promoterDetails.forEach(
                    promoterDetail -> {
                        PromoterDetailsResource promoterDetailsResourceResource = new PromoterDetailsResource();
                        promoterDetailsResourceResource.setLoanApplicationId(loanApplication.getId());
                        promoterDetailsResourceResource.setPromoterDetails(promoterDetail);
                        promoterDetailsResources.add(promoterDetailsResourceResource);
                    }
            );
        }
        return promoterDetailsResources;

    }
}
