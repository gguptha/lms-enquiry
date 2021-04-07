package pfs.lms.enquiry.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class WorkflowTaskDTO {

    private String id;
    private String businessProcessId;
    private String requestDate;
    private String lanContractId;
    private String projectName;
    private String processName;
    private String requestorName;
    private String requestorEmail;
    private String approverName;
    private String approverEmail;
    private String status;

}
