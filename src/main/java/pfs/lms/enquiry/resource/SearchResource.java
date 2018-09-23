package pfs.lms.enquiry.resource;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchResource {
    private LocalDate enquiryDateFrom;
    private LocalDate enquiryDateTo;
    private String promoterName;
    private Integer loanClass;
    private Integer financingType;
    private Integer enquiryNo;
    private String projectLocationState;
    private Integer projectType;
    private String assistanceType;
}
