package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LoanClass;
import pfs.lms.enquiry.domain.ReferenceInterestRate;

import java.util.UUID;

public interface ReferenceInterestRateRepository extends JpaRepository<ReferenceInterestRate, Integer> {
    public ReferenceInterestRate findByCode(String code);
}
