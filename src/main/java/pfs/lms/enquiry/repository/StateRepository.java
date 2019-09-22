package pfs.lms.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfs.lms.enquiry.domain.State;

import java.util.UUID;

/**
 * Created by sajeev on 22-Sep-19.
 */
public interface StateRepository extends JpaRepository<State,UUID> {

    public State findByCode(String code);
}
