package pfs.lms.enquiry.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.LendersFinancialAdvisor;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class LFAResource {

    private UUID loanApplicationId;
    private LendersFinancialAdvisor lendersFinancialAdvisor;
}
