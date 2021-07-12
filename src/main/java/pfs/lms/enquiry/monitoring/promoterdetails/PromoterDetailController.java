package pfs.lms.enquiry.monitoring.promoterdetails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RepositoryRestController
@AllArgsConstructor
public class PromoterDetailController {

    private final IPromoterDetailService promoterDetailService;

    @PostMapping("/promoterDetails")
    public ResponseEntity<PromoterDetail> createPromoterDetails(@RequestBody PromoterDetailResource resource,
                                                                HttpServletRequest request) {
        PromoterDetail promoterDetail =
                promoterDetailService.savePromoterDetails(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(promoterDetail);
    }

    @PutMapping("/promoterDetails/{id}")
    public ResponseEntity<PromoterDetail> updatePromoterDetails(@PathVariable("id") String promoterDetailsId,
                                                                @RequestBody PromoterDetailResource resource, HttpServletRequest request)
            throws CloneNotSupportedException {

        PromoterDetail promoterDetail =
                promoterDetailService.updatePromoterDetails(resource, request.getUserPrincipal().getName());
        return ResponseEntity.ok(promoterDetail);
    }

    @GetMapping("/promoterDetails/loanApplication/{loanapplicationid}")
    public ResponseEntity<List<PromoterDetailResource>> getPromoterDetails(
            @PathVariable("loanapplicationid") String loanApplicationId, HttpServletRequest request) {

        List<PromoterDetailResource> promoterDetails = promoterDetailService.getPromoterDetails(loanApplicationId,
                request.getUserPrincipal().getName());
        return ResponseEntity.ok(promoterDetails);
    }
}
