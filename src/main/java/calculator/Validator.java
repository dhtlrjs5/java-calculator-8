package calculator;

import java.util.regex.Pattern;

import static calculator.ErrorMessageConstants.*;

public class Validator {

    private static final String ONLY_NUMBER_PATTERN = "^\\d+$";
    private static final String NUMBER_AND_DELIMITER_PATTERN_TEMPLATE = "^\\d+(%s\\d+)*$";

    private final InputNormalizer inputNormalizer;
    private final DelimiterExtractor delimiterExtractor;

    public Validator(InputNormalizer inputNormalizer, DelimiterExtractor delimiterExtractor) {
        this.inputNormalizer = inputNormalizer;
        this.delimiterExtractor = delimiterExtractor;
    }

    public String validate(String input) {
        String origin = inputNormalizer.extractOriginAndValidateDelimiterFormat(input);
        String delimiter = delimiterExtractor.extractDelimiter(input);
        validateInput(origin, delimiter);
        validatePositiveNumber(origin, delimiter);

        return origin;
    }

    /**
     * 입력값이 올바른지 확인
     * 숫자 + 구분자 + 숫자 구조인지
     * 허용되지 않은 구분자 검증은 구분자 추출 후 검증 예정
     */
    private void validateInput(String input, String delimiter) {

        if (input.matches(ONLY_NUMBER_PATTERN)) {
            return;
        }

        Pattern pattern = Pattern.compile(java.lang.String.format(NUMBER_AND_DELIMITER_PATTERN_TEMPLATE, delimiter));
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT);
        }
    }

    /**
     * 양수로 이루어져 있는지 확인
     */
    private void validatePositiveNumber(String input, String delimiter) {

        String[] numbers = input.split(delimiter);

        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr);
            if (num <= 0) throw new IllegalArgumentException(NOT_POSITIVE_NUMBER);
        }
    }
}
