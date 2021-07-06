package pfs.lms.enquiry.monitoring.projectmonitoring;

import lombok.*;
import pfs.lms.enquiry.domain.AggregateRoot;
import pfs.lms.enquiry.domain.LoanMonitor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"loanMonitor"}, callSuper = false)
@NoArgsConstructor
public class ProjectMonitoringData extends AggregateRoot<ProjectMonitoringData> {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private LocalDate dateOfChange;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectMonitoringDataItem> projectMonitoringDataItems;
}
