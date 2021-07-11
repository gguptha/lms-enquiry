package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectMonitoringDataItemHistoryService implements IProjectMonitoringDataItemHistoryService {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final ProjectMonitoringDataItemHistoryRepository projectMonitoringDataItemHistoryRepository;

    @Override
    public ProjectMonitoringDataItemHistory saveProjectMonitoringDataItemHistory(UUID loanApplicationId,
                                                                                 UUID projectMonitoringDataItemId) {
        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository
                .findByLoanMonitorLoanApplicationId(loanApplicationId);
        ProjectMonitoringDataItem projectMonitoringDataItem = projectMonitoringDataItemRepository
                .findById(projectMonitoringDataItemId)
                .orElseThrow(() -> new EntityNotFoundException(projectMonitoringDataItemId.toString()));
        ProjectMonitoringDataItemHistory projectMonitoringDataItemHistory = new ProjectMonitoringDataItemHistory();
        projectMonitoringDataItemHistory.setProjectMonitoringDataId(projectMonitoringData.getId().toString());
        projectMonitoringDataItemHistory.setDateOfEntry(projectMonitoringDataItem.getDateOfEntry());
        projectMonitoringDataItemHistory.setParticulars(projectMonitoringDataItem.getParticulars());
        projectMonitoringDataItemHistory.setDescription(projectMonitoringDataItem.getDescription());
        projectMonitoringDataItemHistory.setOriginalData(projectMonitoringDataItem.getOriginalData());
        projectMonitoringDataItemHistory.setRevisedData1(projectMonitoringDataItem.getRevisedData1());
        projectMonitoringDataItemHistory.setRevisedData2(projectMonitoringDataItem.getRevisedData2());
        projectMonitoringDataItemHistory.setRemarks(projectMonitoringDataItem.getRemarks());
        projectMonitoringDataItemHistory = projectMonitoringDataItemHistoryRepository.save(projectMonitoringDataItemHistory);
        return projectMonitoringDataItemHistory;
    }
}
