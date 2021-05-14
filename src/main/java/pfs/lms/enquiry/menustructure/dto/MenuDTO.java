package pfs.lms.enquiry.menustructure.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pfs.lms.enquiry.menustructure.domain.MenuHeader;
import pfs.lms.enquiry.menustructure.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 14-May-21.
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MenuDTO {

    private
    List<MenuHeaderDTO> menuHeaderDTOS;

    public void addMenuHeader (MenuHeaderDTO menuHeader) {
        if (this.getMenuHeaderDTOS() == null) {
            List<MenuHeaderDTO> menuHeaderDTOS = new ArrayList<>();
            this.setMenuHeaderDTOS(menuHeaderDTOS);
            this.getMenuHeaderDTOS().add(menuHeader);
        } else {
            this.getMenuHeaderDTOS().add(menuHeader);
        }
    }


 }
