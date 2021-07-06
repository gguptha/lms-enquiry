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

    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;

    @PutMapping("/projectMonitoringDataItems/{projectMonitoringDataItemId}")
    public ResponseEntity<ProjectMonitoringDataItem> updateProjectMonitoringDataItem(
            @PathVariable UUID projectMonitoringDataItemId,
            @RequestBody ProjectMonitoringDataItemResource projectMonitoringDataItemResource) {

        ProjectMonitoringDataItem projectMonitoringDataItem = projectMonitoringDataItemRepository
                .findById(projectMonitoringDataItemId)
                .orElseThrow(() -> new EntityNotFoundException(projectMonitoringDataItemId.toString()));

        projectMonitoringDataItem.setDateOfEntry(projectMonitoringDataItemResource.getDateOfEntry());
        projectMonitoringDataItem.setOriginalData(projectMonitoringDataItemResource.getOriginalData());
        projectMonitoringDataItem.setRevisedData1(projectMonitoringDataItemResource.getRevisedData1());
        projectMonitoringDataItem.setRevisedData2(projectMonitoringDataItemResource.getRevisedData2());
        projectMonitoringDataItem.setRemarks(projectMonitoringDataItemResource.getRemarks());
        projectMonitoringDataItem = projectMonitoringDataItemRepository.save(projectMonitoringDataItem);

        return ResponseEntity.ok(projectMonitoringDataItem);
    }
}
