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
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.dto.WorkflowTaskDTO;
import pfs.lms.enquiry.service.workflow.IWorkflowService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/tasklist")
    public ResponseEntity<List<WorkflowTaskDTO>> getTasks(HttpServletRequest httpServletRequest) {

        List<WorkflowTaskDTO> workflowTaskDTOList = new ArrayList<WorkflowTaskDTO>();

        workflowTaskDTOList  = workflowService.getTasks(httpServletRequest);

        return ResponseEntity.ok(workflowTaskDTOList);

    }


    @GetMapping("/start-process")
    public ResponseEntity<String> startProcess(String businessProcessId,
                                               String loanContractId,
                                               String requestorName,
                                               String requestorEmail,
                                               String processName,
                                               HttpServletRequest httpServletRequest) {

        String processInstanceId = workflowService.startWorkflowProcessInstance(businessProcessId,
                loanContractId,httpServletRequest.getUserPrincipal().getName(),requestorEmail,processName);


        return ResponseEntity.ok(processInstanceId);

    }


    @GetMapping("/approve-task")
    public ResponseEntity<String> approveTask(String processInstanceId, String loanContractId, HttpServletRequest httpServletRequest) {


        workflowService.approveTask(processInstanceId, loanContractId);


        return ResponseEntity.ok("Approved");

    }

    @GetMapping("/reject-task")
    public ResponseEntity<String> rejectTask(String processInstanceId, String loanContractId, HttpServletRequest httpServletRequest) {


        workflowService.rejectTask(processInstanceId, loanContractId);


        return ResponseEntity.ok("Approved");

    }
    }
