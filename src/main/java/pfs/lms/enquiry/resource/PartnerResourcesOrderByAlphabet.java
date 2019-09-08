package pfs.lms.enquiry.resource;

import pfs.lms.enquiry.domain.Partner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 07-Sep-19.
 */
public class PartnerResourcesOrderByAlphabet {


    List<PartnerResourceByAlphabet> partnersOrderedByAlphabet = new ArrayList<PartnerResourceByAlphabet>();


    public List<PartnerResourceByAlphabet> getPartnersOrderedByAlphabet() {
        return partnersOrderedByAlphabet;
    }

    public void setPartnersOrderedByAlphabet(List<PartnerResourceByAlphabet> partnersOrderedByAlphabet) {
        this.partnersOrderedByAlphabet = partnersOrderedByAlphabet;
    }

    public void addPartnerOrderByAlphabet(PartnerResourceByAlphabet partnerResourceByAlphabet){
        this.partnersOrderedByAlphabet.add(partnerResourceByAlphabet);
    }

}
