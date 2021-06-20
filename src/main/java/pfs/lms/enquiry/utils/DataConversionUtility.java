package pfs.lms.enquiry.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by sajeev on 14-Jun-21.
 */
public class DataConversionUtility {


    public  String convertDateToSAPFormat (LocalDate date) throws ParseException {

        String dateOut  = new String();


        if (date != null) {
            dateOut =date.toString();
            dateOut = dateOut + " 01:01:01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datedParsed = sdf.parse(dateOut);
            long millis = datedParsed.getTime();
            dateOut = "\\/Date(" + millis + ")\\/";
            return dateOut;
        }
        else {
            return null;
        }
    }
}
