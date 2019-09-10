package pfs.lms.enquiry.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 07-Sep-19.
 */
public class PartnerResourceByAlphabet {

    String letter;
    List<String> names = new ArrayList<String>();

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
