package pfs.lms.enquiry.domain;

import lombok.*;
import org.javers.core.metamodel.annotation.DiffIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LoanMonitor extends AggregateRoot<LoanMonitor>  implements Cloneable {

    @DiffIgnore
    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private LoanApplication loanApplication;

    //Workflow Process Instance Id
    private String processInstanceId;

    //Workflow Status
    // 01 - Created //11 Updated
    // 02 - Sent for Approval
    // 03 - Approved
    // 04 - Rejected
    private Integer workFlowStatusCode;
    //Workflow Status Descr.
    private String workFlowStatusDescription;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
     }

}
