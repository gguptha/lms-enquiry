package pfs.lms.enquiry.monitoring.projectmonitoring;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface IProjectMonitoringDataItemHistoryService {
    ProjectMonitoringDataItemHistory saveProjectMonitoringDataItemHistory(UUID loanApplicationId,
                                                                          UUID projectMonitoringDataItemId,
                                                                          HttpServletRequest request);

}
