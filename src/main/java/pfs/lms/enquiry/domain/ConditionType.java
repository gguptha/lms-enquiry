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
public class ConditionType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;

    private String description;
//
//    2603 IOA for Principal
//    2605 IOA on Interest
//    2607 IOA on IOA
//    201  Nominal interest

}
