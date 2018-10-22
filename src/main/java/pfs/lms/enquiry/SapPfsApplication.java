package pfs.lms.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SapPfsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SapPfsApplication.class, args);
	}
}
