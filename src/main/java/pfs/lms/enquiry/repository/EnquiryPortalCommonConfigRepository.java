package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.EnquiryPortalCommonConfig;
import pfs.lms.enquiry.domain.UserRole;

import java.util.UUID;

public interface EnquiryPortalCommonConfigRepository extends JpaRepository<EnquiryPortalCommonConfig, UUID> {

    public EnquiryPortalCommonConfig findBySystemId(String systemId);


}
