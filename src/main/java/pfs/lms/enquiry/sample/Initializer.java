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

    private final IndustrySectorRepository industrySectorRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final UserRoleRepository userRoleRepository;

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

        if (industrySectorRepository.count() == 0){
             IndustrySector i2 = new IndustrySector( "1" , "Power");
            IndustrySector i3 = new IndustrySector( "2" , "Railways");
            IndustrySector i4 = new IndustrySector( "3" , "Urban Infra");
            IndustrySector i5 = new IndustrySector( "4" , "Roads");
            IndustrySector i6 = new IndustrySector( "5" , "Ports");
            IndustrySector i7 = new IndustrySector( "6" , "Oil & Gas");
            IndustrySector i8 = new IndustrySector( "7" , "Corporates");
            IndustrySector i9 = new IndustrySector( "8" , "Infrastructure");
            IndustrySector i10 = new IndustrySector( "9" , "Others");
            IndustrySector i11 = new IndustrySector( "10" , "Energy Supply / Distribution");
            IndustrySector i12 = new IndustrySector( "11" , "Div. Holding comp");
            IndustrySector i13 = new IndustrySector( "12" , "Raw Materials");
            IndustrySector i14 = new IndustrySector( "13" , "Precious Metals");
            IndustrySector i15 = new IndustrySector( "14" , "Financial Services");
            IndustrySector i16 = new IndustrySector( "15" , "Real Estate");
            IndustrySector i17 = new IndustrySector( "21" , "Chemical Industry");
            IndustrySector i18 = new IndustrySector( "22" , "Health");
            IndustrySector i19 = new IndustrySector( "23" , "Glass");
            IndustrySector i20 = new IndustrySector( "24" , "Construction Industry");
            IndustrySector i21 = new IndustrySector( "25" , "Building Supplier");
            IndustrySector i22 = new IndustrySector( "26" , "Paper and Pulp");
            IndustrySector i23 = new IndustrySector( "27" , "Timber and Infrastructure");
            IndustrySector i24 = new IndustrySector( "31" , "Spinning Mill, Weaving Mill and Textile Refinement");
            IndustrySector i25 = new IndustrySector( "32" , "Apparel");
            IndustrySector i26 = new IndustrySector( "41" , "Iron and Steel");
            IndustrySector i27 = new IndustrySector( "42" , "Vehicles");
            IndustrySector i28 = new IndustrySector( "43" , "Vehicle Supplier");
            IndustrySector i29 = new IndustrySector( "44" , "Mechanical Engineering");
            IndustrySector i30 = new IndustrySector( "45" , "Specialized Mechanical Engineering");
            IndustrySector i31 = new IndustrySector( "46" , "Machine Tool Engineering");
            IndustrySector i32 = new IndustrySector( "47" , "Aircraft Construction");
            IndustrySector i33 = new IndustrySector( "51" , "Breweries, Beverages, Tobacco");
            IndustrySector i34 = new IndustrySector( "52" , "Nutrition");
            IndustrySector i35 = new IndustrySector( "61" , "Electricals / Electrical Engineering");
            IndustrySector i36 = new IndustrySector( "62" , "Computers and Data Processing");
            IndustrySector i37 = new IndustrySector( "63" , "Software");
            IndustrySector i38 = new IndustrySector( "64" , "Telecommunications");
            IndustrySector i39 = new IndustrySector( "71" , "Consumer Products");
            IndustrySector i40 = new IndustrySector( "72" , "Traffic and Transport");
            IndustrySector i41 = new IndustrySector( "73" , "Leisure and Hotel");
            IndustrySector i42 = new IndustrySector( "81" , "Commercial Banks");
            IndustrySector i43 = new IndustrySector( "82" , "Mortgage Banks");
            IndustrySector i44 = new IndustrySector( "83" , "Life Insurances");
            IndustrySector i45 = new IndustrySector( "84" , "Non-Life Insurances");
            IndustrySector i46 = new IndustrySector( "85" , "Reinsurances");
            IndustrySector i47 = new IndustrySector( "86" , "Insurance Holdings");
            IndustrySector i48 = new IndustrySector( "91" , "Trading");
            IndustrySector i49 = new IndustrySector( "92" , "Pharmaceutical Trade");
            IndustrySector i50 = new IndustrySector( "93" , "Publishing and Media");
            IndustrySector i51 = new IndustrySector( "94" , "Environment");



            industrySectorRepository.saveAll(Arrays.asList( i2,i3,i4,i5,i6,i7,i8,i9,i10));
            industrySectorRepository.saveAll(Arrays.asList(i11,i12,i13,i14,i15,i16,i17,i18,i19,i20));
            industrySectorRepository.saveAll(Arrays.asList(i21,i22,i23,i24,i25,i26,i27,i28,i29,i30));
            industrySectorRepository.saveAll(Arrays.asList(i31,i32,i33,i34,i35,i36,i37,i38,i39,i40));
            industrySectorRepository.saveAll(Arrays.asList(i41,i42,i43,i44,i45,i46,i47,i48,i49,i50,i51));
            log.info("Added Industry Sectors data");



        }

        if (unitOfMeasureRepository.count() == 0) {

            UnitOfMeasure u1 = new UnitOfMeasure("MW", "Mega Watts");
            UnitOfMeasure u2 = new UnitOfMeasure("KW", "Kilo Watts");
            UnitOfMeasure u3 = new UnitOfMeasure("KM", "Kilometers");
            UnitOfMeasure u4 = new UnitOfMeasure("KM2", "Square Kilometers");


            unitOfMeasureRepository.saveAll(Arrays.asList(u1, u2,u3,u4));
            log.info("Added Unit of Measures");
        }

        if (userRoleRepository.count() == 0){
            UserRole r1 = new UserRole("TR0100", "Loan Applicant");
            UserRole r2 = new UserRole("ZLM013", "Appraisal Officer");
            UserRole r3 = new UserRole("ZLM023", "Administrator");
            UserRole r4 = new UserRole("ZLM001", "Promoter");
            UserRole r5 = new UserRole("ZLM023", "Co-Appraisal Officer");
            UserRole r6 = new UserRole("ZLM023", "Appraisal Officer");
            UserRole r7 = new UserRole("TR0110", "Prospect");


            userRoleRepository.saveAll(Arrays.asList(r1,r2,r3));
            log.info("Added User Roles");
        }

    }
}
