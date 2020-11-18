package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.LoanContractExtension;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class LoanContractExtensionResource {

    private UUID loanApplicationId;
    private LoanContractExtension loanContractExtension;

}
