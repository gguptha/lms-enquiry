package pfs.lms.enquiry.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.Partner;
import pfs.lms.enquiry.mail.domain.MailObject;
import pfs.lms.enquiry.mail.repository.MailObjectRepository;
import pfs.lms.enquiry.mail.resource.MailSearchResource;
import pfs.lms.enquiry.mail.service.EmailQueryService;
import pfs.lms.enquiry.mail.service.EmailService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 14-Sep-19.
 */
@Component
public class EmailQueryServiceImpl implements EmailQueryService {

    @Autowired
    MailObjectRepository mailObjectRepository;

    @Override
    public List<MailObject> searchMailObjectByResource(MailSearchResource searchResource) {

        List<MailObject> mailObjects = new ArrayList<>();
        String toAddress = null;
        LocalDateTime localDateTimeFrom = null;
        LocalDateTime localDateTimeTo = null;

        toAddress = searchResource.getToAddress();

        if (searchResource.getDateFrom() !=null){
            localDateTimeFrom = searchResource.getDateFrom().atStartOfDay();
        }

        if (searchResource.getDateTo() !=null){
            localDateTimeTo = searchResource.getDateTo().atTime(LocalTime.MAX);
        }


        if (localDateTimeFrom != null && localDateTimeTo == null)
            localDateTimeTo = searchResource.getDateFrom().atTime(LocalTime.MAX);

        if (toAddress != null && localDateTimeFrom != null && localDateTimeTo !=null) {
            mailObjects = mailObjectRepository.findByToAddressContainsAndDateTimeBetween(toAddress,localDateTimeFrom,localDateTimeTo);
        }

        if (toAddress == null && localDateTimeFrom != null && localDateTimeTo !=null) {
            mailObjects = mailObjectRepository.findByDateTimeBetween(localDateTimeFrom,localDateTimeTo);

        }

        if (toAddress != null && localDateTimeFrom == null && localDateTimeFrom == null) {
            mailObjects= mailObjectRepository.findByToAddressContains(toAddress);
        }

        return mailObjects;
    }

    @Override
    public List<MailObject> searchMailObject(String[] queryParams) {

        String toAddress = null;
        String dateFrom = null;
        String dateTo = null;

        Integer index = 0;

        List<MailObject> mailObjects = new ArrayList<>();
        LocalDateTime localDateTimeFrom = null;
        LocalDateTime localDateTimeTo = null;


        for (String parameter:queryParams) {

            switch (index){
                case 0:
                    if (parameter.length() > 0)
                        toAddress = parameter;
                    break;
                case 1:
                    if (parameter.length() > 0) {
                        dateFrom = parameter;
                        localDateTimeFrom = this.convertToLocalDateTimeFromDateString(dateFrom);
                    }
                    break;
                case 2:
                    if (parameter.length() > 0) {
                        dateTo = parameter;
                        localDateTimeTo = this.convertToLocalDateTimeFromDateString(dateTo);
                    }
                    break;
            }
            index++;
        }



        if (localDateTimeTo == null)
            localDateTimeTo = localDateTimeFrom;

        if (toAddress != null && dateFrom != null && dateTo !=null) {
            mailObjects = mailObjectRepository.findByToAddressContainsAndDateTimeBetween(toAddress,localDateTimeFrom,localDateTimeTo);
        }

        if (toAddress == null && dateFrom != null && dateTo !=null) {
            mailObjects = mailObjectRepository.findByDateTimeGreaterThanEqualAndDateTimeLessThanEqual(localDateTimeFrom,localDateTimeTo);
        }

        if (toAddress!= null) {
            mailObjects= mailObjectRepository.findByToAddressContains(toAddress);
        }

        return mailObjects;
    }


    private LocalDateTime convertToLocalDateTimeFromDateString(String date){
         date = date +  " 00:00:01";

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }
}
