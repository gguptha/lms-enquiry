package pfs.lms.enquiry.domain;

import lombok.*;
import org.hibernate.annotations.IndexColumn;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChangeDocument extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private Long riskModelTemplateId;

    @Nullable
    private String userName;

    @Nullable
    private Date date;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private LoanApplication loanApplication;

    @Nullable
    private String loanNumber;

    @Nullable
    private String loanEnquiryId;


    @NotNull
    private String action;



    @Nullable
    @IndexColumn(name = "INDEX_COL12")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="changeDocumentItem__Id",referencedColumnName = "id")
    private List<ChangeDocumentItem> changeDocumentItems;


    public ChangeDocumentItem addChangeDocumentItem (ChangeDocumentItem changeDocumentItem) {

        this.getChangeDocumentItems().add(changeDocumentItem);
        return changeDocumentItem;
    }




}
