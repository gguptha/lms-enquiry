package pfs.lms.enquiry.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

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
public class WorkflowRequestResource {

    private UUID businessProcessId;
    private String requestorName;
    private String requestorEmail;
    private String processName;
}
