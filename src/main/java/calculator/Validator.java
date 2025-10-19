package calculator;

import java.util.regex.Pattern;

import static calculator.ErrorMessageConstants.*;

public class Validator {

    private static final String NUMBER_START_PATTERN = "^\\d.*";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//.\\\\n.*";
    private static final String ONLY_NUMBER_PATTERN = "^\\d+$";
    private static final String NUMBER_AND_DELIMITER_PATTERN_TEMPLATE = "^\\d+(%s\\d+)*$";

    public static String validate(String input) {
        String delimiter = DelimiterExtractor.extractDelimiter(input);
        String origin = validateCustomDelimiter(input);
        validateInput(origin, delimiter);
        validatePositiveNumber(origin, delimiter);

        return origin;
    }

    /**
     * 커스텀 구분자 형식이 올바른 형식인지 확인
     */
    private static String validateCustomDelimiter(String input) {

        if (input.matches(NUMBER_START_PATTERN)) {
            return input;
        }

        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_PATTERN);
        if (pattern.matcher(input).matches()) {
            int idx = input.indexOf('n');
            if (idx == -1) throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER);

            return input.substring(idx + 1);
        }

        throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER);
    }

    /**
     * 입력값이 올바른지 확인
     * 숫자 + 구분자 + 숫자 구조인지
     * 허용되지 않은 구분자 검증은 구분자 추출 후 검증 예정
     */
    private static void validateInput(String input, String delimiter) {

        if (input.matches(ONLY_NUMBER_PATTERN)) {
            return;
        }

        Pattern pattern = Pattern.compile(String.format(NUMBER_AND_DELIMITER_PATTERN_TEMPLATE, delimiter));
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT);
        }
    }

    /**
     * 양수로 이루어져 있는지 확인
     */
    private static void validatePositiveNumber(String input, String delimiter) {

        String[] numbers = input.split(delimiter);

        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr);
            if (num <= 0) throw new IllegalArgumentException(NOT_POSITIVE_NUMBER);
        }
    }
}
