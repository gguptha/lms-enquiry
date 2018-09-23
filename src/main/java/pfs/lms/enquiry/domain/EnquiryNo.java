package pfs.lms.enquiry.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class EnquiryNo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="ENQ_SEQ")
    @SequenceGenerator(name="ENQ_SEQ",sequenceName="ENQ_SEQ",allocationSize=1)
    private Long id;

}