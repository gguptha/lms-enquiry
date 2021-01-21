package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.TermsAndConditionsModification;

import java.util.List;

public interface TermsAndConditionsRepository extends JpaRepository<TermsAndConditionsModification, String> {

    List<TermsAndConditionsModification> findByLoanMonitor(LoanMonitor loanMonitor);

}
