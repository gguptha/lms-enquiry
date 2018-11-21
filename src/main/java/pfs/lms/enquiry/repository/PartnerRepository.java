package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.Partner;

import java.util.UUID;

public interface PartnerRepository extends JpaRepository<Partner, UUID> {
    Partner findByEmail(String email);
    Partner findByUserName(String username);
}
