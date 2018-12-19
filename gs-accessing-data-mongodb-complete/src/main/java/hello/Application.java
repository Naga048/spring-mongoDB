package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.poc.service.CounterService;
import com.poc.service.CounterServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableMongoAuditing
@ComponentScan({"com.poc","hello"})
@EnableMongoRepositories("com.poc.service")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CounterService counterService() {

		return new CounterServiceImpl();

	}

	

}
