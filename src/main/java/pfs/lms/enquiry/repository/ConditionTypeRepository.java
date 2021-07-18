package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.ConditionType;
import pfs.lms.enquiry.domain.PaymentForm;

public interface ConditionTypeRepository extends JpaRepository<ConditionType, Integer> {
    public ConditionType findByCode(String code);
}
