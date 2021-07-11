package pfs.lms.enquiry.monitoring.projectmonitoring;

import java.util.UUID;

public interface IProjectMonitoringDataService {
    ProjectMonitoringData saveProjectMonitoringData(UUID loanApplicationId);
}
