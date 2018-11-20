package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pfs.lms.enquiry.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    @Override
    @RestResource(exported = false)
    User save(User s);
}
