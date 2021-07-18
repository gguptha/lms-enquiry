package pfs.lms.enquiry.domain;

import com.sun.org.apache.xpath.internal.operations.Minus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceInterestSign {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;

    private String description;

    // + Plus
//  - Minus
//  - Multiply

}
