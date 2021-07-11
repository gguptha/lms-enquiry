package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.service.changedocs.IChangeDocumentService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectMonitoringDataItemHistoryService implements IProjectMonitoringDataItemHistoryService {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final ProjectMonitoringDataItemHistoryRepository projectMonitoringDataItemHistoryRepository;
    private final IChangeDocumentService changeDocumentService;

    @Override
    public ProjectMonitoringDataItemHistory saveProjectMonitoringDataItemHistory(UUID loanApplicationId,
                                                                                 UUID projectMonitoringDataItemId,
                                                                                 HttpServletRequest request) {
        ProjectMonitoringData projectMonitoringData = new ProjectMonitoringData();
        projectMonitoringData = projectMonitoringDataRepository
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


        // Change Documents for Fin. Covenants
        changeDocumentService.createChangeDocument(
                projectMonitoringData.getLoanMonitor().getId(),
                projectMonitoringDataItemHistory.getId().toString(),null,
                projectMonitoringData.getLoanMonitor().getLoanApplication().getLoanContractId(),
                null,
                projectMonitoringDataItemHistory,
                "Created",
                request.getUserPrincipal().getName(),
                "Monitoring", "Project Monitoring History");


        return projectMonitoringDataItemHistory;
    }
}
