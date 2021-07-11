package pfs.lms.enquiry.monitoring.projectmonitoring;

import java.util.UUID;

public interface IProjectMonitoringDataItemHistoryService {
    ProjectMonitoringDataItemHistory saveProjectMonitoringDataItemHistory(UUID loanApplicationId,
                                                                          UUID projectMonitoringDataItemId);

}
