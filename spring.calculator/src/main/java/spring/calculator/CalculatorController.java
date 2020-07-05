/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.calculator;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author abhunavgarg
 */
@RestController
@RequestMapping(value = "/calculate")
public class CalculatorController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{calculationString}")
    public String calculate(@PathVariable String calculationString) throws Exception {

        return doCalculation(calculationString);

    }

    private String doCalculation(String calculationString) throws Exception {
        String str = calculationString;

        str = calculateFor("/", str);
        str = calculateFor("*", str);
        str = calculateFor("+", str);
        str = calculateFor("-", str);

        return str;

    }

    private String calculateFor(String delimeter, String str) throws Exception {
        while (str.contains(delimeter)) {

            int divIndex = str.indexOf(delimeter);
            int startIndex = getNumberStartIndex(divIndex, str);
            int endIndex = getNumberEndIndex(divIndex, str);
            String subString = str.substring(startIndex, endIndex + 1);
//            delimeter = "\\"+String.valueOf(delimete4r);
            String[] numbers = subString.split(Pattern.quote(delimeter));

            String result = null;
            Calculate calculate = new Calculate(this.restTemplate);
            if (delimeter.equals("/")) {
                result = calculate.divide(numbers);
            } else if (delimeter.equals("*")) {
                result = calculate.multiply(numbers);
            } else if (delimeter.equals("+")) {
                result = calculate.add(numbers);
            } else if (delimeter.equals("-")) {
                result = calculate.subtract(numbers);
            }
            Integer resultString = Integer.valueOf(result.toString());

            String newString = replaceResult(startIndex, endIndex, resultString, str);
            str = newString;
        }
        return str;
    }

    private String replaceResult(int startIndex, int endIndex, int divideResult, String str) {
        CharSequence target = str.subSequence(startIndex, endIndex + 1);
        String divideResultString = String.valueOf(divideResult);
        CharSequence replacement = divideResultString.subSequence(0, divideResultString.length());
        String newString = str.replace(target, replacement);
        return newString;
    }

    private int getNumberStartIndex(int index, String str) throws Exception {
        char[] charArray = str.toCharArray();
        int result = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (charArray[i] == '/') {
                return i+1;
            } else if (charArray[i] == '*') {
                return i+1;
            } else if (charArray[i] == '+') {
                return i+1;
            } else if (charArray[i] == '-') {
                return i+1;
            } else {
                result = i;
            }
//            else {
//                throw new Exception("Error");
//            }
        }
        return result;
    }

    private int getNumberEndIndex(int index, String str) throws Exception {
        char[] charArray = str.toCharArray();
        int result = 0;
        for (int i = index + 1; i < str.length(); i++) {
            if (charArray[i] == '/') {
                return i-1;
            } else if (charArray[i] == '*') {
                return i-1;
            } else if (charArray[i] == '+') {
                return i-1;
            } else if (charArray[i] == '-') {
                return i-1;
            } else {
                result = i;
            }
//            else {
//                throw new Exception("Error");
//            }
        }
        return result;
    }

}
