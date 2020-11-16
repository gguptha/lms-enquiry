package pfs.lms.enquiry.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LendersIndependentEngineer extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;

    private String advisor;

    private String bpCode;

    private String name;

    private LocalDate dateOfAppointment;

    private LocalDate contractPeriodFrom;

    private LocalDate contractPeriodTo;

    private String contactPerson;

    private String contactNumber;

    private String email;
}
