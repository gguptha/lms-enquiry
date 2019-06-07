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
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.service.EmailService;
import pfs.lms.enquiry.mail.service.PasswordResetService;
import pfs.lms.enquiry.mail.service.SendEmail;

import java.util.concurrent.CompletableFuture;

import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

@Component
public class PasswordResetServiceImpl implements PasswordResetService {



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
        String line2 = "    Your password for PTC Financial Services is reset." + System.lineSeparator();
        String line3 = "    The new password is as follows:" + System.lineSeparator() ;
        String line4 = "" ; //System.lineSeparator();
        String line5 = "    " + newPassword + System.lineSeparator();
        String line6 = "    " + System.lineSeparator();
        String line7 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5 + line6 + line7;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Portal");
        mailObject.setAppObjectId(" ");
        mailObject.setToAddress(emailId);
        mailObject.setSubject("Your password for PTC Financial Services is reset");
        mailObject.setMailContent(content);

        //emailService.sendEmailMessage(mailObject);

//        new Thread(() -> {
//            // code goes here.
//            emailService.sendEmailMessage(mailObject);
//        }).start();
//

        CompletableFuture.runAsync(() -> {
            // method call or code to be asynch.
            emailService.sendEmailMessage(mailObject);

        });

        return newPassword;
    }


    @Override
    public String sendPasswordChangeNotificationMail(String emailId, String fName, String lName) {

        String line1 = "Dear" + " " + fName + " " + lName + System.lineSeparator();
        String line2 = "    Your password for PTC Financial Services Portal has been changed." + System.lineSeparator();
        String line3 = "    In case this is unintentional, please contact PTC Financial Services immediately" + System.lineSeparator() ;
        String line4 = "    " + System.lineSeparator();
        String line5 = "Regards," + System.lineSeparator() + "PTC Financial Services";
        String content = line1 + line2 + line3 + line4 + line5 ;

        MailObject mailObject = new MailObject();
        mailObject.setSendingApp("PFS Portal");
        mailObject.setAppObjectId(" ");
        mailObject.setToAddress(emailId);
        mailObject.setSubject("Your password for PTC Financial Services has been changed");
        mailObject.setMailContent(content);



        //mailObject = emailService.sendEmailMessage(mailObject);


        CompletableFuture.runAsync(() -> {
            // method call or code to be asynch.
            emailService.sendEmailMessage(mailObject);

        });

        return null; //mailObject.getId().toString();
    }




}
