package pfs.lms.enquiry.monitoring.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.monitoring.domain.RateOfInterest;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class RateOfInterestResource {

    private UUID loanApplicationId;
    private RateOfInterest rateOfInterest;
}
