package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.TermsAndConditionsModification;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class TermsAndConditionsResource  implements Cloneable{

    private UUID loanApplicationId;
    private TermsAndConditionsModification termsAndConditionsModification;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

}
