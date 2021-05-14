package pfs.lms.enquiry.menustructure.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sajeev on 14-May-21.
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChildrenDTO {

    private String id;


    private String title;

    private String translate;

    private String type;

    private String icon;

    private String url;
}
