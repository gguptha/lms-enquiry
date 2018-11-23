package pfs.lms.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableFeignClients
@SpringBootApplication
public class SapPfsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SapPfsApplication.class, args);
	}

	/*@Bean(name = {"applicationEventMulticaster"})
	public ApplicationEventMulticaster threadPoolAndSecurityAwareEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Executor delegatedExecutor = Executors.newWorkStealingPool();
		Executor delegatingExecutor = new DelegatingSecurityContextExecutor(delegatedExecutor, securityContext);
		eventMulticaster.setTaskExecutor(delegatingExecutor);
		return eventMulticaster;
	}*/
}
