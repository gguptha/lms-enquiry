package pfs.lms.enquiry.monitoring.projectmonitoring;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Projection(name = "defaultProjectMonitoringDataProjection", types = { ProjectMonitoringData.class })
public interface DefaultProjectMonitorningDataProjection {

    UUID getId();

    LocalDate getDateOfChange();

    List<ProjectMonitoringDataItem> getProjectMonitoringDataItems();
}
