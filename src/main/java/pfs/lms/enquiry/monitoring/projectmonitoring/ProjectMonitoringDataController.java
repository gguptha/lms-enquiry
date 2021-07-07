package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.LoanMonitorRepository;

import java.util.*;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class ProjectMonitoringDataController {

    private final ProjectMonitoringDataRepository projectMonitoringDataRepository;
    private final ProjectMonitoringDataItemRepository projectMonitoringDataItemRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanMonitorRepository loanMonitorRepository;

    @PostMapping("/projectMonitoringDatas/loanApplication/{loanApplicationId}")
    public ResponseEntity<ProjectMonitoringData> createProjectMonitoringData(@PathVariable UUID loanApplicationId) {

        LoanApplication loanApplication = loanApplicationRepository.getOne(loanApplicationId);

        LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(loanMonitor == null)
        {
            loanMonitor = new LoanMonitor();
            loanMonitor.setLoanApplication(loanApplication);
            loanMonitor = loanMonitorRepository.save(loanMonitor);
        }

        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository.
                findByLoanMonitorLoanApplicationId(loanApplicationId);
        if (projectMonitoringData == null) {
            // Create project monitoring data
            projectMonitoringData = new ProjectMonitoringData();
            projectMonitoringData.setLoanMonitor(loanMonitor);

            // Create default project monitoring data items
            List<ProjectMonitoringDataItem> projectMonitoringDataItems = new ArrayList<>();

            ProjectMonitoringDataItem projectMonitoringDataItem = new ProjectMonitoringDataItem(1, null, "1",
                    "Unit Size", "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(2, null, "2", "Overall Project Cost",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(3, null, "3", "Debt Equity Ratio",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(4, null, "4", "Total Debt",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(5, null, "5", "LEV. Cost of Supply w/o ROE - Total",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(6, null, "6", "DSCR (MIN)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(7, null, "7", "DSCR (MAX)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(8, null, "8", "Offtake Volume",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(9, null, "9", "Offtake Mix",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(10, null, "10", "Sale Rate",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(11, null, "11", "Fuel Mix",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(12, null, "12", "Fuel Cost",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(13, null, "13", "Construction Period",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(14, null, "14", "SCOD(3 Revisions)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringData.setProjectMonitoringDataItems(projectMonitoringDataItems);
            projectMonitoringData = projectMonitoringDataRepository.save(projectMonitoringData);
        }

        Collections.sort(projectMonitoringData.getProjectMonitoringDataItems(), Comparator.comparingInt((ProjectMonitoringDataItem a) ->
                a.getSerialNumber()));

        return ResponseEntity.ok(projectMonitoringData);
    }

    @GetMapping("/projectMonitoringDatas/loanApplication/{loanApplicationId}")
    public ResponseEntity<ProjectMonitoringData> getProjectMonitoringData(@PathVariable UUID loanApplicationId) {
        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository.
                findByLoanMonitorLoanApplicationId(loanApplicationId);
        if (projectMonitoringData != null && projectMonitoringData.getProjectMonitoringDataItems() != null)
            Collections.sort(projectMonitoringData.getProjectMonitoringDataItems(), Comparator.comparingInt((ProjectMonitoringDataItem a) ->
                    a.getSerialNumber()));
        return ResponseEntity.ok(projectMonitoringData);
    }
}
