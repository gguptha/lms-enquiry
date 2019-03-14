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

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {

        if(loanClassRepository.count() == 0) {
            LoanClass lc1 = new LoanClass("001", "Power");
            LoanClass lc2 = new LoanClass("002", "Railways");
            LoanClass lc3 = new LoanClass("003", "Urban Infrastructure");
            LoanClass lc4 = new LoanClass("004", "Roads");
            LoanClass lc5 = new LoanClass("005", "Ports");
            LoanClass lc6 = new LoanClass("006", "Oil & Gas");
            LoanClass lc7 = new LoanClass("007", "Corporates");
            LoanClass lc8 = new LoanClass("008", "Infrastructure");
            LoanClass lc9 = new LoanClass("009", "Others");
            loanClassRepository.saveAll(Arrays.asList(lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8, lc9));
            log.info("Added loan class sample data");
        }

        if(projectTypeRepository.count() == 0) {
            ProjectType pt1 = new ProjectType("01", "Thermal - Coal");
            ProjectType pt2 = new ProjectType("02", "Thermal - Ignite");
            ProjectType pt3 = new ProjectType("03", "Thermal - Gas");
            ProjectType pt4 = new ProjectType("04", "Hydro");
            ProjectType pt5 = new ProjectType("05", "Renewable - Solar");
            ProjectType pt6 = new ProjectType("06", "Renewable - Wind");
            ProjectType pt7 = new ProjectType("07", "Renewable - Biomass");
            ProjectType pt8 = new ProjectType("08", "Renewable - Small Hydro");
            ProjectType pt9 = new ProjectType("09", "EPC Contractors");
            ProjectType pt10 = new ProjectType("10", "Coal Mining");
            ProjectType pt11 = new ProjectType("11", "Power Transmission");
            ProjectType pt12 = new ProjectType("12", "Railway Siding");
            ProjectType pt13 = new ProjectType("13", "Ports");
            ProjectType pt14 = new ProjectType("14", "Corporate");
            ProjectType pt15 = new ProjectType("15", "Renovation & Modernisation");
            ProjectType pt16 = new ProjectType("16", "Others");
            projectTypeRepository.saveAll(Arrays.asList(pt1, pt2, pt3, pt4, pt5, pt6, pt7, pt8, pt9, pt10, pt11, pt12,
                    pt13, pt14, pt15, pt16));
            log.info("Added project type sample data");
        }

        if(financingTypeRepository.count() == 0) {
            FinancingType ft1 = new FinancingType("01", "Sole Lending");
            FinancingType ft2 = new FinancingType("02", "Consortium Lending");
            FinancingType ft3 = new FinancingType("03", "Lead FI");
            FinancingType ft4 = new FinancingType("04", "Underwriting");
            FinancingType ft5 = new FinancingType("05", "Lead FI & Synd.");
            FinancingType ft6 = new FinancingType("06", "Syndication");
            financingTypeRepository.saveAll(Arrays.asList(ft1, ft2, ft3, ft4, ft5, ft6));
            log.info("Added financing type sample data");
        }

        if(assistanceTypeRepository.count() == 0) {
            AssistanceType at1 = new AssistanceType("D", "Debt");
            AssistanceType at2 = new AssistanceType("E", "Equity");
            assistanceTypeRepository.saveAll(Arrays.asList(at1, at2));
            log.info("Added assistance type sample data");
        }

        if (userRepository.count() == 0) {
            User user1 = new User("Admin", "- PFS", "admin@gmail.com", "ZLM023", true, "admin@gmail.com", "50000284", "02", false);
            userRepository.save(user1);

//            User user2 = new User("Loan", "Officer - 1", "lo1@gmail.com", "ZLM013", true, "lo1@gmail.com", "50000284", "01");
//            userRepository.save(user2);
//
//            User user3 = new User("Gopinath", "B R", "gopinath.br@gmail.com", "TR0100", true, "gopinath.br@gmail.com", "", "03");
//            userRepository.save(user3);
        }

        if (productRepository.count() == 0) {
            Product p1 = new Product("301", "Bridge Loan");
            Product p2 = new Product("301", "Short Term Loan");
            Product p3 = new Product("303", "Term Loan");
            Product p4 = new Product("304", "Debentures");
            Product p5 = new Product("305", "Non Fund Based Loan");
            Product p6 = new Product("30F", "Facilities");
            Product p7 = new Product("310", "Facilities Drawdown-NFB Loan");
            Product p8 = new Product("311", "Facilities Drawdown-Term Loan");
            Product p9 = new Product("991", "Short Term Loan for Vehicle");
            productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
            log.info("Added products sample data");
        }
    }
}
