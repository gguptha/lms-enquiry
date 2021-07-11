package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectMonitoringDataItemService implements IProjectMonitoringDataItemService {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final ProjectMonitoringDataItemHistoryService projectMonitoringDataItemHistoryService;

    @Override
    public ProjectMonitoringDataItem saveProjectMonitoringDataItem(ProjectMonitoringDataItemResource projectMonitoringDataItemResource) {
        ProjectMonitoringDataItem projectMonitoringDataItem = new ProjectMonitoringDataItem(
                projectMonitoringDataItemResource.getSerialNumber(),
                projectMonitoringDataItemResource.getDateOfEntry(),
                projectMonitoringDataItemResource.getParticulars(),
                projectMonitoringDataItemResource.getDescription(),
                projectMonitoringDataItemResource.getOriginalData(),
                projectMonitoringDataItemResource.getRevisedData1(),
                projectMonitoringDataItemResource.getRevisedData2(),
                projectMonitoringDataItemResource.getRemarks()
        );
        projectMonitoringDataItem = projectMonitoringDataItemRepository.save(projectMonitoringDataItem);
        return projectMonitoringDataItem;
    }

    @Override
    @Transactional
    public ProjectMonitoringDataItem updateProjectMonitoringDataItem(UUID projectMonitoringDataItemId,
                                                                     ProjectMonitoringDataItemResource projectMonitoringDataItemResource) {
        ProjectMonitoringDataItem projectMonitoringDataItem = projectMonitoringDataItemRepository
                .findById(projectMonitoringDataItemId)
                .orElseThrow(() -> new EntityNotFoundException(projectMonitoringDataItemId.toString()));

        if (projectMonitoringDataItem.getDateOfEntry() != null) {
            projectMonitoringDataItemHistoryService.saveProjectMonitoringDataItemHistory(
                    projectMonitoringDataItemResource.getLoanApplicationId(), projectMonitoringDataItemId);
        }

        projectMonitoringDataItem.setDateOfEntry(projectMonitoringDataItemResource.getDateOfEntry());
        projectMonitoringDataItem.setOriginalData(projectMonitoringDataItemResource.getOriginalData());
        projectMonitoringDataItem.setRevisedData1(projectMonitoringDataItemResource.getRevisedData1());
        projectMonitoringDataItem.setRevisedData2(projectMonitoringDataItemResource.getRevisedData2());
        projectMonitoringDataItem.setRemarks(projectMonitoringDataItemResource.getRemarks());
        projectMonitoringDataItem = projectMonitoringDataItemRepository.save(projectMonitoringDataItem);
        return projectMonitoringDataItem;
    }
}
