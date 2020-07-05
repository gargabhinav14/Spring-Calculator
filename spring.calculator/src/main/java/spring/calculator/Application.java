package spring.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
 
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan("spring.calculator")
public class Application {
    
    @Bean
    @LoadBalanced
    public RestTemplate getRestTamplate()
    {
        System.out.println("Instance of Rest Template Created");
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
