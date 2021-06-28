package pfs.lms.enquiry.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.SAPIntegrationPointer;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFee;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFeeRepository;
import pfs.lms.enquiry.monitoring.lie.LIERepository;
import pfs.lms.enquiry.monitoring.lie.LendersIndependentEngineer;
import pfs.lms.enquiry.monitoring.resource.*;
import pfs.lms.enquiry.monitoring.service.ISAPFileUploadIntegrationService;
import pfs.lms.enquiry.monitoring.service.ISAPLoanMonitoringIntegrationService;
import pfs.lms.enquiry.repository.SAPIntegrationRepository;
import pfs.lms.enquiry.repository.UserRepository;
import pfs.lms.enquiry.resource.FileResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;
import pfs.lms.enquiry.vault.FilePointer;
import pfs.lms.enquiry.vault.FileStorage;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sajeev on 11-Aug-19.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoanMonitoringScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value("${sap.lieUri}")
    private String lieUri;

    @Value("${sap.lieReportAndFeeUri}")
    private String lieReportAndFeeUri;

    @Value("${sap.monitorDocumentUri}")
    private String monitorDocumentUri;

    private  final ISAPIntegrationService isapIntegrationService;

    private final FileStorage fileStorage;


    private final UserRepository userRepository;

    private final SAPIntegrationRepository sapIntegrationRepository;

    private final ISAPLoanMonitoringIntegrationService sapLoanMonitoringIntegrationService;

    private final ISAPFileUploadIntegrationService fileUploadIntegrationService;

    private final SAPLIEResource saplieResource;

    private final SAPLIEReportAndFeeResource saplieReportAndFeeResource;

    private final SAPDocumentAttachmentResource sapDocumentAttachmentResource;

    private final LIERepository lieRepository;

    private final LIEReportAndFeeRepository lieReportAndFeeRepository;


     @Scheduled(fixedRate = 5000)
    public void syncLoanApplicationsToBackend() throws ParseException, IOException {


         LendersIndependentEngineer lendersIndependentEngineer = new LendersIndependentEngineer();

         User lastChangedByUser = new User();
         lastChangedByUser = userRepository.findByEmail(lendersIndependentEngineer.getChangedByUserName());

         Object response = new Object();
         Object resource;

         //Collect SAPIntegrationPointer with the following  Posting Status = 0
         List<SAPIntegrationPointer> sapIntegrationPointers = new ArrayList<>();
         sapIntegrationPointers.addAll(sapIntegrationRepository.getByBusinessProcessNameAndStatus("Monitoring", 0));
         sapIntegrationPointers.addAll(sapIntegrationRepository.getByBusinessProcessNameAndStatus("Monitoring", 2));

         // SAPDocumentAttachmentResource sapDocumentAttachmentResource = new SAPDocumentAttachmentResource();


         for (SAPIntegrationPointer sapIntegrationPointer : sapIntegrationPointers) {

             switch (sapIntegrationPointer.getSubBusinessProcessName()) {
                 case "Lenders Independent Engineer":

                     log.info("Attempting to Post LIE to SAP AT :" + dateFormat.format(new Date()));
                     lendersIndependentEngineer = lieRepository.getOne(sapIntegrationPointer.getBusinessObjectId());

                     //Set Status as in progress
                     sapIntegrationPointer.setStatus(1); // In Posting Process
                     sapIntegrationRepository.save(sapIntegrationPointer);

                     SAPLIEResourceDetails saplieResourceDetails = saplieResource.mapToSAP(lendersIndependentEngineer, lastChangedByUser);
                     SAPLIEResource d = new SAPLIEResource();
                     d.setSaplieResourceDetails(saplieResourceDetails);

                     resource = (Object) d;
                     response = sapLoanMonitoringIntegrationService.postResourceToSAP(d, lieUri, HttpMethod.POST, MediaType.APPLICATION_JSON);

                     updateSAPIntegrationPointer(response, sapIntegrationPointer);
                     break;

                 case "LIE Report And Fee":

                     LIEReportAndFee lieReportAndFee = new LIEReportAndFee();
                     log.info("Attempting to Post LIE  Report and Fee to SAP AT :" + dateFormat.format(new Date()));
                     Optional<LIEReportAndFee> lieRF = lieReportAndFeeRepository.findById(sapIntegrationPointer.getBusinessObjectId().toString());

                     lieReportAndFee = lieRF.get();

                     //Set Status as in progress
                     sapIntegrationPointer.setStatus(1); // In Posting Process
                     sapIntegrationRepository.save(sapIntegrationPointer);

                     SAPLIEReportAndFeeResourceDetails saplieReportAndFeeResourceDetails = saplieReportAndFeeResource.mapToSAP(lieReportAndFee, lastChangedByUser);
                     SAPLIEReportAndFeeResource c = new SAPLIEReportAndFeeResource();
                     c.setSaplieReportAndFeeResourceDetails(saplieReportAndFeeResourceDetails);
                     resource = (Object) c;
                     response = sapLoanMonitoringIntegrationService.postResourceToSAP(resource, lieReportAndFeeUri, HttpMethod.POST, MediaType.APPLICATION_JSON);

                     if (response != null) {
                         response = postDocument(lieReportAndFee.getFileReference(), lieReportAndFee.getId(), "LIE Report & Fee", lieReportAndFee.getDocumentTitle());
                     }

                     updateSAPIntegrationPointer(response,sapIntegrationPointer);

             }
         }
     }

    private Object postDocument(String fileReference,
                                String entityId,
                                String entityName,
                                String fileName) throws IOException {

        UUID fileUUID = UUID.fromString(fileReference);
        byte[] file = fileStorage.download(fileUUID);
        FileResource fileResource = fileStorage.getFile(fileUUID);
        Optional<FilePointer> filePointer = fileStorage.findFile(fileUUID);
        FilePointer fp = filePointer.get();

        com.google.common.net.MediaType mediaType = fp.getMediaType().get();
        //MediaType mediaType = (MediaType) mediaTypeOptional.get();

        String mimeType = mediaType.toString();
        String filePath = fileStorage.getFilePath(fileUUID);


        SAPDocumentAttachmentResourceDetails sapDocumentAttachmentResourceDetails = new SAPDocumentAttachmentResourceDetails();
        if (mimeType == "")
            mimeType = "application/pdf";

         sapDocumentAttachmentResourceDetails = sapDocumentAttachmentResource.mapToSAP(
                fileUUID.toString(),
                entityId,
                entityName, file.toString(),
                mimeType,
                fileName );

        sapDocumentAttachmentResource.setSapDocumentAttachmentResourceDetails(sapDocumentAttachmentResourceDetails);
        Object d1 = (Object) sapDocumentAttachmentResource;

        String fileType = new String();
        String [] mimeTypeParts = mimeType.split("\\/") ;
        mimeType = mimeTypeParts[1];
        fileType = mimeTypeParts[0];

        String documentUploadUri = monitorDocumentUri + "("
                + "Id='" + fileUUID.toString() + "',"
                + "EntityId='" +entityId +  "',"
                + "EntityName='" +entityName +  "',"
                + "MimeType='" +mimeType +  "',"
                + "Filename='" +fileName +  "',"
                + "FileType='" +fileType +  "',"
                + ")/$value";


        Object response =  fileUploadIntegrationService.fileUploadToSAP(documentUploadUri, filePath);



        return  response;
    }


    private MediaType getMediaType(String mimeType) {

         MediaType mediaType = MediaType.MULTIPART_FORM_DATA;
         switch (mimeType) {
             case "application/pdf":
                 mediaType = MediaType.APPLICATION_PDF;
                 break;
             case "image/jpg":
                 mediaType = MediaType.IMAGE_JPEG;
                 break;
             case "text/plain":
                 mediaType = MediaType.TEXT_PLAIN;
                 break;
             case "image/jpeg":
                 mediaType = MediaType.IMAGE_JPEG;
         }

         return mediaType;
    }


    private void updateSAPIntegrationPointer(Object response, SAPIntegrationPointer sapIntegrationPointer) {

        if (response != null) {

            if (response == null) {
                //Set Status as Failed
                sapIntegrationPointer.setStatus(2); // Posting Failed
                sapIntegrationRepository.save(sapIntegrationPointer);
            } else {
                //Set Status as Posted Successfully
                sapIntegrationPointer.setStatus(3); // Posting Successful
                sapIntegrationRepository.save(sapIntegrationPointer);
            }
        }
    }

}








