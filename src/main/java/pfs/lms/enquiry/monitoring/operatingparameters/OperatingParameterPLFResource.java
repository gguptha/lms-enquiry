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

public class OperatingParameterPLFResource {

    private UUID loanApplicationId;
    private OperatingParameterPLF operatingParameterPLF;
}
