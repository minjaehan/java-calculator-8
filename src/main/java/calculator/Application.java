package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.)\n(.*)$", Pattern.DOTALL);
    private static final String DEFAULT_DELIMITER = "[,:]";

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        try {
            int result = calculate(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // \\n을 실제 개행문자로 변환
        input = input.replace("\\n", "\n");

        String[] numbers = splitByDelimiter(input);
        return sum(numbers);
    }

    private static String[] splitByDelimiter(String input) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);

        if (matcher.matches()) {
            String customDelimiter = matcher.group(1);
            String numbersText = matcher.group(2);
            return numbersText.split(Pattern.quote(customDelimiter));
        }

        return input.split(DEFAULT_DELIMITER);
    }

    private static int sum(String[] numbers) {
        int total = 0;

        for (String number : numbers) {
            int value = parseNumber(number);
            total += value;
        }

        return total;
    }

    private static int parseNumber(String number) {
        int value;
        try {
            value = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다.");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("양수만 입력할 수 있습니다.");
        }

        return value;
    }
}