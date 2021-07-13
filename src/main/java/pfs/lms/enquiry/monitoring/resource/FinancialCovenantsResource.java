package pfs.lms.enquiry.monitoring.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.monitoring.domain.FinancialCovenants;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class FinancialCovenantsResource {

    private UUID loanApplicationId;
    private FinancialCovenants financialCovenants;
}
