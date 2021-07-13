package pfs.lms.enquiry.monitoring.operatingparameters;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.monitoring.domain.LoanMonitor;

import java.util.List;

public interface OperatingParameterPLFRepository extends JpaRepository<OperatingParameterPLF, String> {

    List<OperatingParameterPLF> findByLoanMonitor(LoanMonitor loanMonitor);
}
