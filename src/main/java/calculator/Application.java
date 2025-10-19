package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;



public class Application {
    public static void main(String[] args) {
        String input = Console.readLine();
        int result = calculation(input);
        System.out.println(result);
    }

    public static int calculation(String input) {
       int[] nums = parseAndValidate(input);
       int sum = 0;
       for(int n : nums){
           sum += n;
       }
       return sum;
    }

    private static int sum(String[] input){
        int sum=0;
        for(String eachNum : input)
        {
            sum += Integer.parseInt(eachNum.trim());
        }
        return sum;
    }

    private static String[] split(String input, String pattern){
        return Pattern.compile(pattern).split(input);
    }

    private static int[] parseAndValidate(String input){
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("input is empty");
        }

        String numbers;
        String pattern = "[,:]";

        if (input.startsWith("//")) {
            int nl = input.indexOf('\n');
            if (nl <= 2) {
                throw new IllegalArgumentException("invalid custom delimiter header");
            }
            String delim = input.substring(2, nl);
            pattern = Pattern.quote(delim);
            numbers = input.substring(nl + 1);
            if (numbers.trim().isEmpty()) {
                throw new IllegalArgumentException("no numbers after delimiter");
            }
        } else {
            numbers = input; // 기본 구분자 사용
        }
        
        String[] tokens = Pattern.compile(pattern).split(numbers);

        int[] vals = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i].trim();
            if (t.isEmpty()) {
                throw new IllegalArgumentException("the input is empty");
            }
            int v;
            try {
                v = Integer.parseInt(t);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("the input is not a number");
            }
            if (v < 0) {
                throw new IllegalArgumentException("negative numbers are not allowed");
            }
            vals[i] = v;
        }
        return vals;
    }


    private static class deli {
        final String input;
        final String delipattern;

        deli(String input, String delipattern) {
            this.input = input;
            this.delipattern = delipattern;
        }
    }
}






