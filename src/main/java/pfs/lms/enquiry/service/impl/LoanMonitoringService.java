package pfs.lms.enquiry.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.*;
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
        existinglieReportAndFee.setDocumentContent(resource.getLieReportAndFee().getDocumentContent());
        existinglieReportAndFee.setNextReportDate(resource.getLieReportAndFee().getNextReportDate());
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
        existinglfaReportAndFee.setDocumentContent(resource.getLfaReportAndFee().getDocumentContent());
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
        existingTrustRetentionAccountStatement.setDocumentContent(resource.getTrustRetentionAccountStatement().getDocumentContent());
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

}
