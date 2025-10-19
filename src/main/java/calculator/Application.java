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
        if (input == null) {
            return 0;
        }

        deli info = parsing(input);
        if(info.input == null){
            return 0;
        }
        String[] numbers= split(info.input,info.delipattern);

       validation(numbers);

       return sum(numbers);

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

    private static void validation(String[] input){
        for(String eachNum : input){
            String num = eachNum.trim();
            if(num.isEmpty()){
                throw new IllegalArgumentException("the input is empty");
            }
            int value;
            try{
                value = Integer.parseInt(num);
            } catch(NumberFormatException e){
                throw new IllegalArgumentException("the input is not a number");
            }
            if(value <0)
            {
                throw new IllegalArgumentException("the input is not a negative number");
            }



        }
    }

    public static deli parsing(String input) {
        String defaultPattern = "[,:]";

        if (input.startsWith("//")
                && input.length() >= 5
                && input.charAt(3) == '\\'
                && input.charAt(4) == 'n') {
            char custom = input.charAt(2);
            String pattern = Pattern.quote(String.valueOf(custom));
            String number = input.substring(5);
            return new deli(number, pattern);
        }

        return new deli(input, defaultPattern);
    }

    //구분자 정보 클래스
    private static class deli {
        final String input;
        final String delipattern;

        deli(String input, String delipattern) {
            this.input = input;
            this.delipattern = delipattern;
        }
    }

}






