package pfs.lms.enquiry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author : Fahad Fazil
 * @since : 16/01/18
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry().addMapping("/**").allowedOrigins("http://localhost:4200").allowedHeaders("x-requested-with","authorization","content-Type").allowedMethods("GET, POST, PUT, DELETE, OPTIONS, PATCH");
    }
}