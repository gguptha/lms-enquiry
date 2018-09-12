package pfs.lms.enquiry.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanApplicationStatus;
import pfs.lms.enquiry.repository.LoanApplicationStatusRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationEngine {

    private final LoanApplicationStatusRepository loanApplicationStatusRepository;

    @EventListener
    public void onLoanApplicationCreated(LoanApplication.LoanApplicationCreated loanApplicationCreated){
        loanApplicationStatusRepository.save(new LoanApplicationStatus(loanApplicationCreated.getLoanApplication()));
    }
}
