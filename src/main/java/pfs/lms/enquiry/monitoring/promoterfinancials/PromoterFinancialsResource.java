package pfs.lms.enquiry.monitoring.promoterfinancials;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
