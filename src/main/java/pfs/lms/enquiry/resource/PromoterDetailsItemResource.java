package pfs.lms.enquiry.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.PromoterDetails;
import pfs.lms.enquiry.domain.PromoterDetailsItem;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PromoterDetailsItemResource {

    private String promoterDetailsId;
    private PromoterDetailsItem promoterDetailsItem;
}
