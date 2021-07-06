package pfs.lms.enquiry.domain;

import lombok.*;
import org.hibernate.annotations.IndexColumn;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
public class BankMaster extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankCountryKey;
    private String bankKey;
    private String creationDate;
    private String createdBy;
    private String bankName;
    private String region; //(State,Province,County)
    private String houseNumberAndStreet;
    private String city;
    private String swiftCodeOrBIC; //sWIFT/BICforInternationalPayments
    private String bankGroup; //(banknetwork)
    private String postOfficeBankCurrentAccount;
    private String deletionIndicator;
    private String bankNumber;
    private String postOfficeBankCurrentAccountNumber;
    private String addressNumber;
    private String bankBranch;
    private String checkDigitCalculationMethod;
    private String formatofFileWithBankData;
    private String bankMasterData; //(AdditionalFieldsforSEPA)
    private String supportofSEPAB2BDirectDebit;
    private String supportofSEPACOR1DirectDebit;
    private String supportofSEPAReturnedDebits; //(RTransactions)
    private String keyofaBICDataRecord; //(Swift)
    private String routingControlCode;


}
