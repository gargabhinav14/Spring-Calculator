/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springadder.spring.adder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abhunavgarg
 */

@RestController
@RequestMapping(value = "/add")
public class AdderController {
     
    @GetMapping(value = "{numbers}")
    public String multiply(@PathVariable String numbers) {
        Integer num1 = Integer.valueOf(numbers.split("-")[0]);
        Integer num2 = Integer.valueOf(numbers.split("-")[1]);

        return String.valueOf(num1 + num2);

    }
    
    
}
