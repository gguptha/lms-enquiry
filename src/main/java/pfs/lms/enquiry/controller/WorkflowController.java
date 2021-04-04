package pfs.lms.enquiry.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.dto.WorkflowTaskDTO;
import pfs.lms.enquiry.repository.LoanMonitorRepository;
import pfs.lms.enquiry.service.workflow.IWorkflowService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//
//import org.activiti.engine.RuntimeService;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Slf4j
@ApiController
@RequiredArgsConstructor
public class WorkflowController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private LoanMonitorRepository loanMonitorRepository;

    @GetMapping("/tasklist")
    public ResponseEntity<List<WorkflowTaskDTO>> getTasks(HttpServletRequest httpServletRequest) {

        List<WorkflowTaskDTO> workflowTaskDTOList = new ArrayList<WorkflowTaskDTO>();

        workflowTaskDTOList  = workflowService.getTasks(httpServletRequest);

        return ResponseEntity.ok(workflowTaskDTOList);

    }


    @PutMapping("/start-process")
    public ResponseEntity<String> startProcess(UUID businessProcessId,
                                               String requestorName,
                                               String requestorEmail,
                                               String processName,
                                               HttpServletRequest httpServletRequest) {
        //Fetch the Entity
        LoanMonitor loanMonitor = loanMonitorRepository.getOne(businessProcessId);

        String loanContractId =  loanMonitor.getLoanApplication().getLoanContractId();

        String processInstanceId = workflowService.startWorkflowProcessInstance(businessProcessId,
                loanContractId,httpServletRequest.getUserPrincipal().getName(),requestorEmail,processName);

        // Set the Work Flow Status Code "02" - Sent for Approval

        loanMonitor.setWorkFlowStatusCode(02); loanMonitor.setWorkFlowStatusDescription("Sent for Approval");

        // Set the Workflow Process Instance on the Entity
        if (processInstanceId != null) {
            loanMonitor.setProcessInstanceId(processInstanceId);
        }

        // Save the Entity
        loanMonitorRepository.save(loanMonitor);


        return ResponseEntity.ok(processInstanceId);

    }


    @PutMapping("/approve-task")
    public ResponseEntity<String> approveTask(String processInstanceId, String loanContractId, HttpServletRequest httpServletRequest) {

        // Fetch the Entity

        // Set the Workflow Status Code as "03" Approved


        workflowService.approveTask(processInstanceId, loanContractId);


        // Save the Entity


        return ResponseEntity.ok("Approved");

    }

    @PutMapping("/reject-task")
    public ResponseEntity<String> rejectTask(String processInstanceId, String loanContractId, HttpServletRequest httpServletRequest) {

        // Fetch the Entity

        // Set the Workflow Status Code as "04" - Rejected

        workflowService.rejectTask(processInstanceId, loanContractId);

        // Save the Entity

        return ResponseEntity.ok("Approved");

    }
    }
