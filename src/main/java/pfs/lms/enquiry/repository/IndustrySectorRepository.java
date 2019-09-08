package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.IndustrySector;
import pfs.lms.enquiry.domain.ProjectType;

import java.util.UUID;

public interface IndustrySectorRepository extends JpaRepository<IndustrySector, UUID> {
}
