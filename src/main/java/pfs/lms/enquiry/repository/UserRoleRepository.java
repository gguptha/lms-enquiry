package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.UnitOfMeasure;
import pfs.lms.enquiry.domain.UserRole;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    UserRole findByCode(String code);
}
