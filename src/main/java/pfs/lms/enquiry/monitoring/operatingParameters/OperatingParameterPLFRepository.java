package pfs.lms.enquiry.monitoring.operatingParameters;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.List;

public interface OperatingParameterPLFRepository extends JpaRepository<OperatingParameterPLF, String> {

    List<OperatingParameterPLF> findByLoanMonitor(LoanMonitor loanMonitor);
}
