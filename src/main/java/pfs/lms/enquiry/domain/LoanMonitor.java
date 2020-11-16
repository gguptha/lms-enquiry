package pfs.lms.enquiry.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LoanMonitor extends AggregateRoot<LoanMonitor> {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private LoanApplication loanApplication;

}
