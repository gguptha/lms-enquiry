package pfs.lms.enquiry.service.workflow;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.dto.WorkflowTaskDTO;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sajeev on 09-Mar-21.
 */
public interface IWorkflowService {

    public Object startWorkflowProcessInstance (UUID businessProcessId,
                                                     String requestorName,
                                                     String requestorEmail,
                                                     String processName);


    public Object approveTask(String processInstanceId, UUID businessProcessId, String processName);

    public Object rejectTask(String processInstanceId, UUID businessProcessId, String processName, String rejectionReason,String requestorEmail);


    // Workflow Tasks for an User
    public  List<WorkflowTaskDTO>  getTasks(HttpServletRequest httpServletRequest);

}
