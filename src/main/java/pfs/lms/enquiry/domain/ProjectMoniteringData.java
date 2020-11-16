package pfs.lms.enquiry.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ProjectMoniteringData extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanMonitor loanMonitor;
}
