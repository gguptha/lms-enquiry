package pfs.lms.enquiry.menustructure.dao;

import org.springframework.data.repository.CrudRepository;
import pfs.lms.enquiry.menustructure.domain.Menu;

/**
 * Created by sajeev on 14-May-21.
 */
public interface MenuDao extends CrudRepository<Menu, Integer> {
}
