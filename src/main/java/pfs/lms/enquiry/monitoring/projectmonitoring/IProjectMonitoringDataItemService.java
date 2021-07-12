package pfs.lms.enquiry.monitoring.projectmonitoring;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface IProjectMonitoringDataItemService {
    ProjectMonitoringDataItem saveProjectMonitoringDataItem(ProjectMonitoringDataItemResource projectMonitoringDataItemResource, HttpServletRequest servletRequest);
    ProjectMonitoringDataItem updateProjectMonitoringDataItem(UUID projectMonitoringDataItemId,
                                                              ProjectMonitoringDataItemResource projectMonitoringDataItemResource, HttpServletRequest servletRequest) throws CloneNotSupportedException;
}
