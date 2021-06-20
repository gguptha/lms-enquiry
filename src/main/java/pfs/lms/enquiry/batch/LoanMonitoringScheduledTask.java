package pfs.lms.enquiry.batch;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.SAPIntegrationPointer;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFee;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFeeRepository;
import pfs.lms.enquiry.monitoring.lie.LIERepository;
import pfs.lms.enquiry.monitoring.lie.LendersIndependentEngineer;
import pfs.lms.enquiry.monitoring.resource.SAPLIEReportAndFeeDetailsResource;
import pfs.lms.enquiry.monitoring.resource.SAPLIEReportAndFeeResource;
import pfs.lms.enquiry.monitoring.resource.SAPLIEResource;
import pfs.lms.enquiry.monitoring.service.ISAPLoanMonitoringIntegrationService;
import pfs.lms.enquiry.repository.SAPIntegrationRepository;
import pfs.lms.enquiry.repository.UserRepository;
import pfs.lms.enquiry.monitoring.resource.SAPLIEDetailsResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;
import pfs.lms.enquiry.vault.DownloadService;
import pfs.lms.enquiry.vault.IDownloadService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sajeev on 11-Aug-19.
 */
@Slf4j
@Service
@Transactional
public class LoanMonitoringScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(LoanMonitoringScheduledTask.class);

    @Value("${sap.lieUri}")
    private String lieUri;

    @Value("${sap.lfaReportAndFee}")
    private String lfaReportAndFeeUri;



    @Autowired
    private  final ISAPIntegrationService isapIntegrationService;

    @Autowired
    private final IDownloadService downloadService = null;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SAPIntegrationRepository sapIntegrationRepository;

    @Autowired
    private ISAPLoanMonitoringIntegrationService sapLoanMonitoringIntegrationService;

    @Autowired
    private SAPLIEResource saplieResource;

    @Autowired
    private SAPLIEReportAndFeeResource saplieReportAndFeeResource;

    @Autowired
    private final LIERepository lieRepository;

    @Autowired
    private final LIEReportAndFeeRepository lieReportAndFeeRepository;

    public LoanMonitoringScheduledTask(LIERepository lieRepository,
                                       ISAPIntegrationService isapIntegrationService,
                                       LIEReportAndFeeRepository lieReportAndFeeRepository) {
        this.lieRepository = lieRepository;
        this.isapIntegrationService = isapIntegrationService;
        this.lieReportAndFeeRepository = lieReportAndFeeRepository;
    }

     @Scheduled(fixedRate = 5000)
    public void syncLoanApplicationsToBackend() throws ParseException {

        LendersIndependentEngineer lendersIndependentEngineer = new LendersIndependentEngineer();

        User lastChangedByUser = new User() ;
        lastChangedByUser = userRepository.findByEmail(lendersIndependentEngineer.getChangedByUserName());

        Object response = new Object();
        Object resource;

        //Collect SAPIntegrationPointer with the following  Posting Status = 0
        List<SAPIntegrationPointer> sapIntegrationPointers = new ArrayList<>();
        sapIntegrationPointers.addAll(sapIntegrationRepository.getByBusinessProcessNameAndStatus("Monitoring",0));
        sapIntegrationPointers.addAll(sapIntegrationRepository.getByBusinessProcessNameAndStatus("Monitoring",2));



        for (SAPIntegrationPointer sapIntegrationPointer : sapIntegrationPointers) {

            switch (sapIntegrationPointer.getSubBusinessProcessName()) {
                case "Lenders Independent Engineer":

                    log.info("Attempting to Post LIE to SAP AT :" + dateFormat.format(new Date()));
                    lendersIndependentEngineer = lieRepository.getOne(sapIntegrationPointer.getBusinessObjectId());

                    //Set Status as in progress
                    sapIntegrationPointer.setStatus(1); // In Posting Process
                    sapIntegrationRepository.save(sapIntegrationPointer);

                    SAPLIEDetailsResource saplieDetailsResource = saplieResource.mapToSAP(lendersIndependentEngineer,lastChangedByUser);

                    SAPLIEResource d = new SAPLIEResource();
                    d.setSaplieDetailsResource(saplieDetailsResource);

                    resource = (Object) d;
                    response =  sapLoanMonitoringIntegrationService.postResourceToSAP(d,lieUri);

                    if ( response ==  null ) {
                        //Set Status as Failed
                        sapIntegrationPointer.setStatus(2); // Posting Failed
                        sapIntegrationRepository.save(sapIntegrationPointer);

                }

                    break;
                case "LIE Report And Fee":


                    LIEReportAndFee lieReportAndFee = new LIEReportAndFee();


                    log.info("Attempting to Post LIE  Report and Fee to SAP AT :" + dateFormat.format(new Date()));
                    Optional<LIEReportAndFee> lieRF = lieReportAndFeeRepository.findById( sapIntegrationPointer.getBusinessObjectId().toString());

                    lieReportAndFee = lieRF.get();

                    //Set Status as in progress
                    sapIntegrationPointer.setStatus(1); // In Posting Process
                    sapIntegrationRepository.save(sapIntegrationPointer);

                    SAPLIEReportAndFeeDetailsResource saplieReportAndFeeDetailsResource= saplieReportAndFeeResource.mapToSAP(lieReportAndFee,lastChangedByUser);


                    SAPLIEReportAndFeeResource c = new SAPLIEReportAndFeeResource();
                    c.setSaplieReportAndFeeDetailsResource(saplieReportAndFeeDetailsResource);

                    resource = (Object) c;
                    response =  sapLoanMonitoringIntegrationService.postResourceToSAP(resource,lfaReportAndFeeUri);




                    if ( response ==  null ) {
                        //Set Status as Failed
                        sapIntegrationPointer.setStatus(2); // Posting Failed
                        sapIntegrationRepository.save(sapIntegrationPointer);

                    }

            }

        }






    }





}
