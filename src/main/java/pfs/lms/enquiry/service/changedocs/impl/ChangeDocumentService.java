package pfs.lms.enquiry.service.changedocs.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.*;
import pfs.lms.enquiry.monitoring.borrowerfinancials.BorrowerFinancials;
import pfs.lms.enquiry.monitoring.lfa.LFAReportAndFee;
import pfs.lms.enquiry.monitoring.lfa.LendersFinancialAdvisor;
import pfs.lms.enquiry.monitoring.lie.LIEReportAndFee;
import pfs.lms.enquiry.monitoring.lie.LendersIndependentEngineer;
import pfs.lms.enquiry.monitoring.operatingParameters.OperatingParameter;
import pfs.lms.enquiry.monitoring.promoterFinancials.PromoterFinancials;
import pfs.lms.enquiry.monitoring.tra.TrustRetentionAccount;
import pfs.lms.enquiry.monitoring.tra.TrustRetentionAccountStatement;
import pfs.lms.enquiry.repository.ChangeDocumentRepository;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.service.changedocs.IChangeDocumentService;

import javax.transaction.Transactional;
import java.util.*;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ChangeDocumentService implements IChangeDocumentService {


    @Autowired
    ChangeDocumentRepository changeDocumentRepository;

    ChangeDocument changeDocument;

    @Autowired
    LoanApplicationRepository loanApplicationRepository;


    @Override
    public ChangeDocument createChangeDocument(UUID objectId,
                                               String loanContractId,
                                               Object oldObject,
                                               Object changedObject,
                                               String action,
                                               String userName,
                                               String businessProcessName,
                                               String subProcessName, String key1, String key2) {



        changeDocument = new ChangeDocument();

        switch (action) {
            case "Created":
//                if (objectId == null) {
//                    changeDocument.setAction("Created");
                    changeDocument = prepareCreateChangeDocument(objectId,
                            loanContractId,
                            changedObject,
                            action,
                            userName,
                            businessProcessName,subProcessName,   key1,   key2);

                 break;
            case "Updated":
//                changeDocument.setAction("Sent for Approval");
                changeDocument = prepareUpdateChangeDocument(objectId,
                        loanContractId,
                        oldObject,
                        changedObject,
                        action,
                        userName,
                        businessProcessName,subProcessName,   key1,   key2);
                break;
            case "Sent for Approval":
                changeDocument.setAction("Rejected");
                changeDocument = changeDocument = prepareUpdateChangeDocument(objectId,
                        loanContractId,
                        oldObject,
                        changedObject,
                        action,
                        userName,
                        businessProcessName,subProcessName,   key1,   key2);
                break;
            case "Approved":
                changeDocument.setAction("Approved");
                changeDocument = changeDocument = prepareUpdateChangeDocument(objectId,
                        loanContractId,
                        oldObject,
                        changedObject,
                        action,
                        userName,
                        businessProcessName,subProcessName ,   key1,   key2);
                break;

            case "Rejected":
        }

        changeDocument = this.saveChangeDocument(changeDocument);

        return changeDocument;
    }


    @Override
    public ChangeDocument saveChangeDocument(ChangeDocument changeDocument) {

        changeDocument.setCreatedAt(new Date());
        changeDocument.setUpdatedAt(new Date());
        changeDocument = changeDocumentRepository.save(changeDocument);
        return changeDocument;
    }



    @Override
    public ChangeDocument updateChangeDocument(ChangeDocument changeDocument, Object object) {
        return null;
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessNameAndLoanContractIdAndDateBetween(String processName,
                                                                                         String loanContractId,
                                                                                         Date dateFrom,
                                                                                         Date dateTo,
                                                                                         Pageable pageable) {
        return null;
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessNameAndLoanContractIdAndDate(String processName,
                                                                                  String loanContractId,
                                                                                  Date date,
                                                                                  Pageable pageable) {
        return changeDocumentRepository.findByBusinessProcessNameAndLoanContractIdAndDate(processName,loanContractId,date,pageable);
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessNameAndLoanContractId(String processName,
                                                                           String loanContractId,
                                                                           Pageable pageable) {
        return changeDocumentRepository.findByBusinessProcessNameAndLoanContractId(processName,loanContractId,pageable);
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessName(String businessProcessName, Pageable pageable) {
        return changeDocumentRepository.findByBusinessProcessName(businessProcessName,pageable);
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessNameAndDateBetween(String businessProcessName, Date dateFrom, Date dateTo, Pageable pageable) {
         return changeDocumentRepository.findByBusinessProcessNameAndDateBetween(businessProcessName,dateFrom,dateTo,pageable);
    }

    @Override
    public Page<ChangeDocument> findByBusinessProcessNameAndDate(String businessProcessName, Date date, Pageable pageable) {
          return changeDocumentRepository.findByBusinessProcessNameAndDate(businessProcessName,date,pageable);
    }


    private ChangeDocument prepareCreateChangeDocument(UUID objectId,
                                                       String loanContractId,
                                                       Object changedObject,
                                                       String action,
                                                       String userName,
                                                       String businessProcessName,
                                                       String subProcessName, String key1, String key2) {

        changeDocument = prepareHeader(objectId,
                loanContractId,
                changedObject,
                action,
                userName,
                businessProcessName,  subProcessName,   key1,   key2);

        return changeDocument;


    }


    private ChangeDocument prepareUpdateChangeDocument(UUID objectId,
                                                       String loanContractId,
                                                       Object oldObject,
                                                       Object changedObject,
                                                       String action,
                                                       String userName,
                                                       String businessProcessName,
                                                       String subProcessName, String key1, String key2) {

        changeDocument = prepareHeader(objectId,
                loanContractId,
                changedObject,
                action,
                userName,
                businessProcessName,subProcessName,   key1,   key2);


        // Compare RiskModel Header //given
        Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
                .build();

        Diff diff = javers.compare(oldObject, changedObject);

        List<ChangeDocumentItem> changeDocumentItems = new ArrayList<>();
        changeDocumentItems = prepareChangeDocumentItems(diff, changeDocumentItems);




        changeDocument.setChangeDocumentItems(changeDocumentItems);


        return changeDocument;
    }




    private List<ChangeDocumentItem> prepareChangeDocumentItems(Diff diff, List<ChangeDocumentItem> changeDocumentItems) {



        int i = changeDocumentItems.size() + 1;
        for (ValueChange change : diff.getChangesByType(ValueChange.class)) {

            ChangeDocumentItem changeDocumentItem = new ChangeDocumentItem();
            changeDocumentItem.setItemNo(i);
            if (change.getPropertyName().equals("createdAt") || change.getPropertyName().equals("updatedAt"))
                continue;

            Object object = change.getAffectedObject().get();
            Map<String, String> result = getObjectDetails(object.getClass().getSimpleName(), object);

            changeDocumentItem.setEntityName(object.getClass().getSimpleName().toString());
            changeDocumentItem.setEntityDescription(result.get("description"));
            changeDocumentItem.setAttributeName(change.getPropertyName());

            if (change.getRight() != null)
                changeDocumentItem.setNewValue(change.getRight().toString());
            else
                changeDocumentItem.setNewValue(null);
            if (change.getLeft() != null)
                changeDocumentItem.setOldValue(change.getLeft().toString());
            else
                changeDocumentItem.setOldValue(null);

            changeDocumentItem.setTableKey(result.get("id"));

            changeDocumentItem.setCreatedAt(new Date());
            changeDocumentItem.setUpdatedAt(new Date());


            changeDocumentItems.add(changeDocumentItem);

            i++;

        }



        return changeDocumentItems;

    }

    private Map<String, String>    getObjectDetails(String className, Object object ) {

        Object objectParsed = new Object();

        Map<String, String> result = new HashMap<>();

        try {

            switch (className) {
                case "LoanMonitor":
                    LoanMonitor loanMonitor = (LoanMonitor) object;
                    result.put("id", loanMonitor.getLoanApplication().getLoanContractId().toString());
                    result.put("description", loanMonitor.getLoanApplication().getProjectName());
                    return result;
                case "LendersIndependentEngineer":
                    LendersIndependentEngineer lendersIndependentEngineer = (LendersIndependentEngineer) object;
                    result.put("id", lendersIndependentEngineer.getBpCode().toString());
                    result.put("description", lendersIndependentEngineer.getName());
                    return result;

                case "LIEReportAndFee":
                    LIEReportAndFee lieReportAndFee = (LIEReportAndFee) object;
                    result.put("id", lieReportAndFee.getLendersIndependentEngineer().getBpCode().toString());
                    result.put("description", lieReportAndFee.getReportType() + lieReportAndFee.getDateOfReceipt());
                    return result;

                case "LendersFinancialAdvisor":
                    LendersFinancialAdvisor lendersFinancialAdvisor = (LendersFinancialAdvisor) object;
                    result.put("id", lendersFinancialAdvisor.getBpCode().toString());
                    result.put("description", lendersFinancialAdvisor.getName());
                    return result;

                case "LFAReportAndFee":
                    LFAReportAndFee lfaReportAndFee = (LFAReportAndFee) object;
                    result.put("id", lfaReportAndFee.getLendersFinancialAdvisor().getBpCode().toString());
                    result.put("description", lfaReportAndFee.getReportType() + lfaReportAndFee.getDateOfReceipt());
                    return result;
                case "TrustRetentionAccount":
                    TrustRetentionAccount trustRetentionAccount = (TrustRetentionAccount) object;
                    result.put("id", trustRetentionAccount.getTypeOfAccount().toString());
                    result.put("description", trustRetentionAccount.getBankKey());
                    return result;
                case "TrustRetentionAccountStatement":
                    TrustRetentionAccountStatement trustRetentionAccountStatement = (TrustRetentionAccountStatement) object;
                    result.put("id", trustRetentionAccountStatement.getDocumentType().toString());
                    result.put("description", trustRetentionAccountStatement.getDocumentType());
                    return result;
                case "TermsAndConditionsModification":
                    TermsAndConditionsModification termsAndConditionsModification = (TermsAndConditionsModification) object;
                    result.put("id", termsAndConditionsModification.getCommunication().toString());
                    result.put("description", termsAndConditionsModification.getCommunication());
                    return result;
                case "SecurityCompliance":
                    SecurityCompliance securityCompliance = (SecurityCompliance) object;
                    result.put("id", securityCompliance.getCollateralObjectType().toString());
                    result.put("description", securityCompliance.getCollateralObjectType());
                    return result;
                case "SiteVisit":
                    SiteVisit siteVisit = (SiteVisit) object;
                    result.put("id", siteVisit.getSerialNumber().toString());
                    result.put("description", siteVisit.getSerialNumber().toString());
                    return result;
                case "OperatingParameter":
                    OperatingParameter operatingParameter = (OperatingParameter) object;
                    result.put("id", operatingParameter.getSerialNumber().toString());
                    result.put("description", operatingParameter.getSerialNumber().toString());
                    return result;
                case "RateOfInterest":
                    RateOfInterest rateOfInterest = (RateOfInterest) object;
                    result.put("id", rateOfInterest.getParticulars().toString());
                    result.put("description", rateOfInterest.getParticulars().toString());
                    return result;
                case "BorrowerFinancials":
                    BorrowerFinancials borrowerFinancials = (BorrowerFinancials) object;
                    result.put("id", borrowerFinancials.getFiscalYear().toString());
                    result.put("description", borrowerFinancials.getFiscalYear().toString());
                    return result;
                case "PromoterFinancials":
                    PromoterFinancials promoterFinancials = (PromoterFinancials) object;
                    result.put("id", promoterFinancials.getFiscalYear().toString());
                    result.put("description", promoterFinancials.getFiscalYear().toString());
                    return result;
                case "FinancialCovenants":
                    FinancialCovenants financialCovenants = (FinancialCovenants) object;
                    result.put("id", financialCovenants.getFinancialCovenantType().toString());
                    result.put("description", financialCovenants.getFinancialCovenantType().toString());
                    return result;

//                case "PromoterDetails":
//                    PromoterDetails promoterDetails  =   (PromoterDetails) object;
                     //TODO
//                    result.put("id", promoterDetails.getPromoterDetailsItemSet().toString());
//                    // result.put("description", promoterDetails.get().toString());
//                    return result;






            }

        } catch ( Exception ex) {

            //System.out.println(className);
        }
             return null;
    }


    private ChangeDocument prepareHeader(UUID objectId,
                                         String loanContractId,
                                         Object changedObject,
                                         String action,
                                         String userName,
                                         String businessProcessName,String subProcessName, String key1, String key2) {

        //ChangeDocument changeDocument = new ChangeDocument();
        changeDocument.setLoanBusinessProcessObjectId(objectId);
        changeDocument.setDate(new Date());

        LoanApplication loanApplication = loanApplicationRepository.findByLoanContractId(loanContractId);
        changeDocument.setLoanApplication(loanApplication);

        changeDocument.setLoanContractId(loanContractId);
        changeDocument.setAction(action);
        changeDocument.setBusinessProcessName(businessProcessName);
        changeDocument.setSubProcessName(subProcessName);
        changeDocument.setKey1(key1);
        changeDocument.setKey2(key2);
        changeDocument.setUserName(userName);

        return changeDocument;
    }


}
