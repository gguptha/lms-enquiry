package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.*;

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
public class ChangeDocumentItem extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer itemNo;

    private String entityName;

    @Column(columnDefinition = "LONGTEXT")
    private String attributeName;

    private String tableKey;

    @Column(columnDefinition = "LONGTEXT")
    private String entityDescription;

    private String oldValue;

    private String newValue;




}
