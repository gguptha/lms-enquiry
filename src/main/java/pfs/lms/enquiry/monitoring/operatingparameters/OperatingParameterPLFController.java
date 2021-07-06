package pfs.lms.enquiry.monitoring.operatingparameters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RepositoryRestController
@RequiredArgsConstructor
public class OperatingParameterPLFController {

    private final IOperatingParameterPLFService operatingParameterPLFService;

    @PostMapping("/loanApplications/operatingparameterplf/create")
    public ResponseEntity<OperatingParameterPLF> createOperatingParameterPLF(@RequestBody OperatingParameterPLFResource resource,
                                                                             HttpServletRequest request) {
        OperatingParameterPLF operatingParameterPLF = operatingParameterPLFService.
                saveOperatingParameterPLF(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(operatingParameterPLF);
    }

    @PutMapping("/loanApplications/operatingparameterplf/{id}")
    public ResponseEntity<OperatingParameterPLF> updateOperatingParameterPLF(@PathVariable("id") String operatingParameterPLFId,
                                 @RequestBody OperatingParameterPLFResource resource,
                                 HttpServletRequest request) throws CloneNotSupportedException {
        OperatingParameterPLF operatingParameterPLF =
                operatingParameterPLFService.updateOperatingParameterPLF(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(operatingParameterPLF);
    }

    @GetMapping("/loanApplications/{loanapplicationid}/operatingparameterplfs")
    public ResponseEntity<List<OperatingParameterPLFResource>> getOperatingParameterPLF(
            @PathVariable("loanapplicationid") String loanApplicationId, HttpServletRequest request)
    {
        List<OperatingParameterPLFResource> operatingParameterPLFs = operatingParameterPLFService.getOperatingParameterPLF(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(operatingParameterPLFs);
    }
}
