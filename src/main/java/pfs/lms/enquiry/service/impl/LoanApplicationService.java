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
        Partner applicant = partnerService.save(resource.getPartner());
        Partner user = partnerService.getOne(username);

        //Set it to the Loan Application
        LoanApplication loanApplication = resource.getLoanApplication();
        loanApplication.applicant(applicant);
        loanApplication.created(user);

        //Save and return the Loan Application
        loanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplication;
    }
}
