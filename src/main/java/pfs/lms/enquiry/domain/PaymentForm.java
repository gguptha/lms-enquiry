package pfs.lms.enquiry.domain;

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
public class PaymentForm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;

    private String description;

//    JM	Mid-year
//    JN	Yearly at end of period
//    JV	Yearly at start of period
//    MM	Middle of the month
//    MN	Monthly at end of period
//    MV	Monthly at start of period
//    QM	Quarterly at mid of period
//    V5	Quarterly at start + 5 days
//    VN	Quarterly at end of period
//    VV	Quarterly at start of period
//    Z1	monthly at end + 15 days
//    Z2	Quarterly at end + 15 days

}
