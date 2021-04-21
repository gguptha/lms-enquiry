package pfs.lms.enquiry.service.workflow.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.domain.WorkflowApprover;
import pfs.lms.enquiry.dto.WorkflowTaskDTO;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.LoanMonitorRepository;
import pfs.lms.enquiry.repository.WorkflowApproverRepository;
import pfs.lms.enquiry.service.workflow.IWorkflowService;
import pfs.lms.enquiry.vault.FileSystemStorage;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by sajeev on 09-Mar-21.
 */
@Service
@RequiredArgsConstructor
public class WorkflowService implements IWorkflowService {

    @Value( "${spring.activiti.mail-server-user-name}" )
    private String username;

    @Autowired
    private LoanMonitorRepository loanMonitorRepository;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private WorkflowApproverRepository workflowApproverRepository;

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorage.class);


    @Override
    public Object startWorkflowProcessInstance(UUID businessProcessId,
                                               String requestorName,
                                               String requestorEmail,
                                               String processName) {

        Map<String, Object> variables = new HashMap<>();
        String processInstanceId =  new String();
        LoanMonitor loanMonitor = new LoanMonitor();
        String loanContractId = null;

        switch (processName) {
            case "Monitoring":
                //Fetch the Entity
                 loanMonitor = loanMonitorRepository.getOne(businessProcessId);
                // Set the Work Flow Status Code "02" - Sent for Approval
                loanMonitor.setWorkFlowStatusCode(02); loanMonitor.setWorkFlowStatusDescription("Sent for Approval");
                loanContractId =  loanMonitor.getLoanApplication().getLoanContractId();
                break;
            case "Appraisal" :
                break;
        }

        //Get Loan Application
        LoanApplication loanApplication = loanApplicationRepository.findByLoanContractId(loanContractId);

        //Deterimine Approver Name and Email
        WorkflowApprover workflowApprover = workflowApproverRepository.findByProcessName(processName);

        //Fill the process Variables
        variables.put("LoanProcessId",businessProcessId);
        variables.put("approverEmail", workflowApprover.getApproverEmail());
        variables.put("approverName", workflowApprover.getApproverName());
        variables.put("requestorName", requestorName);
        variables.put("requestorEmail", requestorEmail);
        variables.put("loanContractId"  , loanContractId);
        variables.put("fromEmail" , username);
        variables.put("processName"  , processName);
        variables.put("requestDate", DateTime.now().toString());
        variables.put("projectName", loanApplication.getProjectName());
        variables.put("workflowStatus", "In Approval");


        runtimeService = processEngine.getRuntimeService();


        try {

            ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("LoansOneLevelApproval", variables);
            processInstanceId = processInstance.getProcessInstanceId();

            System.out.println("Process Instance : " + processInstance.getProcessInstanceId());

        } catch (Exception ex) {
            log.info ("Exception starting workflow process ------------------------------");
            log.info(ex.toString());
        }

        switch (processName) {
            case "Monitoring":
                //Save entity with the Process Instance and workflow status code
                loanMonitor.setProcessInstanceId(processInstanceId);
                loanMonitor = loanMonitorRepository.save(loanMonitor);
                return loanMonitor;
            case "Appraisal" :
                break;
        }

        return null;
    }

    @Override
    public Object approveTask(String processInstanceId, UUID businessProcessId, String processName) {


        Map<String, Object> variables = new HashMap<>();
         LoanMonitor loanMonitor = new LoanMonitor();
        String loanContractId = null;


        switch (processName) {
            case "Monitoring":
                //Fetch the Entity
                loanMonitor = loanMonitorRepository.getOne(businessProcessId);
                // Set the Work Flow Status Code "03" - Approved
                loanMonitor.setWorkFlowStatusCode(03); loanMonitor.setWorkFlowStatusDescription("Approved");
                loanContractId =  loanMonitor.getLoanApplication().getLoanContractId();
                processInstanceId = loanMonitor.getProcessInstanceId();
                break;
            case "Appraisal" :
                break;
        }



        // Fetch the Task
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

//        if ( task == null) {
//            try {
//                throw new Exception("Workflow Task Id is NULL !");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        // Prepare Variables
        variables.put("workflowStatus", "TRUE");

        System.out.println("--------------- Workflow Task Execution  Started @ : " + DateTime.now());

        try {
            taskService.complete(task.getId(), variables);
        } catch (Exception ex) {
         log.info("WorkFlow Approval Exception : " +ex.getMessage());
        }
        System.out.println("--------------- Workflow Task Execution Finished @ : " + DateTime.now());

        switch (processName) {
            case "Monitoring":
                //Save entity with the new workflow status code
                loanMonitor.setWorkFlowStatusDescription("Approved");
                loanMonitor.setWorkFlowStatusCode(2);
                loanMonitor.setProcessInstanceId(processInstanceId);
                return loanMonitor;
            case "Appraisal" :
                break;
        }

        return null;

     }

    @Override
    public Object rejectTask(String processInstanceId, UUID businessProcessId, String processName, String rejectionReason) {



        Map<String, Object> variables = new HashMap<>();
        LoanMonitor loanMonitor = new LoanMonitor();
        String loanContractId = null;

        switch (processName) {
            case "Monitoring":
                //Fetch the Entity
                loanMonitor = loanMonitorRepository.getOne(businessProcessId);
                // Set the Work Flow Status Code "04" - Rejected
                loanMonitor.setWorkFlowStatusCode(04); loanMonitor.setWorkFlowStatusDescription("Rejected");
                loanContractId =  loanMonitor.getLoanApplication().getLoanContractId();
                processInstanceId = loanMonitor.getProcessInstanceId();
                break;
            case "Appraisal" :
                break;
        }


        // Fetch the Task
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        if ( task == null) {
            //Raise Exception
        }

        // Prepare Variables
        variables.put("workflowStatus", "FALSE");
        variables.put("rejectionReason", rejectionReason);

        System.out.println("--------------- Workflow Task Execution  Started @ : " + DateTime.now());

        try {
            taskService.complete(task.getId(), variables);
        } catch (Exception ex) {
            log.info("WorkFlow REJECTION Exception : " +ex.getMessage());
        }
        System.out.println("--------------- Workflow Task Execution Finished @ : " + DateTime.now());

        switch (processName) {
            case "Monitoring":
                //Save entity with the new workflow status code
                loanMonitor.setWorkFlowStatusDescription("Rejected");
                loanMonitor.setWorkFlowStatusCode(4);
                loanMonitor = loanMonitorRepository.save(loanMonitor);
                 return loanMonitor;
            case "Appraisal" :
                break;
        }

        return null;
    }


    @Override
    public   List<WorkflowTaskDTO>  getTasks(HttpServletRequest httpServletRequest) {

        TaskService taskService = processEngine.getTaskService();
        String userName = httpServletRequest.getUserPrincipal().getName();

        List<WorkflowTaskDTO> workflowTaskDTOList = new ArrayList<>();

        log.info(LocalDateTime.now() + ": USER NAME: " + httpServletRequest.getUserPrincipal().getName());

        List<Task>  tasks = taskService.createTaskQuery()
                .taskAssignee(userName)
                .includeProcessVariables()
                .orderByTaskCreateTime()
                .desc()
                .list();

        for (Task task: tasks) {

            // Only Active Tasks
            if (task.isSuspended() == false) {
                // System.out.println(task.getAssignee() + task.getId() + task.getProcessDefinitionId() );
                Map<String, Object> variables = task.getProcessVariables();

                WorkflowTaskDTO workflowTaskDTO = prepareWorkflowTask(task, variables);

                 // Eliminate Duplicate Workflow Tasks

                    workflowTaskDTOList.add(workflowTaskDTO);
            }
        }
        return workflowTaskDTOList;
    }



    private WorkflowTaskDTO prepareWorkflowTask ( Task task, Map<String, Object> variables) {

        WorkflowTaskDTO workflowTaskDTO = new WorkflowTaskDTO();
        workflowTaskDTO.setId(task.getId().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String dateAsString = sdf.format(task.getCreateTime());

        workflowTaskDTO.setRequestDate(dateAsString);
        workflowTaskDTO.setLanContractId(variables.get("loanContractId").toString());
        workflowTaskDTO.setProjectName(variables.get("projectName").toString());
        workflowTaskDTO.setApproverEmail(variables.get("approverEmail").toString());
        workflowTaskDTO.setApproverName(variables.get("approverName").toString());
        workflowTaskDTO.setRequestDate(variables.get("requestDate").toString());
        workflowTaskDTO.setRequestorEmail(variables.get("requestorName").toString());
        workflowTaskDTO.setRequestorName(variables.get("requestorEmail").toString());
        workflowTaskDTO.setProcessName(variables.get("processName").toString());
        workflowTaskDTO.setBusinessProcessId(variables.get("LoanProcessId").toString());

        if (variables.get("workflowStatus") != null) {
            if (variables.get("workflowStatus").toString() == "TRUE")
                workflowTaskDTO.setStatus("Approved");

            if (variables.get("workflowStatus").toString() == "FALSE")
                workflowTaskDTO.setStatus("Rejected");

            if (variables.get("workflowStatus").toString() == "In Approval")
                workflowTaskDTO.setStatus("Approved");
        }
        else
            workflowTaskDTO.setStatus("In Approval");


        return workflowTaskDTO;

    }

}
