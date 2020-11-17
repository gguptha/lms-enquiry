package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;

import java.util.UUID;

public interface LIERepository extends JpaRepository<LendersIndependentEngineer, UUID> {
}
