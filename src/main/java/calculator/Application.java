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

        deli info = parse(input);
        if(info.input == null){
            return 0;
        }
        String[] numbers= split(info.input,info.delipattern);

        int sum=0;
        for(int i=0;i<numbers.length;i++){
            sum+=Integer.parseInt(numbers[i]);
        }
        return sum;

    }


    private static String[] split(String input, String pattern){
        return Pattern.compile(pattern).split(input);

    }

    public static deli parse(String input) {
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






