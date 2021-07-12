package pfs.lms.enquiry.monitoring.promoterdetails;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PromoterDetailResource {

    private UUID loanApplicationId;
    private PromoterDetail promoterDetail;
}
