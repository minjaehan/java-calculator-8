package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Application {
    private static final Pattern CUSTOM = Pattern.compile("^//(.)\n(.*)$", Pattern.DOTALL);

    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String line = Console.readLine();
        if (line == null) line = "";

        line = line.replace("\r", "");

        if (line.contains("\\n")) {
            line = line.replace("\\n", "\n");
        }

        if (line.startsWith("//") && !line.contains("\n")) {
            String next = Console.readLine();
            if (next == null) next = "";
            next = next.replace("\r", "");
            line = line + "\n" + next;
        }

            int result = calculation(line);
            System.out.println("결과 :  " + result);


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
        String splitRegex;


        Matcher m = CUSTOM.matcher(input);
        if (m.matches()) {
            String delim = m.group(1);
            numbers = m.group(2).trim();

            if (numbers.isEmpty()) {
                return new int[0];
            }

            if (delim.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 문자 하나여야 합니다.");
            }

            splitRegex = Pattern.quote(delim);
        } else {
            numbers = input.trim();
            if (numbers.isEmpty()) return new int[0];
            splitRegex = "[,:]";
        }

        String[] tokens = numbers.split(splitRegex);
        if (tokens.length == 0) return new int[0];

        int[] vals = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i].trim();
            if (t.isEmpty()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }
            int v;
            try {
                v = Integer.parseInt(t);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
            }

            if (v <= 0) {
                throw new IllegalArgumentException("양수만 입력할 수 있습니다.");
            }
            vals[i] = v;
        }
        return vals;
    }


}






