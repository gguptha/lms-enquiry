package pfs.lms.enquiry.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/").setViewName("index.html");
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                //Configure HTTP Basic Authentication
                .httpBasic()
                //Disable CSRF and FrameOptions for H2
                .and().csrf().disable().headers().frameOptions().disable()
                //Disable Basic Authentication for OPTIONS
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //Added headers for CORS
                .and().headers().addHeaderWriter((request, response) -> {
                    response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
                    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
                    response.setHeader("Access-Control-Max-Age", "3600");
                    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                });

            log.info("CORS settings for /api/**");
        }
    }
}