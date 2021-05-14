package pfs.lms.enquiry.menustructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import pfs.lms.enquiry.menustructure.domain.Menu;
import pfs.lms.enquiry.menustructure.domain.MenuHeader;

/**
 * Created by sajeev on 14-May-21.
 */
@Repository
@RepositoryRestResource
public interface MenuHeaderRepository extends JpaRepository<MenuHeader, Integer>{

 }
