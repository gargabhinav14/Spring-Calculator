/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.calculator;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author abhunavgarg
 */
public class Calculate {

    RestTemplate restTemplate;

    Calculate(RestTemplate template) {
        this.restTemplate = template;
    }

    String divide(String[] numbers) {
        return restTemplate.getForObject("http://divider-service/divide/" + numbers[0] + "-" + numbers[1], String.class);
    }

    String multiply(String[] numbers) {
        return restTemplate.getForObject("http://multiplicator-service/multiply/" + numbers[0] + "-" + numbers[1], String.class);
    }

    String add(String[] numbers) {
        return restTemplate.getForObject("http://adder-service/add/" + numbers[0] + "-" + numbers[1], String.class);
    }

    String subtract(String[] numbers) {
        return restTemplate.getForObject("http://subtractor-service/subtract/" + numbers[0] + "-" + numbers[1], String.class);
    }

}
