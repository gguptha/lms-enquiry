package pfs.lms.enquiry.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;
import pfs.lms.enquiry.resource.LoanApplicationResource;
import pfs.lms.enquiry.resource.SAPLoanApplicationDetailsResource;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 11-Aug-19.
 */
@Service
@Transactional
public class LoanApplicationsScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(LoanApplicationsScheduledTask.class);

    @Autowired
    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private  final ISAPIntegrationService isapIntegrationService;

    @Autowired
    private final PartnerRepository partnerRepository;



    public LoanApplicationsScheduledTask(LoanApplicationRepository loanApplicationRepository, ISAPIntegrationService isapIntegrationService, PartnerRepository partnerRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.isapIntegrationService = isapIntegrationService;
        this.partnerRepository = partnerRepository;
    }

    //@Scheduled(fixedRate = 500000000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        System.out.println("The time is now :" + dateFormat.format(new Date()));

        List<LoanApplication> loanApplicationList = loanApplicationRepository.findByTechnicalStatusAndPostedInSAP(4,false);
        for (LoanApplication loanApplication: loanApplicationList) {

            SAPLoanApplicationResource sapLoanApplicationResource = new SAPLoanApplicationResource();

            LoanApplicationResource loanApplicationResource = new LoanApplicationResource();

            Partner partner = partnerRepository.getOne(loanApplication.getLoanApplicant());
            loanApplicationResource.setPartner(partner);
            loanApplicationResource.setLoanApplication(loanApplication);

            SAPLoanApplicationDetailsResource detailsResource= sapLoanApplicationResource.mapLoanApplicationToSAP(loanApplication,partner);


            SAPLoanApplicationResource d = new SAPLoanApplicationResource();
            d.setSapLoanApplicationDetailsResource(detailsResource);

            sapLoanApplicationResource =   isapIntegrationService.postLoanApplication(d);

            if (sapLoanApplicationResource != null) {
                loanApplication.responseFromSAP(sapLoanApplicationResource);

                //Set PostedInSAP to True
                loanApplication.setPostedInSAP(true);

                loanApplication = loanApplicationRepository.save(loanApplication);
                System.out.println("Loan Contract Id in SAP: " + loanApplication.getLoanContractId());
            }
        }

    }





}
