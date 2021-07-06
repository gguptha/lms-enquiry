package pfs.lms.enquiry.monitoring.operatingparameters;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanMonitor;

import java.util.List;

public interface OperatingParameterRepository extends JpaRepository<OperatingParameter, String> {

    List<OperatingParameter> findByLoanMonitor(LoanMonitor loanMonitor);
}
