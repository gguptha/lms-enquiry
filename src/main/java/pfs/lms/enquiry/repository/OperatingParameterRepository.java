package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.OperatingParameter;
import pfs.lms.enquiry.domain.SiteVisit;

import java.util.List;

public interface OperatingParameterRepository extends JpaRepository<OperatingParameter, String> {

    List<OperatingParameter> findByLoanMonitor(LoanMonitor loanMonitor);
}
