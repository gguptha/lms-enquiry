package pfs.lms.enquiry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pfs.lms.enquiry.domain.EnquiryNo;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.repository.LoanApplicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SapPfsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Test
	public void findByEnquiryNo() {
		EnquiryNo enquiryNo = new EnquiryNo();
		enquiryNo.setId(new Long(11));
		LoanApplication loanApplication = loanApplicationRepository.findByEnquiryNo(enquiryNo);
		System.out.println(loanApplication.getLoanEnquiryDate());
	}
}
