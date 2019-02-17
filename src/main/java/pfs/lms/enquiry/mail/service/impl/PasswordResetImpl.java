package pfs.lms.enquiry.mail.service.impl;

//  **
// * Created by sajeev on 16-Feb-19.
// */

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.User;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.service.EmailService;
import pfs.lms.enquiry.mail.service.PasswordReset;

import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

@Component
public class PasswordResetImpl implements PasswordReset {



    @Autowired
    EmailService emailService;


    public String generatePassayPassword() {

        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }


            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
        return password;
    }

    @Override
    public String sendMailWithNewPassword(String emailId, String fName, String lName) {

        String newPassword = generatePassayPassword();


        String line1 = "Dear" + " " + fName + " " + lName + System.lineSeparator();
        String line2 = "Your password for PTC Financial Services is reset." + System.lineSeparator();
        String line3 = "The new password is as follows:" + System.lineSeparator();
        String line4 = newPassword + System.lineSeparator();
        String line5 = "Regards, PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Portal");
        mailObject.setAppObjectId(" ");
        mailObject.setToAddress(emailId);
        mailObject.setSubject("Your password for PTC Financial Services is reset");
        mailObject.setMailContent(content);

        emailService.sendEmailMessage(mailObject);

        return newPassword;
    }




}
