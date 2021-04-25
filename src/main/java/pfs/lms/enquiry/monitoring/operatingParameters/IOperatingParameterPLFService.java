package pfs.lms.enquiry.monitoring.operatingParameters;

import java.util.List;

public interface IOperatingParameterPLFService {
    OperatingParameterPLF saveOperatingParameterPLF(OperatingParameterPLFResource resource, String username);
    OperatingParameterPLF updateOperatingParameterPLF(OperatingParameterPLFResource resource, String username) throws CloneNotSupportedException;
    List<OperatingParameterPLFResource> getOperatingParameterPLF(String loanApplicationId, String name);
}
