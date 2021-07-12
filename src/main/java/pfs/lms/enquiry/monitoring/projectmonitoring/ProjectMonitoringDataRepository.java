package pfs.lms.enquiry.monitoring.projectmonitoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(excerptProjection = DefaultProjectMonitorningDataProjection.class)
public interface ProjectMonitoringDataRepository extends JpaRepository<ProjectMonitoringData, String> {

    ProjectMonitoringData findByLoanMonitorLoanApplicationId(UUID loanApplicationId);
}
