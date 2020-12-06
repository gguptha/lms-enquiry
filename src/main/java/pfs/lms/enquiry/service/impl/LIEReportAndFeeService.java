package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.domain.LIEReportAndFee;
import pfs.lms.enquiry.domain.LendersIndependentEngineer;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.domain.LoanMonitor;
import pfs.lms.enquiry.repository.LIEReportAndFeeRepository;
import pfs.lms.enquiry.repository.LIERepository;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.LoanMonitorRepository;
import pfs.lms.enquiry.resource.LIEReportAndFeeResource;
import pfs.lms.enquiry.resource.LIEResource;
import pfs.lms.enquiry.service.ILIEReportAndFeeService;
import pfs.lms.enquiry.service.ILIEService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LIEReportAndFeeService implements ILIEReportAndFeeService{

    private final LIERepository lieRepository;
    private final LIEReportAndFeeRepository lieReportAndFeeRepository;
    private  final LoanMonitorRepository loanMonitorRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    @Transactional
    public LIEReportAndFee save(LIEReportAndFeeResource resource, String username) {


        LendersIndependentEngineer lendersIndependentEngineer = lieRepository.getOne(resource.getLendersIndependentEngineerId());
        LIEReportAndFee lieReportAndFee = resource.getLieReportAndFee();
        lieReportAndFee.setLendersIndependentEngineer(lendersIndependentEngineer);
        lieReportAndFee = lieReportAndFeeRepository.save(lieReportAndFee);
        return lieReportAndFee;
    }

    @Override
    public LIEReportAndFee update(LIEReportAndFeeResource resource, String username) {

        LIEReportAndFee existinglieReportAndFee
                = lieReportAndFeeRepository.getOne(UUID.fromString(resource.getLieReportAndFee().getId()));

        existinglieReportAndFee.setReportType(resource.getLieReportAndFee().getReportType());
        existinglieReportAndFee.setDateOfReceipt(resource.getLieReportAndFee().getDateOfReceipt());
        existinglieReportAndFee.setInvoiceDate(resource.getLieReportAndFee().getInvoiceDate());
        existinglieReportAndFee.setInvoiceNo(resource.getLieReportAndFee().getInvoiceNo());
        existinglieReportAndFee.setFeeAmount(resource.getLieReportAndFee().getFeeAmount());
        existinglieReportAndFee.setStatusOfFeeReceipt(resource.getLieReportAndFee().getStatusOfFeeReceipt());
        existinglieReportAndFee.setStatusOfFeePaid(resource.getLieReportAndFee().getStatusOfFeePaid());
        existinglieReportAndFee.setDocumentTitle(resource.getLieReportAndFee().getDocumentTitle());
        existinglieReportAndFee.setDocumentContent(resource.getLieReportAndFee().getDocumentContent());
        existinglieReportAndFee.setNextReportDate(resource.getLieReportAndFee().getNextReportDate());
        existinglieReportAndFee = lieReportAndFeeRepository.save(existinglieReportAndFee);

        return existinglieReportAndFee;
    }

    @Override
    public List<LIEReportAndFeeResource> getLIEReportAndFee(String llendersIndependentEngineerId, String name) {

        List<LIEReportAndFeeResource>  lieReportAndFeeResources  = new ArrayList<>();
        LendersIndependentEngineer lendersIndependentEngineer = lieRepository.getOne(UUID.fromString(llendersIndependentEngineerId));
        //LoanMonitor loanMonitor = loanMonitorRepository.findByLoanApplication(loanApplication);
        if(lendersIndependentEngineer != null) {
            List<LIEReportAndFee> lieReportAndFees
                    = lieReportAndFeeRepository.findByLendersIndependentEngineer(lendersIndependentEngineer);
            lieReportAndFees.forEach(
                    lieReportAndFee -> {
                        LIEReportAndFeeResource lieReportAndFeeResource = new LIEReportAndFeeResource();
                        //lieResource.setLoanApplicationId(loanApplication.getId());
                        lieReportAndFeeResource.setLendersIndependentEngineerId(UUID.fromString(lendersIndependentEngineer.getId()));
                        lieReportAndFeeResource.setLieReportAndFee(lieReportAndFee);
                        lieReportAndFeeResources.add(lieReportAndFeeResource);
                    }
            );
        }
        return lieReportAndFeeResources;
    }
}
