package pfs.lms.enquiry.service;

import pfs.lms.enquiry.monitoring.lie.LendersIndependentEngineer;
import pfs.lms.enquiry.monitoring.lie.LIEResource;

import java.util.List;

public interface ILIEService {
    LendersIndependentEngineer save(LIEResource resource, String username);
    LendersIndependentEngineer update(LIEResource resource, String username);

    List<LIEResource> getLendersIndependentEngineers(String loanApplicationId, String name);
}
