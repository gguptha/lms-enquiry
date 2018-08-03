package pfs.lms.enquiry.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author : fahadfazil
 * @since : 22/12/17
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                //Configuring Form Login
                .formLogin().loginPage("/login").failureHandler(new CustomAuthenticationFailureHandler()).permitAll()

                //Configuring Logout
                .and().logout().logoutUrl("/logout").permitAll()

                //ui exemption
                .and().authorizeRequests().antMatchers("/js/**", "/navigation/**", "/css/**", "/images/**", "/template/**", "/webjars/**","/assets/**","/assets/**/**").permitAll()

                //for dispenser apis , login , device endpoints
                // /announcements/** for signage announcements
                .and().authorizeRequests().antMatchers("/login/**").permitAll()

                //Enable authentication for the rest of the end points
                .and().authorizeRequests().anyRequest().authenticated()

                //disable csrf and frame options for H2
                .and().csrf().disable().headers().frameOptions().disable();

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