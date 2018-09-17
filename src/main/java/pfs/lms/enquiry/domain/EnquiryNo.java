package pfs.lms.enquiry.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EnquiryNo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="ENQ_SEQ")
    @SequenceGenerator(name="ENQ_SEQ",sequenceName="ENQ_SEQ",allocationSize=1)
    private Long id;

    public EnquiryNo(){}
}