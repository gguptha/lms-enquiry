package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanContractExtension;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.LoanContractExtensionRepository;
import pfs.lms.enquiry.resource.LoanContractExtensionResource;
import pfs.lms.enquiry.service.ILoanContractExtensionService;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanContractExtensionService implements ILoanContractExtensionService {

    private final LoanContractExtensionRepository loanContractExtensionRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public LoanContractExtension save(LoanContractExtensionResource resource, String username) {

        System.out.println(" Saving NEW Loan Contract Extension : Loan Application Id: " + resource.getLoanApplicationId());

        LoanApplication loanApplication = loanApplicationRepository.getOne(resource.getLoanApplicationId());

        System.out.println(" Continuing to SAVE NEW Loan Contract Extension :" + resource.getLoanApplicationId());

        LoanContractExtension loanContractExtension = resource.getLoanContractExtension();
        loanContractExtension.setLoanApplication(loanApplication);
        loanContractExtension.setLoanNumber(loanApplication.getLoanContractId());
        loanContractExtension.setBoardApprovalDate(resource.getLoanContractExtension().getBoardApprovalDate());
        loanContractExtension.setBoardMeetingNumber(resource.getLoanContractExtension().getBoardMeetingNumber());
        //loanContractExtension.setLoanNumber(resource.getLoanContractExtension().getLoanNumber());
        loanContractExtension.setSanctionLetterDate(resource.getLoanContractExtension().getSanctionLetterDate());
        loanContractExtension.setLoanDocumentationDate(resource.getLoanContractExtension().getLoanDocumentationDate());
        loanContractExtension.setFirstDisbursementDate(resource.getLoanContractExtension().getFirstDisbursementDate());
        loanContractExtension.setSanctionAmount(resource.getLoanContractExtension().getSanctionAmount());
        loanContractExtension.setDisbursementStatus(resource.getLoanContractExtension().getDisbursementStatus());
        loanContractExtension.setScheduledCOD(resource.getLoanContractExtension().getScheduledCOD());
        loanContractExtension = loanContractExtensionRepository.save(loanContractExtension);
        return loanContractExtension;

    }

    @Override
    public LoanContractExtension update(LoanContractExtensionResource resource, String username) {

        System.out.println(" Updating Loan Contract Extension : " + resource.getLoanContractExtension().getId());


        LoanContractExtension existingLoanContractExtension
                = loanContractExtensionRepository.getOne(resource.getLoanContractExtension().getId());

        System.out.println(" Continuing Updating Loan Contract Extension : " + resource.getLoanContractExtension().getId());



        existingLoanContractExtension.setLoanApplication(resource.getLoanContractExtension().getLoanApplication());
        existingLoanContractExtension.setBoardApprovalDate(resource.getLoanContractExtension().getBoardApprovalDate());
        existingLoanContractExtension.setBoardMeetingNumber(resource.getLoanContractExtension().getBoardMeetingNumber());
        existingLoanContractExtension.setLoanNumber(resource.getLoanContractExtension().getLoanNumber());
        existingLoanContractExtension.setSanctionLetterDate(resource.getLoanContractExtension().getSanctionLetterDate());
        existingLoanContractExtension.setLoanDocumentationDate(resource.getLoanContractExtension().getLoanDocumentationDate());
        existingLoanContractExtension.setFirstDisbursementDate(resource.getLoanContractExtension().getFirstDisbursementDate());
        existingLoanContractExtension.setSanctionAmount(resource.getLoanContractExtension().getSanctionAmount());
        existingLoanContractExtension.setDisbursementStatus(resource.getLoanContractExtension().getDisbursementStatus());
        existingLoanContractExtension.setScheduledCOD(resource.getLoanContractExtension().getScheduledCOD());
        existingLoanContractExtension = loanContractExtensionRepository.save(existingLoanContractExtension);

        return existingLoanContractExtension;

    }
}
