package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.domain.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    @Override
    @RestResource(exported = false)
    User save(User s);

    Set<User> findByRiskDepartmentContainingIgnoreCase(String riskDepartment);

    List<User> findByFirstNameStartingWith(String firstName);

    List<User> findByFirstNameContaining(String firstName);

    List<User> findByLastNameStartingWith(String lastName);
    List<User> findByLastNameContaining(String lastName);


    List<User> findByFirstNameStartingWithAndLastNameStartingWith(String firstName, String lastName);
    List<User> findByFirstNameStartingWithAndLastNameContaining(String firstName, String lastName);



    List<User> findByEmailContains(String email);
    List<User> findByRole(String role);

    List<User> findByEmailContainsAndRole(String email, String role);

}
