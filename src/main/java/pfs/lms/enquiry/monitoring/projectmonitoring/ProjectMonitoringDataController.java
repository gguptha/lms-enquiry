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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            projectMonitoringData.setDateOfChange(LocalDate.now());
            projectMonitoringData.setLoanMonitor(loanMonitor);

            // Create default project monitoring data items
            List<ProjectMonitoringDataItem> projectMonitoringDataItems = new ArrayList<>();

            ProjectMonitoringDataItem projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "1",
                    "Unit Size", "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "2", "Overall Project Cost",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "3", "Debt Equity Ratio",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "4", "Total Debt",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "5", "LEV. Cost of Supply w/o ROE - Total",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "6", "DSCR (MIN)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "7", "DSCR (MAX)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "8", "Offtake Volume",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "9", "Offtake Mix",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "10", "Sale Rate",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "11", "Fuel Mix",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "12", "Fuel Cost",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "13", "Construction Period",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringDataItem = new ProjectMonitoringDataItem(LocalDate.now(), "14", "SCOD(3 Revisions)",
                    "", "", "", "");
            projectMonitoringDataItems.add(projectMonitoringDataItem);

            projectMonitoringData.setProjectMonitoringDataItems(projectMonitoringDataItems);
            projectMonitoringData = projectMonitoringDataRepository.save(projectMonitoringData);
        }

        return ResponseEntity.ok(projectMonitoringData);
    }

    @GetMapping("/projectMonitoringDatas/loanApplication/{loanApplicationId}")
    public ResponseEntity<ProjectMonitoringData> getProjectMonitoringData(@PathVariable UUID loanApplicationId) {
        ProjectMonitoringData projectMonitoringData = projectMonitoringDataRepository.
                findByLoanMonitorLoanApplicationId(loanApplicationId);
        return ResponseEntity.ok(projectMonitoringData);
    }
}
