package pfs.lms.enquiry.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.mail.domain.MailObject;

import java.util.UUID;

/**
 * Created by sajeev on 16-Feb-19.
 */
public interface MailObjectRepository extends JpaRepository<MailObject, UUID> {
}
