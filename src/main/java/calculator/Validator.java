package calculator;

import java.util.regex.Pattern;

public class Validator {

    public static String validate(String input) {
        String delimiter = DelimiterExtractor.extractDelimiter(input);
        String origin = validateCustomDelimiter(input);
        validateInput(origin, delimiter);
        validatePositiveNumber(origin, delimiter);

        return origin;
    }

    /**
     * @param input
     * 커스텀 구분자 형식이 올바른 형식인지 확인
     */
    private static String validateCustomDelimiter(String input) {
        if (input.matches("^\\d.*")) {
            return input;
        }

        Pattern pattern = Pattern.compile("^//.\\\\n.*");
        if (pattern.matcher(input).matches()) {
            int idx = input.indexOf('n');
            if (idx == -1) throw new IllegalArgumentException("커스텀 구분자 형식 오류");

            return input.substring(idx + 1);
        }

        throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
    }

    /**
     * @param input
     * 입력값이 올바른지 확인
     * 숫자 + 구분자 + 숫자 구조인지
     * 허용되지 않은 구분자 검증은 구분자 추출 후 검증 예정
     */
    private static void validateInput(String input, String delimiter) {

        if (input.matches("^\\d+$")) {
            return;
        }

        Pattern pattern = Pattern.compile("^\\d+(" + delimiter + "\\d+)*$");
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException("입력값 형식이 올바르지 않습니다.");
        }
    }

    /**
     * @param input
     * 양수로 이루어져 있는지 확인
     */
    private static void validatePositiveNumber(String input, String delimiter) {

        String[] numbers = input.split(delimiter);

        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr);
            if (num <= 0) throw new IllegalArgumentException("양수가 아닙니다.");
        }
    }
}
