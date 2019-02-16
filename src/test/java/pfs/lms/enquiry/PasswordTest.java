package pfs.lms.enquiry;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pfs.lms.enquiry.domain.EnquiryNo;
import pfs.lms.enquiry.domain.LoanApplication;
import pfs.lms.enquiry.mail.service.PasswordReset;
import pfs.lms.enquiry.repository.LoanApplicationRepository;
import pfs.lms.enquiry.repository.PartnerRepository;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PasswordTest {

	@Test
	public void contextLoads() {
	}

	@Autowired
	PasswordReset passwordReset;

	@Test
	public void whenPasswordGeneratedUsingPassay_thenSuccessful() {

		//passwordReset passGen = new RandomPasswordGenerator();

		String password = passwordReset.generatePassayPassword();
		System.out.println("Password --------------- :" + password);
		int specialCharCount = 0;
		for (char c : password.toCharArray()) {
			if (c >= 33 || c <= 47) {
				specialCharCount++;
			}
		}
		assertTrue("Password validation failed in Passay", specialCharCount >= 2);
	}
}
