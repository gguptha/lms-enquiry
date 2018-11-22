package pfs.lms.enquiry.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanApplicationStatus extends AbstractEntity{

    @Type(type = "uuid-char")
    private UUID loanApplicationId;

    private Integer status;

    private LocalDate createdOn;

    private LocalTime createdAt;

    private String createdByUserName;

    public LoanApplicationStatus(LoanApplication loanApplication){
        this.loanApplicationId = loanApplication.getId();
        this.status = 01;
        this. createdOn = LocalDate.now();
        this.createdAt = LocalTime.now();
        this.createdByUserName = loanApplication.getCreatedByUserName();
    }

    public static LoanApplicationStatus reject(LoanApplication loanApplication){
        LoanApplicationStatus status = new LoanApplicationStatus();
        status.setCreatedAt(LocalTime.now());
        status.setCreatedByUserName(loanApplication.getChangedByUserName());
        status.setCreatedOn(LocalDate.now());
        status.setLoanApplicationId(loanApplication.getId());
        status.setStatus(8);
        return status;
    }
}
