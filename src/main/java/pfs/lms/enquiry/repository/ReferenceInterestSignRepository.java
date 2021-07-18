package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.ConditionType;
import pfs.lms.enquiry.domain.PaymentForm;
import pfs.lms.enquiry.domain.ReferenceInterestSign;

public interface ReferenceInterestSignRepository extends JpaRepository<ReferenceInterestSign, Integer> {
    public ReferenceInterestSign findByCode(String code);
}
