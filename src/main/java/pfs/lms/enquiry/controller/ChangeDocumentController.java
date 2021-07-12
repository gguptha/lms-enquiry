package pfs.lms.enquiry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.domain.ChangeDocument;
import pfs.lms.enquiry.service.changedocs.IChangeDocumentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class ChangeDocumentController {


    @Autowired
    private IChangeDocumentService changeDocumentService;

//    @Autowired
//    private HttpServletRequest request;


    @GetMapping("/changedocuments")
    public ResponseEntity getChangeDocuments(@RequestParam(value = "processName", required = true) String processName,
                                             @RequestParam(value = "loanContractId", required = false) String loanContractId,
                                             @RequestParam(value = "dateFrom", required = false) String dateFromString,
                                             @RequestParam(value = "dateTo", required = false) String dateToString,
                                             Pageable pageable) throws Exception {
        if (processName == null)
            processName = "Monitoring";

        if (dateToString == null) dateToString = dateFromString;

        if (loanContractId != null && dateFromString != null && dateToString != null)
            return ResponseEntity.ok(this.getChangeDocumentForBusinessProcessNameLoanContractIdDateRange(processName, loanContractId, dateFromString, dateToString, pageable));

        if (loanContractId == null && dateFromString == null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForProcessName(processName, pageable));
        }

        if (loanContractId != null && dateFromString != null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForBusinessProcessNameLoanContractIdDate(processName, loanContractId, dateFromString, pageable));
        }

        if (loanContractId != null && dateFromString == null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForProcessNameLoanContract(processName,loanContractId,pageable));
        }

        if (loanContractId == null && dateFromString != null && dateToString != null) {
            return ResponseEntity.ok(this.getChangeDocumentForProcessNameDateRange(processName, dateFromString, dateToString, pageable));
        }

        if (loanContractId == null && dateFromString == null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForProcessName(processName, pageable));
        }
        if (loanContractId == null && dateFromString != null && dateToString == null) {
            return ResponseEntity.ok(this.getChangeDocumentForProcessNameDate(processName, dateFromString, pageable));
        }

        return ResponseEntity.noContent().build();

    }


    private Page<ChangeDocument> getChangeDocumentForProcessName(String businessProcessName,
                                                                 Pageable pageable) throws ParseException {

        return changeDocumentService.findByBusinessProcessName(businessProcessName, pageable);
    }


    private Page<ChangeDocument> getChangeDocumentForProcessNameLoanContractIdDate(String businessProcessName,
                                                                                   String loanContractId,
                                                                                   String date,
                                                                                   Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn = format.parse(date);
        return changeDocumentService.findByBusinessProcessNameAndLoanContractIdAndDate(businessProcessName, loanContractId, dateOn, pageable);
    }


    private Page<ChangeDocument> getChangeDocumentForBusinessProcessNameLoanContractIdDate(String processName, String loanContractId,
                                                                                           String date,
                                                                                           Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn = format.parse(date);
        return changeDocumentService.findByBusinessProcessNameAndLoanContractIdAndDate(
                processName, loanContractId, dateOn, pageable);

    }

    private Page<ChangeDocument> getChangeDocumentForProcessNameLoanContract(String businessProcessName,
                                                                             String loanContractId,
                                                                             Pageable pageable) throws  ParseException
    {
        return changeDocumentService.findByBusinessProcessNameAndLoanContractId(businessProcessName,loanContractId,pageable);
    }
    private Page<ChangeDocument> getChangeDocumentForBusinessProcessNameLoanContractIdDateRange(String processName, String loanContractId,
                                                                                                String dateFromString,
                                                                                                String dateToString,
                                                                                                Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = format.parse(dateFromString);
        Date dateTo = format.parse(dateToString);
        dateTo = setTimeToLastSecondOfDay(dateTo);


        Page<ChangeDocument> changeDocuments = changeDocumentService.findByBusinessProcessNameAndLoanContractIdAndDateBetween(
                processName, loanContractId, dateFrom, dateTo, pageable);

        return changeDocuments;

    }


    private Page<ChangeDocument> getChangeDocumentForProcessNameDateRange(String processName,
                                                                          String dateFromString,
                                                                          String dateToString,
                                                                          Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = format.parse(dateFromString);
        Date dateTo = format.parse(dateToString);
        dateTo = setTimeToLastSecondOfDay(dateTo);

        Page<ChangeDocument> changeDocuments = changeDocumentService.findByBusinessProcessNameAndDateBetween(
                processName, dateFrom, dateTo, pageable);

        return changeDocuments;

    }

    private Page<ChangeDocument> getChangeDocumentForProcessNameDate(String processName,
                                                                     String dateFromString,
                                                                     Pageable pageable) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = format.parse(dateFromString);

        Page<ChangeDocument> changeDocuments = changeDocumentService.findByBusinessProcessNameAndDate(
                processName, dateFrom, pageable);

        return changeDocuments;

    }


    private Date setTimeToLastSecondOfDay(Date date) {

        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

}
