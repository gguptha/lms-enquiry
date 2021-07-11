package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfs.lms.enquiry.service.changedocs.IChangeDocumentService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectMonitoringDataItemService implements IProjectMonitoringDataItemService {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final ProjectMonitoringDataItemHistoryService projectMonitoringDataItemHistoryService;
    private final IChangeDocumentService changeDocumentService;

    @Override
    public ProjectMonitoringDataItem saveProjectMonitoringDataItem(ProjectMonitoringDataItemResource projectMonitoringDataItemResource,
                                                                   HttpServletRequest request) {
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

        ProjectMonitoringData projectMonitoringData =
                projectMonitoringDataRepository.findByLoanMonitorLoanApplicationId(projectMonitoringDataItemResource.getLoanApplicationId());

        // Change Documents for Project Monitoring Item
        changeDocumentService.createChangeDocument(
                projectMonitoringData.getLoanMonitor().getId(),
                projectMonitoringDataItemResource.getId().toString(),
                null,
                projectMonitoringData.getLoanMonitor().getLoanApplication().getLoanContractId(),
                null,
                projectMonitoringDataItemResource,
                "Created",
                request.getUserPrincipal().getName(),
                "Monitoring", "Project Monitoring Item");

        return projectMonitoringDataItem;
    }

    @Override
    @Transactional
    public ProjectMonitoringDataItem updateProjectMonitoringDataItem(UUID projectMonitoringDataItemId,
                                                                     ProjectMonitoringDataItemResource projectMonitoringDataItemResource,
                                                                     HttpServletRequest request) throws CloneNotSupportedException {
        ProjectMonitoringDataItem projectMonitoringDataItem = projectMonitoringDataItemRepository
                .findById(projectMonitoringDataItemId)
                .orElseThrow(() -> new EntityNotFoundException(projectMonitoringDataItemId.toString()));

        Object existingProjectMonitoringItemObject = projectMonitoringDataItem.clone();
        ProjectMonitoringDataItem existingProjectMonitoringItem = (ProjectMonitoringDataItem) existingProjectMonitoringItemObject;

        if (projectMonitoringDataItem.getDateOfEntry() != null) {
            projectMonitoringDataItemHistoryService.saveProjectMonitoringDataItemHistory(
                    projectMonitoringDataItemResource.getLoanApplicationId(), projectMonitoringDataItemId,request);
        }

        projectMonitoringDataItem.setDateOfEntry(projectMonitoringDataItemResource.getDateOfEntry());
        projectMonitoringDataItem.setOriginalData(projectMonitoringDataItemResource.getOriginalData());
        projectMonitoringDataItem.setRevisedData1(projectMonitoringDataItemResource.getRevisedData1());
        projectMonitoringDataItem.setRevisedData2(projectMonitoringDataItemResource.getRevisedData2());
        projectMonitoringDataItem.setRemarks(projectMonitoringDataItemResource.getRemarks());
        projectMonitoringDataItem = projectMonitoringDataItemRepository.save(projectMonitoringDataItem);

        ProjectMonitoringData projectMonitoringData =
                projectMonitoringDataRepository.findByLoanMonitorLoanApplicationId(projectMonitoringDataItemResource.getLoanApplicationId());

        // Change Documents for Project Monitoring Item
        changeDocumentService.createChangeDocument(
                projectMonitoringData.getLoanMonitor().getId(), projectMonitoringDataItemResource.getId().toString(),null,
                projectMonitoringData.getLoanMonitor().getLoanApplication().getLoanContractId(),
                existingProjectMonitoringItem,
                projectMonitoringDataItemResource,
                "Updated",
                request.getUserPrincipal().getName(),
                "Monitoring", "Project Monitoring Item");

        return projectMonitoringDataItem;
    }
}
