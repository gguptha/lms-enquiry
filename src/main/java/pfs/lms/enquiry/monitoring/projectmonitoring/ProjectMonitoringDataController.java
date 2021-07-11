package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.UUID;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class ProjectMonitoringDataController {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;

    private final IProjectMonitoringDataService projectMonitoringDataService;

    @PostMapping("/projectMonitoringDatas/loanApplication/{loanApplicationId}")
    public ResponseEntity<ProjectMonitoringData> saveProjectMonitoringData(@PathVariable UUID loanApplicationId) {

        ProjectMonitoringData projectMonitoringData = projectMonitoringDataService
                .saveProjectMonitoringData(loanApplicationId);
        return ResponseEntity.ok(projectMonitoringData);
    }

    @GetMapping("/projectMonitoringDatas/loanApplication/{loanApplicationId}")
    public ResponseEntity<ProjectMonitoringData> getProjectMonitoringData(@PathVariable UUID loanApplicationId) {
        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository.
                findByLoanMonitorLoanApplicationId(loanApplicationId);
        if (projectMonitoringData != null && projectMonitoringData.getProjectMonitoringDataItems() != null)
            projectMonitoringData.getProjectMonitoringDataItems().sort(Comparator.comparingInt(ProjectMonitoringDataItem
                    ::getSerialNumber));
        return ResponseEntity.ok(projectMonitoringData);
    }
}
