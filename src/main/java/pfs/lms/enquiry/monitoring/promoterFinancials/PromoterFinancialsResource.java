package pfs.lms.enquiry.monitoring.promoterFinancials;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.monitoring.promoterFinancials.PromoterFinancials;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PromoterFinancialsResource {

    private UUID loanApplicationId;
    private PromoterFinancials promoterFinancials;
}
