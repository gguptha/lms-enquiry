package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class ProjectMonitoringDataItemController {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final ProjectMonitoringDataItemHistoryRepository projectMonitoringDataItemHistoryRepository;

    @PutMapping("/projectMonitoringDataItems/{projectMonitoringDataItemId}")
    public ResponseEntity<ProjectMonitoringDataItem> updateProjectMonitoringDataItem(
            @PathVariable UUID projectMonitoringDataItemId,
            @RequestBody ProjectMonitoringDataItemResource projectMonitoringDataItemResource) {

        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository
                .findByLoanMonitorLoanApplicationId(projectMonitoringDataItemResource.getLoanApplicationId());

        ProjectMonitoringDataItem projectMonitoringDataItem = projectMonitoringDataItemRepository
                .findById(projectMonitoringDataItemId)
                .orElseThrow(() -> new EntityNotFoundException(projectMonitoringDataItemId.toString()));

        if (projectMonitoringDataItem.getDateOfEntry() != null) {
            ProjectMonitoringDataItemHistory projectMonitoringDataItemHistory = new ProjectMonitoringDataItemHistory();
            projectMonitoringDataItemHistory.setProjectMonitoringDataId(projectMonitoringData.getId().toString());
            projectMonitoringDataItemHistory.setDateOfEntry(projectMonitoringDataItem.getDateOfEntry());
            projectMonitoringDataItemHistory.setParticulars(projectMonitoringDataItem.getParticulars());
            projectMonitoringDataItemHistory.setDescription(projectMonitoringDataItem.getDescription());
            projectMonitoringDataItemHistory.setOriginalData(projectMonitoringDataItem.getOriginalData());
            projectMonitoringDataItemHistory.setRevisedData1(projectMonitoringDataItem.getRevisedData1());
            projectMonitoringDataItemHistory.setRevisedData2(projectMonitoringDataItem.getRevisedData2());
            projectMonitoringDataItemHistory.setRemarks(projectMonitoringDataItem.getRemarks());
            projectMonitoringDataItemHistoryRepository.save(projectMonitoringDataItemHistory);
        }

        projectMonitoringDataItem.setDateOfEntry(projectMonitoringDataItemResource.getDateOfEntry());
        projectMonitoringDataItem.setOriginalData(projectMonitoringDataItemResource.getOriginalData());
        projectMonitoringDataItem.setRevisedData1(projectMonitoringDataItemResource.getRevisedData1());
        projectMonitoringDataItem.setRevisedData2(projectMonitoringDataItemResource.getRevisedData2());
        projectMonitoringDataItem.setRemarks(projectMonitoringDataItemResource.getRemarks());
        projectMonitoringDataItem = projectMonitoringDataItemRepository.save(projectMonitoringDataItem);

        return ResponseEntity.ok(projectMonitoringDataItem);
    }
}
