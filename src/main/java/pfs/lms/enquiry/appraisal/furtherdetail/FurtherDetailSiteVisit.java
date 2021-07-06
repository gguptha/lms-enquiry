package pfs.lms.enquiry.appraisal.furtherdetail;

import lombok.*;
import pfs.lms.enquiry.domain.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"serialNumber", "dateOfSiteVisit", "fiscalYear", "documentName"}, callSuper = false)
public class FurtherDetailSiteVisit extends AggregateRoot<FurtherDetailSiteVisit> implements Cloneable {

    @ManyToOne(fetch = FetchType.EAGER)
    private FurtherDetail furtherDetail;

    private Integer serialNumber;
    private LocalDate dateOfSiteVisit;
    private String fiscalYear;
    private String documentName;
    private String fileReference;

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
