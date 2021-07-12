package pfs.lms.enquiry.monitoring.projectmonitoring;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface IProjectMonitoringDataService {
    ProjectMonitoringData saveProjectMonitoringData(UUID loanApplicationId, HttpServletRequest httpServletRequest);
}
