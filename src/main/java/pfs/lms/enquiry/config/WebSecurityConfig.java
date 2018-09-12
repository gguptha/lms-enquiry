package pfs.lms.enquiry.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
 * @author : fahadfazil
 * @since : 22/12/17
*/

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        /**
         * Form Login
         */

//        http
//
//                //Configuring Form Login
//                .formLogin().loginPage("/login").failureHandler(new CustomAuthenticationFailureHandler()).permitAll()
//
//                //Configuring Logout
//                .and().logout().logoutUrl("/logout").permitAll()
//
//                //ui exemption
//                .and().authorizeRequests().antMatchers("/js/**", "/navigation/**", "/css/**", "/images/**", "/template/**", "/webjars/**","/assets/**","/assets/**/**").permitAll()
//
//                //for dispenser apis , login , device endpoints
//                // /announcements/** for signage announcements
//                .and().authorizeRequests().antMatchers("/login/**","/api/dispenser/**","/api/signage/**", "/api/devices/**","/push/**","/api/time", "/assets/**","/api/signage/playlist", "/api/download/**","/api/config","/api/config/**", "/announcements/**").permitAll()
//
//                //Enable authentication for the rest of the end points
//                .and().authorizeRequests().anyRequest().authenticated()
//
//                //disable csrf and frame options for H2
//                .and().csrf().disable().headers().frameOptions().disable();
//
//        log.info("Form Authentication Configured for /**");


        /**
         * Basic Auth
         */

        http
                //Configure HTTP Basic Authentication
                .httpBasic()

                //Exemptions
                .and().antMatcher("/api/**").authorizeRequests().antMatchers("/api/time").permitAll()


                //Configure the path for Basic Authentication
                .and().authorizeRequests().anyRequest().authenticated()

                //Disable CSRF and FrameOptions for H2
                .and().csrf().disable().headers().frameOptions().disable()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //Disable Basic Authentication for OPTIONS
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                //Added headers for CORS
                .and().headers().addHeaderWriter((request, response) -> {
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        });


    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails admin =
             User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin,user);
    }
}
