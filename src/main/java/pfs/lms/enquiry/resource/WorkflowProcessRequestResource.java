package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

/**
 * Created by sajeev on 04-Apr-21.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkflowProcessRequestResource {

    private UUID   businessProcessId;
    private String processName;
    private String processInstanceId;
    private String rejectionReason;
}
