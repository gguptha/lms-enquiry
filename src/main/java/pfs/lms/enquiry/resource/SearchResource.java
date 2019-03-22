package pfs.lms.enquiry.resource;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchResource {
    private LocalDate enquiryDateFrom;
    private LocalDate enquiryDateTo;
    private String partyName;
    private Integer loanClass;
    private Integer financingType;
    private Integer enquiryNoFrom;
    private Integer enquiryNoTo;
    private Integer loanNumberFrom;
    private Integer loanNumberTo;
    private String projectLocationState;
    private Integer projectType;
    private String assistanceType;
}
