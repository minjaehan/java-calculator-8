package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;



public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        int result = calculation(input);
        System.out.println("결과 : " + result);
    }

    public static int calculation(String input) {
       int[] nums = parseAndValidate(input);
       int sum = 0;
       for(int n : nums) sum += n;
       return sum;
    }

    private static int[] parseAndValidate(String input){

        if (input == null || input.trim().isEmpty()) {
            return new int[0];
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
                return new int[0];
            }
        } else {
            numbers = input;
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

}






