package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.InterestCalculationMethod;
import pfs.lms.enquiry.domain.PaymentForm;

public interface InterestCalculationMethodRepository extends JpaRepository<InterestCalculationMethod, Integer> {
    public InterestCalculationMethod findByCode(String code);
}
