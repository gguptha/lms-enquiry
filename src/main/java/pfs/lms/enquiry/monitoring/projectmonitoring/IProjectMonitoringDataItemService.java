package pfs.lms.enquiry.monitoring.projectmonitoring;

import java.util.UUID;

public interface IProjectMonitoringDataItemService {
    ProjectMonitoringDataItem saveProjectMonitoringDataItem(ProjectMonitoringDataItemResource projectMonitoringDataItemResource);
    ProjectMonitoringDataItem updateProjectMonitoringDataItem(UUID projectMonitoringDataItemId,
                                                              ProjectMonitoringDataItemResource projectMonitoringDataItemResource);
}
