package pfs.lms.enquiry.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sajeev on 20-Jun-19.
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

//    mail:
//        host: smtp.gmail.com
//        port: 587
//        username: pfsriskmodel@gmail.com
//        password: SapSap@123
//        protocol: smtp
//        tls: true
//        properties.mail.smtp:
//            auth: true
//            starttls.enable: true
//            ssl.trust: smtp.gmail.com

    String host ;  //= env.getProperty("mail.host");
    String port ; //= Integer.parseInt(env.getProperty("mail.port"));
    String userName; //  = env.getProperty("mail.userName");
    String password; // = env.getProperty("mail.password");
    String protocol; // = env.getProperty("mail.protocol");
    String tls;  // = env.getProperty("mail.tls");

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTls() {
        return tls;
    }

    public void setTls(String tls) {
        this.tls = tls;
    }
}
