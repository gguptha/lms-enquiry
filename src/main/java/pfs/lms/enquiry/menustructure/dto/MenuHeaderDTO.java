package pfs.lms.enquiry.menustructure.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 14-May-21.
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MenuHeaderDTO {

    private String id;

    private String title;

    private String translate;

    private String type;

    private String icon;

    private List<ChildrenDTO> children;


    public void addChildren (ChildrenDTO childrenDTO) {
        if (this.getChildren() == null) {
            List<ChildrenDTO> childrenDTOS = new ArrayList<>();
            this.setChildren(childrenDTOS);
            this.getChildren().add(childrenDTO);
        } else {
            this.getChildren().add(childrenDTO);
        }
    }

}
