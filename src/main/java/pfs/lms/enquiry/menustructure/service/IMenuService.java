package pfs.lms.enquiry.menustructure.service;

import pfs.lms.enquiry.menustructure.domain.Menu;

/**
 * Created by sajeev on 14-May-21.
 */
public interface IMenuService {

    public Menu createMenu(Menu menu);
    public Menu updateMenu(Menu menu);
    public Menu findByUserRole(String userRole);
}
