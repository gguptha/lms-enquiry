package pfs.lms.enquiry.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pfs.lms.enquiry.domain.LFAReportAndFee;
import pfs.lms.enquiry.domain.TrustRetentionAccountStatement;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class TRAStatementResource {

    private String trustRetentionAccountId;
    private TrustRetentionAccountStatement trustRetentionAccountStatement;
}
