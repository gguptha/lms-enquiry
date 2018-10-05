package pfs.lms.enquiry.sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pfs.lms.enquiry.domain.*;
import pfs.lms.enquiry.repository.*;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final LoanClassRepository loanClassRepository;

    private final ProjectTypeRepository projectTypeRepository;

    private final FinancingTypeRepository financingTypeRepository;

    private final AssistanceTypeRepository assistanceTypeRepository;

    private final PartnerRepository partnerRepository;

    @Override
    public void run(String... strings) throws Exception {

        if(loanClassRepository.count() == 0) {
            LoanClass lc1 = new LoanClass(1, "Power");
            LoanClass lc2 = new LoanClass(1, "Railways");
            LoanClass lc3 = new LoanClass(3, "Urban Infrastructure");
            LoanClass lc4 = new LoanClass(4, "Roads");
            LoanClass lc5 = new LoanClass(5, "Ports");
            LoanClass lc6 = new LoanClass(6, "Oil & Gas");
            LoanClass lc7 = new LoanClass(7, "Corporates");
            LoanClass lc8 = new LoanClass(8, "Infrastructure");
            LoanClass lc9 = new LoanClass(9, "Others");
            loanClassRepository.saveAll(Arrays.asList(lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8, lc9));
            log.info("Added loan class sample data");
        }

        if(projectTypeRepository.count() == 0) {
            ProjectType pt1 = new ProjectType(1, "Thermal - Coal");
            ProjectType pt2 = new ProjectType(2, "Thermal - Ignite");
            ProjectType pt3 = new ProjectType(3, "Thermal - Gas");
            ProjectType pt4 = new ProjectType(4, "Hydro");
            ProjectType pt5 = new ProjectType(5, "Renewable - Solar");
            ProjectType pt6 = new ProjectType(6, "Renewable - Wind");
            ProjectType pt7 = new ProjectType(7, "Renewable - Biomass");
            ProjectType pt8 = new ProjectType(8, "Renewable - Small Hydro");
            ProjectType pt9 = new ProjectType(9, "EPC Contractors");
            ProjectType pt10 = new ProjectType(10, "Coal Mining");
            ProjectType pt11 = new ProjectType(11, "Power Transmission");
            ProjectType pt12 = new ProjectType(12, "Railway Siding");
            ProjectType pt13 = new ProjectType(13, "Ports");
            ProjectType pt14 = new ProjectType(14, "Corporate");
            ProjectType pt15 = new ProjectType(15, "Renovation & Modernisation");
            ProjectType pt16 = new ProjectType(16, "Others");
            projectTypeRepository.saveAll(Arrays.asList(pt1, pt2, pt3, pt4, pt5, pt6, pt7, pt8, pt9, pt10, pt11, pt12,
                    pt13, pt14, pt15, pt16));
            log.info("Added project type sample data");
        }

        if(financingTypeRepository.count() == 0) {
            FinancingType ft1 = new FinancingType(1, "Sole Lending");
            FinancingType ft2 = new FinancingType(2, "Consortium Lending");
            FinancingType ft3 = new FinancingType(3, "Lead FI");
            FinancingType ft4 = new FinancingType(4, "Underwriting");
            FinancingType ft5 = new FinancingType(5, "Lead FI & Synd.");
            FinancingType ft6 = new FinancingType(6, "Syndication");
            financingTypeRepository.saveAll(Arrays.asList(ft1, ft2, ft3, ft4, ft5, ft6));
            log.info("Added financing type sample data");
        }

        if(assistanceTypeRepository.count() == 0) {
            AssistanceType at1 = new AssistanceType("D", "Debt");
            AssistanceType at2 = new AssistanceType("E", "Equity");
            assistanceTypeRepository.saveAll(Arrays.asList(at1, at2));
            log.info("Added assistance type sample data");
        }

        if (partnerRepository.count() == 0) {
            Partner partner = new Partner("admin","admin");
            partner.setPartyRole("ZLM023");
            partnerRepository.save(partner);
        }
    }
}
