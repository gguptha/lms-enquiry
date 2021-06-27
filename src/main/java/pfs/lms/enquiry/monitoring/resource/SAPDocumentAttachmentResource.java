package pfs.lms.enquiry.monitoring.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by sajeev on 24-Jun-21.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SAPDocumentAttachmentResource implements Serializable {



    @JsonProperty(value = "d")
    private SAPDocumentAttachmentDetailsResource sapDocumentAttachmentDetailsResource;


//    public SAPDocumentAttachmentDetailsResource getSAPDocumentAttachmentResource() {
//        return sapDocumentAttachmentDetailsResource;
//    }

    public void setSapDocumentAttachmentDetailsResource(SAPDocumentAttachmentDetailsResource sapDocumentAttachmentDetailsResource) {
        this.sapDocumentAttachmentDetailsResource = sapDocumentAttachmentDetailsResource;
    }

    public SAPDocumentAttachmentDetailsResource mapToSAP(String id, String entityId, String entityName, String documentContent, String mimeType, String fileName) {

        SAPDocumentAttachmentDetailsResource documentAttachmentDetailsResource = new SAPDocumentAttachmentDetailsResource();

        documentAttachmentDetailsResource.setDocumentcontent(documentContent);
        documentAttachmentDetailsResource.setId(id);
        documentAttachmentDetailsResource.setMimeType(mimeType);
        documentAttachmentDetailsResource.setEntityId(entityId);
        documentAttachmentDetailsResource.setEntityName(entityName);
        documentAttachmentDetailsResource.setFilename(fileName);

        return documentAttachmentDetailsResource;
    }
}
