package pfs.lms.enquiry.monitoring.borrowerfinancials;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BorrowerFinancialsResource {

    private UUID loanApplicationId;
    private BorrowerFinancials borrowerFinancials;
}
