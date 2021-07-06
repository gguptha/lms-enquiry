package pfs.lms.enquiry.monitoring.operatingparameters;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class OperatingParameterResource {

    private UUID loanApplicationId;
    private OperatingParameter operatingParameter;
}
