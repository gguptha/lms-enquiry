package pfs.lms.enquiry.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.RateOfInterest;
import pfs.lms.enquiry.domain.SiteVisit;

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
