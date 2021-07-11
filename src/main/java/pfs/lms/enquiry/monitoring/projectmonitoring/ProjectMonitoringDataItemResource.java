package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMonitoringDataItemResource {

    private UUID id;
    private UUID loanApplicationId;
    private UUID projectMonitoringDataId;

    private Integer serialNumber;

    private LocalDate dateOfEntry;

    private String particulars;
    private String description;
    private String originalData;
    private String revisedData1;
    private String revisedData2;
    private String remarks;
}
