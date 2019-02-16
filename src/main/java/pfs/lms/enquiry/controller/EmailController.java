package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.service.EmailService;

/**
 * Created by sajeev on 16-Feb-19.
 */
@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    EmailService emailService;


//    public String createMail(//Model model,
//                             @ModelAttribute("mailObject") @Valid MailObject mailObject) {
//


    @RequestMapping(value = "/api/mail", method = {RequestMethod.GET})
    public ResponseEntity<MailObject> createMail(MailObject mailObject) {

        System.out.println("------------ MAIL OBJECT INP : " +mailObject);
        mailObject = emailService.sendEmailMessage(mailObject);

        return ResponseEntity.ok(mailObject);
        //return "redirect:/home";
    }

}
