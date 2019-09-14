package pfs.lms.enquiry.mail.resource;

import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by sajeev on 14-Sep-19.
 */
@Getter
public class MailSearchResource {
    private String toAddress;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
