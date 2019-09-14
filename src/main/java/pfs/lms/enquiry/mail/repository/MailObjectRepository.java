package pfs.lms.enquiry.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.mail.domain.MailObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface MailObjectRepository extends JpaRepository<MailObject, UUID> {


    List<MailObject> findByToAddressContainsAndDateTimeBetween(String toAddress, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);
    List<MailObject> findByDateTimeBetween(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);

    List<MailObject> findByDateTimeGreaterThanEqualAndDateTimeLessThanEqual(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);


    List<MailObject> findByToAddressContains(String toAddress);
}
